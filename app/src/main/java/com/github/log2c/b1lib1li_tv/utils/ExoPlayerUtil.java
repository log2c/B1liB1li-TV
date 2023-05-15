package com.github.log2c.b1lib1li_tv.utils;

import android.content.Context;

import com.google.android.exoplayer2.DefaultRenderersFactory;

public class ExoPlayerUtil {
    public static DefaultRenderersFactory buildRenderersFactory(
            Context context) {
        return new FfmpegRenderersFactory(context.getApplicationContext());
    }
}