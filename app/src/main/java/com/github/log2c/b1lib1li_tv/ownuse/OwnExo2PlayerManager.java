package com.github.log2c.b1lib1li_tv.ownuse;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.github.log2c.b1lib1li_tv.utils.ExoPlayerUtil;
import com.shuyu.gsyvideoplayer.cache.ICacheManager;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;

import java.util.List;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.exo2.IjkExo2MediaPlayer;

public class OwnExo2PlayerManager extends Exo2PlayerManager {
    private static final String TAG = OwnExo2PlayerManager.class.getSimpleName();

    @Override
    public void initVideoPlayer(Context context, Message msg, List<VideoOptionModel> optionModelList, ICacheManager cacheManager) {
        super.initVideoPlayer(context, msg, optionModelList, cacheManager);
        if (getMediaPlayer() instanceof IjkExo2MediaPlayer) {
            ((IjkExo2MediaPlayer) getMediaPlayer()).setRendererFactory(ExoPlayerUtil.buildRenderersFactory(Utils.getApp()));
            Log.d(TAG, "initVideoPlayer: Exoplayer使用 FFMpeg解码.");
        }
    }
}
