package com.github.log2c.b1lib1li_tv.repository;

import io.reactivex.Observable;

public interface LoginRepository {
//    Observable<GenerateModel> getQrcode();
//
//    Observable<LoginModel> loginPoll(String qrcodeKey);

    Observable<String> getQrcode();

    Observable<String> loginPoll(String qrcodeKey);
}
