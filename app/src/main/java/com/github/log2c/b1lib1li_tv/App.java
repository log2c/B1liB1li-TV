package com.github.log2c.b1lib1li_tv;

import android.app.Application;

import com.github.log2c.b1lib1li_tv.common.CrashHandler;
import com.github.log2c.base.base.BaseCoreApplication;
import com.github.log2c.base.toast.ToastUtils;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseCoreApplication.init(this, BuildConfig.DEBUG);
        initCore();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    private void initCore() {
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);

        GSYVideoType.enableMediaCodec();
        GSYVideoType.enableMediaCodecTexture();

        ToastUtils.showLong("硬解: " + GSYVideoType.isMediaCodec() + ", 硬解码渲染优化: " + GSYVideoType.isMediaCodecTexture());
    }
}
