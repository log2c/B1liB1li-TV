package com.github.log2c.b1lib1li_tv.ui.fragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.leanback.app.VideoSupportFragment;
import androidx.leanback.app.VideoSupportFragmentGlueHost;
import androidx.leanback.media.PlaybackGlue;
import androidx.leanback.media.PlaybackTransportControlGlue;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.ui.player.PlayerActivity;
import com.github.log2c.b1lib1li_tv.widget.OwnDanmakuView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.DebugTextViewHelper;
import com.google.android.exoplayer2.util.EventLogger;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class ExoPlayerFragment extends VideoSupportFragment implements Player.Listener {
    private static final String TAG = ExoPlayerFragment.class.getSimpleName();
    private StyledPlayerView mPlayerView;
    private ExoPlayer mPlayer;
    private DebugTextViewHelper mDebugViewHelper;
    protected TextView mDebugTextView;
    protected OwnDanmakuView mDanmakuView;
    private PlaybackTransportControlGlue<LeanbackPlayerAdapter> mPlayerGlue;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String video = intent.getStringExtra("video");
            String audio = intent.getStringExtra("audio");
            String danmuPath = intent.getStringExtra("danmu_path");
            if (!TextUtils.isEmpty(danmuPath)) {
                mDanmakuView.setDanmaKuStream(FileUtils.getFileByPath(danmuPath));
            }
            setPlayerMediaSource(video, audio);
            mPlayer.play();
        }
    };
    private boolean mDanmakuLoaded;

    @SuppressLint("PrivateResource")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getContext().getTheme().applyStyle(androidx.leanback.R.style.Theme_Leanback, true);
        final FrameLayout rootView = (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);

        mPlayerView = new StyledPlayerView(requireContext());
        mPlayerView.setControllerAutoShow(false);
        mPlayerView.setControllerHideOnTouch(false);
        rootView.addView(mPlayerView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        mDebugTextView = new TextView(requireContext());
        mDebugTextView.setBackgroundColor(Color.parseColor("#88000000"));
        View controls_dock = rootView.findViewById(ResourceUtils.getIdByName("playback_controls_dock"));
        rootView.addView(mDebugTextView, rootView.indexOfChild(controls_dock) + 1, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        mDanmakuView = new OwnDanmakuView(requireContext());
        rootView.addView(mDanmakuView, rootView.indexOfChild(mDebugTextView) + 1, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        initializePlayer();
        mPlayerGlue = new PlaybackTransportControlGlue<>(getActivity(),
                new LeanbackPlayerAdapter(getActivity(), mPlayer, 200));
        mPlayerGlue.setHost(new VideoSupportFragmentGlueHost(this));
        mPlayerGlue.addPlayerCallback(new PlaybackGlue.PlayerCallback() {
            @Override
            public void onPreparedStateChanged(PlaybackGlue glue) {
                if (glue.isPrepared()) {
//                    playerGlue.setSeekProvider(new MySeekProvider());
                    mPlayerGlue.play();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(mReceiver, new IntentFilter(PlayerActivity.PLAYER_DATA_INTENT_FILTER));
        super.onCreate(savedInstanceState);
    }

    protected void initializePlayer() {
        if (mPlayer == null) {
            ExoPlayer.Builder playerBuilder =
                    new ExoPlayer.Builder(/* context= */ requireContext());
            mPlayer = playerBuilder.build();
            mPlayer.addListener(this);
            mPlayer.addAnalyticsListener(new EventLogger());
            mPlayer.setAudioAttributes(AudioAttributes.DEFAULT, /* handleAudioFocus= */ true);
            mPlayer.setPlayWhenReady(true);
            mPlayerView.setPlayer(mPlayer);
            mDebugTextView.setVisibility(AppConfigRepository.getInstance().isEnableDebugView() ? View.VISIBLE : View.GONE);
            mDebugViewHelper = new DebugTextViewHelper(mPlayer, mDebugTextView);
            mDebugViewHelper.start();
        }
//        boolean haveStartPosition = startItemIndex != C.INDEX_UNSET;
//        if (haveStartPosition) {
//            mPlayer.seekTo(startItemIndex, startPosition);
//        }
//        mPlayer.setMediaItems(mediaItems, /* resetPosition= */ !haveStartPosition);
        mPlayer.prepare();
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory() {
        return new DefaultHttpDataSource.Factory()
                .setUserAgent(Constants.DEFAULT_USER_AGENT)
                .setDefaultRequestProperties(Constants.PLAYER_HEADERS)
                .setAllowCrossProtocolRedirects(true);
    }

    private void setPlayerMediaSource(String videoUrl, String audioUrl) {
        HttpDataSource.Factory dataSourceFactory = buildHttpDataSourceFactory();
        final MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(Uri.parse(videoUrl)));
        final MediaSource audioSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(Uri.parse(audioUrl)));
        MergingMediaSource source = new MergingMediaSource(videoSource, audioSource);

        mPlayer.setMediaSource(source);
    }

    protected void releasePlayer() {
        if (mPlayer != null) {
            if (mDebugViewHelper != null) {
                mDebugViewHelper.stop();
                mDebugViewHelper = null;
            }
            mPlayer.release();
            mPlayer = null;
            mPlayerView.setPlayer(/* player= */ null);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > 23) {
            initializePlayer();
            if (mPlayerView != null) {
                mPlayerView.onResume();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT <= 23 || mPlayer == null) {
            initializePlayer();
            if (mPlayerView != null) {
                mPlayerView.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT <= 23) {
            if (mPlayerView != null) {
                mPlayerView.onPause();
            }
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT > 23) {
            if (mPlayerView != null) {
                mPlayerView.onPause();
            }
            releasePlayer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(mReceiver);
        mDanmakuView.release();
    }

    private static final Map<@Player.State Integer, String> PLAYBACK_STATUS = new HashMap<>();

    static {
        PLAYBACK_STATUS.put(ExoPlayer.STATE_IDLE, "空闲等待");
        PLAYBACK_STATUS.put(ExoPlayer.STATE_BUFFERING, "缓冲中");
        PLAYBACK_STATUS.put(ExoPlayer.STATE_READY, "准备完成");
        PLAYBACK_STATUS.put(ExoPlayer.STATE_ENDED, "完成播放");
    }

    @Override
    public void onIsPlayingChanged(boolean isPlaying) {
        Player.Listener.super.onIsPlayingChanged(isPlaying);
        Log.d(TAG, "onIsPlayingChanged: " + (isPlaying ? "播放中" : "暂停"));
        if (isPlaying) {
            mDanmakuView.onVideoResume();
        } else {
            mDanmakuView.onVideoPause();
        }
    }

    @Override
    public void onPlaybackStateChanged(int playbackState) {
        Player.Listener.super.onPlaybackStateChanged(playbackState);
        Log.d(TAG, "onPlaybackStateChanged: " + PLAYBACK_STATUS.get(playbackState));
        switch (playbackState) {
            case ExoPlayer.STATE_READY:
                if (!mDanmakuLoaded) {
                    mDanmakuView.setDanmakuStartSeekPosition(mPlayer.getCurrentPosition());
                }
                break;
            case ExoPlayer.STATE_IDLE:
                break;
            case ExoPlayer.STATE_BUFFERING:
                break;
            case ExoPlayer.STATE_ENDED:
                mDanmakuView.release();
                break;
        }
    }
}
