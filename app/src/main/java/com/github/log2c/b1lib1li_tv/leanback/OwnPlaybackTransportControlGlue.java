package com.github.log2c.b1lib1li_tv.leanback;

import android.content.Context;

import androidx.leanback.media.PlaybackTransportControlGlue;
import androidx.leanback.media.PlayerAdapter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.PlaybackControlsRow;

public class OwnPlaybackTransportControlGlue<T extends PlayerAdapter> extends PlaybackTransportControlGlue<T> {
    private ActionClickListener mActionClickListener;

    public OwnPlaybackTransportControlGlue(Context context, T impl) {
        super(context, impl);
    }

//    private PlaybackControlsRow.RepeatAction repeatAction;
//    private PlaybackControlsRow.PictureInPictureAction pipAction;
//    private PlaybackControlsRow.ThumbsUpAction thumbsUpAction;
//    private PlaybackControlsRow.ThumbsDownAction thumbsDownAction;
//    private PlaybackControlsRow.SkipPreviousAction skipPreviousAction;
//    private PlaybackControlsRow.SkipNextAction skipNextAction;
    private PlaybackControlsRow.FastForwardAction fastForwardAction;
    private PlaybackControlsRow.RewindAction rewindAction;
    private PlaybackControlsRow.HighQualityAction highQualityAction;

    @Override
    protected void onCreatePrimaryActions(ArrayObjectAdapter primaryActionsAdapter) {
        super.onCreatePrimaryActions(primaryActionsAdapter);
        highQualityAction = new PlaybackControlsRow.HighQualityAction(getContext());
//        repeatAction = new PlaybackControlsRow.RepeatAction(getContext());
//        pipAction = new PlaybackControlsRow.PictureInPictureAction(getContext());
//        thumbsUpAction = new PlaybackControlsRow.ThumbsUpAction(getContext());
//        thumbsDownAction = new PlaybackControlsRow.ThumbsDownAction(getContext());
//        skipPreviousAction = new PlaybackControlsRow.SkipPreviousAction(getContext());
//        skipNextAction=new ;
        fastForwardAction = new PlaybackControlsRow.FastForwardAction(getContext());
        rewindAction = new PlaybackControlsRow.RewindAction(getContext());
//        primaryActionsAdapter.add(skipPreviousAction);
        primaryActionsAdapter.add(rewindAction);
        primaryActionsAdapter.add(fastForwardAction);
//        primaryActionsAdapter.add(skipNextAction);
        primaryActionsAdapter.add(highQualityAction);
    }

    @Override
    protected void onCreateSecondaryActions(ArrayObjectAdapter adapter) {
        super.onCreateSecondaryActions(adapter);
//        adapter.add(thumbsDownAction);
//        adapter.add(thumbsUpAction);
    }

    @Override
    public void onActionClicked(Action action) {
        if (action == highQualityAction) {
            if (mActionClickListener != null) {
                mActionClickListener.onQualityClick(action);
            }
        }
        int duration = 10 * 1000;
        if (action == rewindAction) {
            getPlayerAdapter().seekTo(getPlayerAdapter().getCurrentPosition() - duration);
        } else if (action == fastForwardAction) {
            getPlayerAdapter().seekTo(getPlayerAdapter().getCurrentPosition() + duration);
        }
//        else if (action == thumbsDownAction) {
//            // Handle ThumbsDown
//        } else if (action == thumbsUpAction) {
//            // Handle ThumbsUp
//        }
        else {
            super.onActionClicked(action);
        }
    }

    public void setActionClickListener(ActionClickListener actionClickListener) {
        this.mActionClickListener = actionClickListener;
    }

    public interface ActionClickListener {
        void onQualityClick(Action action);
    }
}
