package com.github.log2c.base.toast;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;

import androidx.annotation.StringRes;

import com.xuexiang.xui.widget.toast.XToast;

@SuppressWarnings("unused")
public class ToastUtils {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private ToastUtils() {
    }

    public static void init(Application applicationContext) {
        context = applicationContext;
        XToast.Config.get().setGravity(Gravity.CENTER);
    }

    public static void showLong(String msg) {
        XToast.normal(context, msg).show();
    }

    public static void showLong(@StringRes int id) {
        XToast.normal(context, id).show();
    }

    public static void show(String msg) {
        XToast.normal(context, msg).show();
    }

    public static void show(@StringRes int id) {
        XToast.normal(context, id).show();
    }

    public static void showShort(String msg) {
        XToast.normal(context, msg).show();
    }

    public static void showShort(@StringRes int id) {
        XToast.normal(context, id).show();
    }

    public static void error(String msg) {
        XToast.error(context, msg).show();
    }

    public static void error(@StringRes int id) {
        XToast.error(context, id).show();
    }

    public static void success(String msg) {
        XToast.success(context, msg).show();
    }

    public static void success(@StringRes int id) {
        XToast.success(context, id).show();
    }

    public static void warning(String msg) {
        XToast.warning(context, msg).show();
    }

    public static void warning(@StringRes int id) {
        XToast.warning(context, id).show();
    }

    public static void info(String msg) {
        XToast.info(context, msg).show();
    }

    public static void info(@StringRes int id) {
        XToast.info(context, id).show();
    }

}
