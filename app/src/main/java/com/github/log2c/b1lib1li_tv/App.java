package com.github.log2c.b1lib1li_tv;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;

import com.github.log2c.b1lib1li_tv.network.NetKit;
import com.github.log2c.base.base.BaseCoreApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings({"unchecked", "rawtypes"})
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetKit.getInstance().init();
        closeDetectedProblemApiDialog();
        BaseCoreApplication.init(this, BuildConfig.DEBUG);
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
            @SuppressLint("PrivateApi") Method method = clsActivityThread.getDeclaredMethod("currentActivityThread");
            method.setAccessible(true);
            Object activityThread = method.invoke(null);
            @SuppressLint("PrivateApi") Field hiddenApiWarning = clsActivityThread.getDeclaredField("mHiddenApiWarningShown");
            hiddenApiWarning.setAccessible(true);
            hiddenApiWarning.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
