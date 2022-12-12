package com.github.log2c.base.base;

import androidx.annotation.StringRes;

import com.aleyn.mvvm.base.BaseViewModel;
import com.github.log2c.base.toast.ToastUtils;

@SuppressWarnings({"unused", "SameParameterValue"})
public class BaseCoreViewModel extends BaseViewModel {

    public void showErrorToast(String message) {
        ToastUtils.error(message);
    }

    public void showErrorToast(@StringRes int message) {
        ToastUtils.error(message);
    }

    public void showSuccessToast(String message) {
        ToastUtils.success(message);
    }

    public void showSuccessToast(@StringRes int message) {
        ToastUtils.success(message);
    }
}