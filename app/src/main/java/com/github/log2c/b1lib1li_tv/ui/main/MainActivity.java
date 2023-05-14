package com.github.log2c.b1lib1li_tv.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.aleyn.mvvm.base.BaseVMActivity;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.ActivityMainBinding;
import com.github.log2c.b1lib1li_tv.ui.dynamic.DynamicActivity;
import com.github.log2c.b1lib1li_tv.ui.login.LoginActivity;
import com.github.log2c.b1lib1li_tv.ui.setting.SettingsActivity;
import com.github.log2c.b1lib1li_tv.ui.toview.ToviewActivity;

public class MainActivity extends BaseVMActivity<MainViewModel, ActivityMainBinding> implements View.OnFocusChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.fetchUserInfo();
    }

    @Override
    public void initData() {
        viewModel.checkIsLogin();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        Glide.with(this).load(R.drawable.ic_avatar_bilibili).transform(new CircleCrop()).into(mBinding.ivAvatar);

        mBinding.btLogin.setOnClickListener(v -> navLogin());
        mBinding.btnSetting.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        mBinding.cvDynamic.setOnClickListener(v -> startActivity(new Intent(this, DynamicActivity.class)));
        mBinding.cvToview.setOnClickListener(v -> startActivity(new Intent(this, ToviewActivity.class)));

        viewModel.navUserInfoEvent.observe(this, model -> {
            Glide.with(this).load(model.getFace()).transform(new CircleCrop()).into(mBinding.ivAvatar);
            mBinding.btLogin.setText(model.getUname());
        });

        mBinding.cvToview.setOnFocusChangeListener(this);
        mBinding.cvDynamic.setOnFocusChangeListener(this);
        mBinding.cvHistory.setOnFocusChangeListener(this);

        viewModel.unLoginEvent.observe(this, unused -> navLogin());
    }

    private void navLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v instanceof CardView) {
            CardView card = (CardView) v;
            if (hasFocus) {
                card.setScaleX(1.2f);
                card.setScaleY(1.2f);
                card.setCardElevation(ConvertUtils.dp2px(15));
            } else {
                card.setCardElevation(0f);
                card.setScaleX(1f);
                card.setScaleY(1f);
            }
        }
    }
}