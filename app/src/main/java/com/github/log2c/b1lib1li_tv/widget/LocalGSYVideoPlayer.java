package com.github.log2c.b1lib1li_tv.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.github.log2c.b1lib1li_tv.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class LocalGSYVideoPlayer extends StandardGSYVideoPlayer {
    public LocalGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public LocalGSYVideoPlayer(Context context) {
        super(context);
    }

    public LocalGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_layout_local;
    }
}
