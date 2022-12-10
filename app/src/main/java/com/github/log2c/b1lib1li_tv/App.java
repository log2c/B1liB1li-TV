package com.github.log2c.b1lib1li_tv;

import android.app.Application;

import com.github.log2c.base.base.BaseCoreApplication;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseCoreApplication.init(this, BuildConfig.DEBUG);
    }
}
