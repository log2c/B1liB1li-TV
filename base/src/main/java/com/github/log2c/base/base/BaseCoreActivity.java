package com.github.log2c.base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.aleyn.mvvm.base.BaseVMActivity;

@SuppressWarnings("unused")
public abstract class BaseCoreActivity<VM extends BaseCoreViewModel, VB extends ViewBinding> extends BaseVMActivity<VM, VB> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
