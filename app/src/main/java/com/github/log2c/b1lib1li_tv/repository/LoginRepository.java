package com.github.log2c.b1lib1li_tv.repository;

import com.github.log2c.b1lib1li_tv.model.GenerateModel;
import com.github.log2c.b1lib1li_tv.model.LoginModel;

import io.reactivex.Observable;

public interface LoginRepository {
    Observable<GenerateModel> getQrcode();

    Observable<LoginModel> loginPoll(String qrcodeKey);
}
