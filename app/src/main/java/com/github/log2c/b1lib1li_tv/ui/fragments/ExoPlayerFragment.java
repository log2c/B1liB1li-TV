package com.github.log2c.b1lib1li_tv.ui.fragments;

import android.annotation.SuppressLint;
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

import androidx.annotation.NonNull;
import androidx.leanback.app.VideoSupportFragment;
import androidx.leanback.app.VideoSupportFragmentGlueHost;
import androidx.leanback.media.PlaybackGlue;
import androidx.leanback.widget.Action;

import com.blankj.utilcode.util.FileUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.contracts.PlayerActivityContract;
import com.github.log2c.b1lib1li_tv.contracts.PlayerFragmentContract;
import com.github.log2c.b1lib1li_tv.leanback.LeanbackPlayerAdapter;
import com.github.log2c.b1lib1li_tv.leanback.OwnPlaybackTransportControlGlue;
import com.github.log2c.b1lib1li_tv.leanback.SelectDialogFragment;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
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
import java.util.List;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class ExoPlayerFragment extends VideoSupportFragment implements Player.Listener, OwnPlaybackTransportControlGlue.ActionClickListener, PlayerFragmentContract {
    private static final String TAG = ExoPlayerFragment.class.getSimpleName();
    private StyledPlayerView mPlayerView;
    private ExoPlayer mPlayer;
    private DebugTextViewHelper mDebugViewHelper;
    protected TextView mDebugTextView;
    protected OwnDanmakuView mDanmakuView;
    private OwnPlaybackTransportControlGlue<LeanbackPlayerAdapter> mPlayerGlue;

    @SuppressLint("PrivateResource")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getContext().getTheme().applyStyle(androidx.leanback.R.style.Theme_Leanback, true);
        final FrameLayout rootView = (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);

        mPlayerView = new StyledPlayerView(requireContext());
        mPlayerView.setUseController(false);
        rootView.addView(mPlayerView, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mDanmakuView = new OwnDanmakuView(requireContext());
        rootView.addView(mDanmakuView, rootView.indexOfChild(getSurfaceView()) + 1, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        mDebugTextView = new TextView(requireContext());
        mDebugTextView.setBackgroundColor(Color.parseColor("#88000000"));
        rootView.addView(mDebugTextView, rootView.indexOfChild(getSurfaceView()) + 1, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        initializePlayer();
        mPlayerGlue = new OwnPlaybackTransportControlGlue<>(getActivity(),
                new LeanbackPlayerAdapter(getActivity(), mPlayer, 200));
        mPlayerGlue.setActionClickListener(this);
        mPlayerGlue.setHost(new VideoSupportFragmentGlueHost(this));
        mPlayerGlue.addPlayerCallback(new PlaybackGlue.PlayerCallback() {
            @Override
            public void onPreparedStateChanged(PlaybackGlue glue) {
                Log.d(TAG, "onPreparedStateChanged");
                if (glue.isPrepared()) {
//                    playerGlue.setSeekProvider(new MySeekProvider());
                    mPlayerGlue.play();
                }
            }
        });
        return rootView;
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
        if (AppConfigRepository.getInstance().fetchDanmakuToggle()) {
            if (isPlaying) {
                mDanmakuView.onVideoResume();
            } else {
                mDanmakuView.onVideoPause();
            }
        }
    }

    @Override
    public void onPositionDiscontinuity(@NonNull Player.PositionInfo oldPosition, @NonNull Player.PositionInfo newPosition, @Player.DiscontinuityReason int reason) {
        mDanmakuView.seekTo(newPosition.positionMs);
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    @Override
    public void onPlaybackStateChanged(int playbackState) {
        Player.Listener.super.onPlaybackStateChanged(playbackState);
        Log.d(TAG, "onPlaybackStateChanged: " + PLAYBACK_STATUS.get(playbackState));
        switch (playbackState) {
            case ExoPlayer.STATE_READY:
                break;
            case ExoPlayer.STATE_IDLE:
                break;
            case ExoPlayer.STATE_BUFFERING:
                break;
            case ExoPlayer.STATE_ENDED:
                break;
        }
    }

    private PlayerActivityContract checkActivity() {
        if (!(requireActivity() instanceof PlayerActivityContract)) {
            throw new IllegalArgumentException("Activity 必须实现 PlayerActivityContract 接口.");
        }
        return (PlayerActivityContract) requireActivity();
    }

    @Override
    public void onQualityClick(Action action) {
        List<PlayUrlModel.DashModel.VideoModel> modelList = checkActivity().getPlayUrlModel().getDash().getVideo();
        final int idx = AppConfigRepository.getInstance().determinedVideoInDashMode(modelList);

        String[] entries = new String[modelList.size()];
        CharSequence[] entryValues = new CharSequence[modelList.size()];
        for (int i = 0; i < modelList.size(); i++) {
            PlayUrlModel.DashModel.VideoModel m = modelList.get(i);
            entries[i] = m.getWidth() + "x" + m.getHeight() + "@" + m.getFrameRate() + "P " + m.getCodecs();
            entryValues[i] = m.getId() + "$$" + m.getCodecs();
        }
        SelectDialogFragment.newSingleInstance(getString(R.string.resolution), "", entries, entryValues, entryValues[idx])
                .show(getChildFragmentManager(), "xxx");
    }

    @Override
    public long playingPosition() {
        return mPlayer.getCurrentPosition();
    }

    @Override
    public void onPlayerDataPrepared(String audioUrl, String videoUrl, String danmakuFilePath) {
        if (!TextUtils.isEmpty(danmakuFilePath)) {
            mDanmakuView.setDanmaKuStream(FileUtils.getFileByPath(danmakuFilePath));
            mDanmakuView.setDanmakuShow(AppConfigRepository.getInstance().fetchDanmakuToggle());
        }
        setPlayerMediaSource(videoUrl, audioUrl);
        mPlayer.play();
        mPlayer.seekTo(checkActivity().getPlayUrlModel().getLast_play_time());
    }
}
