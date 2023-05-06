package com.github.log2c.b1lib1li_tv.repository.impl;

import com.github.log2c.b1lib1li_tv.model.GenerateModel;
import com.github.log2c.b1lib1li_tv.model.LoginModel;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.LoginRepository;

import io.reactivex.Observable;
import rxhttp.network.RxHttp;

public class LoginRepositoryImpl implements LoginRepository {
    private static final String TAG = LoginRepositoryImpl.class.getSimpleName();

    public Observable<GenerateModel> getQrcode() {
        return RxHttp.get(Urls.GENERATE_QRCODE)
                .toObservableResponse(GenerateModel.class);
    }

    @Override
    public Observable<LoginModel> loginPoll(String qrcodeKey) {
        return RxHttp.get(Urls.LOGIN)
                .addQuery("qrcode_key", qrcodeKey)
                .toObservableResponse(LoginModel.class);
    }

}
