package com.github.log2c.b1lib1li_tv.repository.impl;

import com.github.log2c.b1lib1li_tv.network.NetKit;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.LoginRepository;

import java.util.HashMap;

import io.reactivex.Observable;

public class LoginRepositoryImpl implements LoginRepository {
    private static final String TAG = LoginRepositoryImpl.class.getSimpleName();

    @Override
    public Observable<String> getQrcode() {
        return NetKit.getInstance().doGetRx(Urls.GENERATE_QRCODE, null, null);
    }

    @Override
    public Observable<String> loginPoll(String qrcodeKey) {
        final HashMap<String, String> form = new HashMap<>();
        form.put("qrcode_key", qrcodeKey);
        return NetKit.getInstance().doGetWithFormBodyRx(Urls.LOGIN, form, null, null);
    }

}
