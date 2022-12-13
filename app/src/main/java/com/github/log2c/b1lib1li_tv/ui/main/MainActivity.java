package com.github.log2c.b1lib1li_tv.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.aleyn.mvvm.base.BaseVMActivity;
import com.aleyn.mvvm.base.NoViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.ActivityMainBinding;
import com.github.log2c.b1lib1li_tv.ui.dynamic.DynamicActivity;
import com.github.log2c.b1lib1li_tv.ui.login.LoginActivity;

public class MainActivity extends BaseVMActivity<NoViewModel, ActivityMainBinding> {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        Glide.with(this).load(R.drawable.ic_avatar_bilibili).transform(new CircleCrop()).into(mBinding.ivAvatar);

        mBinding.btLogin.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        mBinding.cvDynamic.setOnClickListener(v -> startActivity(new Intent(this, DynamicActivity.class)));

//        mBinding.cvFavor.setOnClickListener(v -> startActivity(new Intent(this, DynamicActivity.class)));
    }
}