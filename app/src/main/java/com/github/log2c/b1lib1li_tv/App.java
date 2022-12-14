package com.github.log2c.b1lib1li_tv;

import android.app.Application;

import com.dueeeke.videoplayer.exo.ExoMediaPlayerFactory;
import com.dueeeke.videoplayer.player.VideoViewConfig;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.github.log2c.b1lib1li_tv.common.CrashHandler;
import com.github.log2c.base.base.BaseCoreApplication;

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
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                .setLogEnabled(true)
//                //使用使用IjkPlayer解码
//                .setPlayerFactory(IjkPlayerFactory.create())
//                //使用ExoPlayer解码
                .setPlayerFactory(ExoMediaPlayerFactory.create())
                //使用MediaPlayer解码
//                .setPlayerFactory(AndroidMediaPlayerFactory.create())
                .build());
    }
}
