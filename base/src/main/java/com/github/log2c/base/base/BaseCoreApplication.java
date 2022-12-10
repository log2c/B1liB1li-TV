package com.github.log2c.base.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.github.log2c.base.toast.ToastUtils;
import com.xuexiang.xui.XUI;

public class BaseCoreApplication extends Application {
    public static void init(Application application, boolean debug) {
        Utils.init(application);
        XUI.init(application); //初始化UI框架
        XUI.debug(debug);
        ToastUtils.init(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init(this, isDebug());
    }

    protected boolean isDebug() {
        return false;
    }
}
