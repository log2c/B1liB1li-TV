package com.github.log2c.b1lib1li_tv.ui.login;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.blankj.utilcode.util.ActivityUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.model.GenerateModel;
import com.github.log2c.b1lib1li_tv.model.LoginModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.LoginRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.LoginRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.utils.Logging;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cookie.ICookieJar;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LoginViewModel extends BaseCoreViewModel {
    private static final long POLL_PERIOD_SECONDS = 5;  // 轮询登陆状态间隔
    private final LoginRepository mLoginRepository;
    public MutableLiveData<GenerateModel> qrcodeRefreshEvent = new SingleLiveEvent<>();
    public MutableLiveData<Integer> qrcodeCodeEvent = new SingleLiveEvent<>();
    private Disposable pollSubscribe;


    public LoginViewModel() {
        mLoginRepository = new LoginRepositoryImpl();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        stopLoginPoll();
    }

    public void genQrcode() {
        mLoginRepository.getQrcode()
                .subscribe(new BackendObserver<GenerateModel>() {
                    @Override
                    public void onSuccess(GenerateModel model) {
                        qrcodeRefreshEvent.postValue(model);
                        startLoginPoll();
                    }

                    @Override
                    public void onFinish() {

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
        pollSubscribe = Observable.interval(POLL_PERIOD_SECONDS, TimeUnit.SECONDS, Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> loginPoll());
    }

    public void loginPoll() {
        mLoginRepository.loginPoll(Objects.requireNonNull(qrcodeRefreshEvent.getValue()).getQrcodeKey())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BackendObserver<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel model) {
                        Logging.i("检查扫码状态: " + model.getCode());
                        qrcodeCodeEvent.setValue(model.getCode());
                        if (model.getCode() == Constants.Login.PASS) {
                            stopLoginPoll();
                            onLoginSuccess();
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    private void onLoginSuccess() {
        processCookie();

        showSuccessToast(R.string.tip_login_success);
        ActivityUtils.getTopActivity().finish();
    }

    private void processCookie() {
        ICookieJar iCookieJar = (ICookieJar) RxHttpPlugins.getOkHttpClient().cookieJar();
        final List<Cookie> cookies = AppConfigRepository.getInstance().fetchCookies();
        for (String domain : Urls.OTHER_DOMAIN_LIST) {
            final HttpUrl httpUrl = HttpUrl.parse(domain);
            iCookieJar.saveCookie(httpUrl, cookies);
        }
        Logging.i("Cookie跨域处理完成");
    }
}
