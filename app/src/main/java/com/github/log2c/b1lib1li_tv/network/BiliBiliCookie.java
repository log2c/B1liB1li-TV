package com.github.log2c.b1lib1li_tv.network;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.base.utils.Logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class BiliBiliCookie implements CookieJar {
    private static final String TAG = BiliBiliCookie.class.getSimpleName();
    private static BiliBiliCookie instance;
    private static final String REGEX = "(?<=SESSDATA\\=)[^;]+";
    private static SPUtils spUtils;
    private String mSessdata;

    private BiliBiliCookie() {
        spUtils = SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API);
        mSessdata = spUtils.getString(Constants.SP_BILIBILI_API_SESSDATA, "");
    }

    public static BiliBiliCookie create() {
        if (instance == null) {
            instance = new BiliBiliCookie();
        }
        return instance;
    }

    @NonNull
    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
        if (httpUrl.host().contains("bilibili.com")) {
            final Cookie sessdata = new Cookie.Builder()
                    .domain("bilibili.com")
                    .name("SESSDATA")
                    .value(mSessdata)
                    .build();
            return Collections.singletonList(sessdata);
        }
        return new ArrayList<>();
    }

    @Override
    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
        if (httpUrl.toString().endsWith(Urls.LOGIN)) {
            Logging.i("Login, Save SESSDATA.");
            for (Cookie cookie : list) {
                if (cookie.name().equals("Set-Cookie")) {
                    final List<String> matches = RegexUtils.getMatches(REGEX, cookie.value());
                    if (!matches.isEmpty()) {
                        Logging.i("Store cookie: " + matches.get(0));
                        storeSessdata(matches.get(0));
                        break;
                    }
                }
            }
        }
    }

    private void storeSessdata(String data) {
        spUtils.put(Constants.SP_BILIBILI_API_SESSDATA, data);
        mSessdata = data;
    }
}
