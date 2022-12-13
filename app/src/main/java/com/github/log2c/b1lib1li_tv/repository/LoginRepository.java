package com.github.log2c.b1lib1li_tv.repository;

import io.reactivex.Observable;

public interface LoginRepository {
    Observable<String> getQrcode();

    Observable<String> loginPoll(String qrcodeKey);
}
