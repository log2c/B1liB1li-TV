package com.github.log2c.b1lib1li_tv.repository;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<String> getUserStat();

    Observable<String> getNavUserInfo();

    Observable<String> getCoin();

    Observable<String> getFeed(String type, int page, String offset);

    Observable<String> toView();

    Observable<String> history();
}
