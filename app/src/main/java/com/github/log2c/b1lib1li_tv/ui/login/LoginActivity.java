package com.github.log2c.b1lib1li_tv.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.ActivityLoginBinding;
import com.github.log2c.b1lib1li_tv.model.GenerateModel;
import com.github.log2c.base.base.BaseCoreActivity;
import com.github.log2c.base.utils.Logging;

import net.glxn.qrgen.android.QRCode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseCoreActivity<LoginViewModel, ActivityLoginBinding> implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        mBinding.btRefresh.setOnClickListener(this);
        viewModel.qrcodeRefreshEvent.observe(this, this::loadQrcode);
        viewModel.qrcodeCodeEvent.observe(this, code -> {

        });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private void loadQrcode(GenerateModel model) {
        Observable.just(mBinding.ivQrcode.getWidth())
                .subscribeOn(Schedulers.newThread())
                .map(size -> QRCode.from(model.getUrl()).withSize(size, size).bitmap())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> Glide.with(LoginActivity.this).load(bitmap).into(mBinding.ivQrcode),
                        throwable -> Logging.e(throwable.toString()));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_refresh) {
            viewModel.genQrcode();
        }
    }

}