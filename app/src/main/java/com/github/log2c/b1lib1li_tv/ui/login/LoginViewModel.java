package com.github.log2c.b1lib1li_tv.ui.login;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.blankj.utilcode.util.ActivityUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.model.GenerateModel;
import com.github.log2c.b1lib1li_tv.model.LoginModel;
import com.github.log2c.b1lib1li_tv.network.BilibiliThrowable;
import com.github.log2c.b1lib1li_tv.network.LocalObserver;
import com.github.log2c.b1lib1li_tv.repository.LoginRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.LoginRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.utils.Logging;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LoginViewModel extends BaseCoreViewModel {
    private final LoginRepository mLoginRepository;
    public MutableLiveData<GenerateModel> qrcodeRefreshEvent = new SingleLiveEvent<>();
    public MutableLiveData<Integer> qrcodeCodeEvent = new SingleLiveEvent<>();
    private Disposable pollSubscribe;


    public LoginViewModel() {
        mLoginRepository = new LoginRepositoryImpl();
    }

    public void genQrcode() {
        mLoginRepository.getQrcode().subscribe(new LocalObserver<GenerateModel>() {
            @Override
            public void onSuccess(GenerateModel response) {
                qrcodeRefreshEvent.setValue(response);
                startLoginPoll();
            }

            @Override
            public void onException(Throwable e) {
                stopLoginPoll();
                if (e instanceof BilibiliThrowable) {
                    showErrorToast(e.getMessage());
                } else {
                    showErrorToast(e.toString());
                }
            }
        });
    }

    private void stopLoginPoll() {
        if (pollSubscribe != null && !pollSubscribe.isDisposed()) {
            pollSubscribe.dispose();
        }
    }

    @SuppressLint("CheckResult")
    private void startLoginPoll() {
        Logging.i("检查扫码状态 开始轮询");
        stopLoginPoll();
        pollSubscribe = Observable.interval(3, TimeUnit.SECONDS, Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> loginPoll());
    }

    public void loginPoll() {
        mLoginRepository.loginPoll(Objects.requireNonNull(qrcodeRefreshEvent.getValue()).getQrcodeKey())
                .subscribe(new LocalObserver<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel response) {
                        Logging.i("检查扫码状态: " + response.getCode());
                        qrcodeCodeEvent.postValue(response.getCode());
                        if (response.getCode() == Constants.Login.PASS) {
                            stopLoginPoll();
                            onLoginSuccess(response);
                        }
                    }

                    @Override
                    public void onException(Throwable e) {
                        stopLoginPoll();
                        if (e instanceof BilibiliThrowable) {
                            showErrorToast(e.getMessage());
                        } else {
                            showErrorToast(e.toString());
                        }
                    }
                });
    }

    private void onLoginSuccess(LoginModel response) {
        showSuccessToast(R.string.tip_login_success);
        ActivityUtils.getTopActivity().finish();
    }
}
