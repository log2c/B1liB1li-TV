package com.github.log2c.b1lib1li_tv;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;

import com.github.log2c.b1lib1li_tv.network.NetKit;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.base.base.BaseCoreApplication;
import com.github.log2c.base.toast.ToastUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.cache.CacheFactory;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.exo2.ExoPlayerCacheManager;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetKit.getInstance().init();
        closeDetectedProblemApiDialog();
        BaseCoreApplication.init(this, BuildConfig.DEBUG);
        initCore();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
    }

    private void initCore() {
        if (AppConfigRepository.getInstance().isUseExoPlayer()) {
            PlayerFactory.setPlayManager(Exo2PlayerManager.class);
            CacheFactory.setCacheManager(ExoPlayerCacheManager.class);
        } else {
            PlayerFactory.setPlayManager(IjkPlayerManager.class);
        }
        GSYVideoType.enableMediaCodec();
        GSYVideoType.enableMediaCodecTexture();
        ToastUtils.showLong("硬解: " + GSYVideoType.isMediaCodec() + ", 硬解码渲染优化: " + GSYVideoType.isMediaCodecTexture());

        List<VideoOptionModel> listVideoOptionModel = new ArrayList<>();
        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "safe", 0);
        VideoOptionModel videoOptionModel2 =
                new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "protocol_whitelist",
                        "rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
        listVideoOptionModel.add(videoOptionModel);
        listVideoOptionModel.add(videoOptionModel2);
        GSYVideoManager.instance().setOptionModelList(listVideoOptionModel);
    }

    private void closeDetectedProblemApiDialog() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return;
        }
        try {
            @SuppressLint("PrivateApi") Class clsPkgParser = Class.forName("android.content.pm.PackageParser$Package");
            Constructor constructor = clsPkgParser.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);

            @SuppressLint("PrivateApi") Class clsActivityThread = Class.forName("android.app.ActivityThread");
            Method method = clsActivityThread.getDeclaredMethod("currentActivityThread");
            method.setAccessible(true);
            Object activityThread = method.invoke(null);
            Field hiddenApiWarning = clsActivityThread.getDeclaredField("mHiddenApiWarningShown");
            hiddenApiWarning.setAccessible(true);
            hiddenApiWarning.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
