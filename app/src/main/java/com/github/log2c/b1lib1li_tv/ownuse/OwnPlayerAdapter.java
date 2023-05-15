package com.github.log2c.b1lib1li_tv.ownuse;

import android.util.Log;

import androidx.leanback.media.PlaybackGlueHost;
import androidx.leanback.media.PlayerAdapter;

import com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

public class OwnPlayerAdapter extends PlayerAdapter {
    private static final String TAG = OwnPlayerAdapter.class.getSimpleName();
    private GSYVideoPlayer mGSYVideoPlayer;
    private boolean isPrepared = false;

    public OwnPlayerAdapter(GSYVideoPlayer gsyVideoPlayer) {
        this.mGSYVideoPlayer = gsyVideoPlayer;
        init();
    }

    private void init() {
        mGSYVideoPlayer.getGSYVideoManager().setListener(new GSYMediaPlayerListener() {
            @Override
            public void onPrepared() {
                isPrepared = true;
                Log.d(TAG, "onPrepared: ");
            }

            @Override
            public void onAutoCompletion() {
                Log.d(TAG, "onAutoCompletion: ");
            }

            @Override
            public void onCompletion() {
                Log.d(TAG, "onCompletion: ");
            }

            @Override
            public void onBufferingUpdate(int percent) {
                Log.d(TAG, "onBufferingUpdate: ");
                getCallback().onBufferedPositionChanged(OwnPlayerAdapter.this);
            }

            @Override
            public void onSeekComplete() {
                Log.d(TAG, "onSeekComplete: ");
            }

            @Override
            public void onError(int what, int extra) {
                Log.d(TAG, "onError: " + what + ", " + extra + ".");
                getCallback().onError(OwnPlayerAdapter.this, what, extra + "");
            }

            @Override
            public void onInfo(int what, int extra) {
                Log.d(TAG, "onInfo: " + what + ", " + extra + ".");
            }

            @Override
            public void onVideoSizeChanged() {
                Log.d(TAG, "onVideoSizeChanged: ");
            }

            @Override
            public void onBackFullscreen() {
                Log.d(TAG, "onBackFullscreen: ");
            }

            @Override
            public void onVideoPause() {
                Log.d(TAG, "onVideoPause: ");
                getCallback().onPlayStateChanged(OwnPlayerAdapter.this);
            }

            @Override
            public void onVideoResume() {
                Log.d(TAG, "onVideoResume: ");
                getCallback().onPlayStateChanged(OwnPlayerAdapter.this);
            }

            @Override
            public void onVideoResume(boolean seek) {
                Log.d(TAG, "onVideoResume: ");
                getCallback().onPlayStateChanged(OwnPlayerAdapter.this);
            }
        });
    }

    @Override
    public void play() {
        mGSYVideoPlayer.onVideoResume();
    }

    @Override
    public void pause() {
        mGSYVideoPlayer.onVideoPause();
    }

    @Override
    public boolean isPrepared() {
        return isPrepared;
    }

    @Override
    public void next() {
        super.next();
    }

    @Override
    public void previous() {
        super.previous();
    }

    @Override
    public void fastForward() {
        super.fastForward();
    }

    @Override
    public void rewind() {
        super.rewind();
    }

    @Override
    public void seekTo(long positionInMs) {
        mGSYVideoPlayer.getGSYVideoManager().seekTo(positionInMs);
    }

    @Override
    public void setProgressUpdatingEnabled(boolean enable) {
        super.setProgressUpdatingEnabled(enable);
    }

    @Override
    public void setShuffleAction(int shuffleActionIndex) {
        super.setShuffleAction(shuffleActionIndex);
    }

    @Override
    public void setRepeatAction(int repeatActionIndex) {
        super.setRepeatAction(repeatActionIndex);
    }

    @Override
    public boolean isPlaying() {
        return mGSYVideoPlayer.getGSYVideoManager().isPlaying();
    }

    @Override
    public long getDuration() {
        return mGSYVideoPlayer.getGSYVideoManager().getDuration();
    }

    @Override
    public long getSupportedActions() {
        return super.getSupportedActions();
    }

    @Override
    public long getCurrentPosition() {
        return mGSYVideoPlayer.getGSYVideoManager().getCurrentPosition();
    }

    @Override
    public long getBufferedPosition() {
        return mGSYVideoPlayer.getGSYVideoManager().getBufferedPercentage();
    }

    @Override
    public void onAttachedToHost(PlaybackGlueHost host) {
        super.onAttachedToHost(host);
    }

    @Override
    public void onDetachedFromHost() {
        super.onDetachedFromHost();
    }
}
