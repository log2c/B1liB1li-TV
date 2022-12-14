package com.github.log2c.b1lib1li_tv.repository.impl;

import com.blankj.utilcode.util.StringUtils;
import com.github.log2c.b1lib1li_tv.network.NetKit;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Observable<String> getUserStat() {
        return NetKit.getInstance().doGetRx(Urls.NAV_STAT, null, null);
    }

    @Override
    public Observable<String> getNavUserInfo() {
        return NetKit.getInstance().doGetRx(Urls.NAV_USER_INFO, null, null);
    }

    @Override
    public Observable<String> getCoin() {
        return NetKit.getInstance().doGetRx(Urls.GET_COIN, null, null);
    }

    @Override
    public Observable<String> getFeed(String type, int page, String offset) {
        Map<String, String> params = new HashMap<>();
        params.put("timezone_offset", "-480");
        params.put("type", type);
        params.put("page", "" + page);
        if (!StringUtils.isTrimEmpty(offset)) {
            params.put("offset", "" + offset);
        }
        return NetKit.getInstance().doGetRx(Urls.FEED_ALL, null, params);
    }

    @Override
    public Observable<String> getUpFeed(String hostMid, int page, String offset) {
        Map<String, String> params = new HashMap<>();
        params.put("host_mid", hostMid);
        params.put("page", "" + page);
        if (!StringUtils.isTrimEmpty(offset)) {
            params.put("offset", "" + offset);
        }
        return NetKit.getInstance().doGetRx(Urls.FEED_ALL, null, params);
    }

    @Override
    public Observable<String> toView() {
        return NetKit.getInstance().doGetRx(Urls.TO_VIEW, null, null);
    }

    @Override
    public Observable<String> history() {
        return NetKit.getInstance().doGetRx(Urls.HISTORY, null, null);
    }
}
