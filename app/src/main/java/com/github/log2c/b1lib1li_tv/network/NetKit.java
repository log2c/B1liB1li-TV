package com.github.log2c.b1lib1li_tv.network;

import static com.github.log2c.b1lib1li_tv.common.Constants.DEFAULT_USER_AGENT;
import static com.github.log2c.b1lib1li_tv.common.Constants.REFERER;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.PathUtils;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.base.utils.Logging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cookie.CookieStore;
import rxhttp.wrapper.cookie.ICookieJar;

public class NetKit {
    private static final String TAG = NetKit.class.getSimpleName();
    private static NetKit mInstance;

    private NetKit() {

    }

    public void init() {
        final String networkCacheDir = PathUtils.join(PathUtils.getInternalAppCachePath(), "network");
        boolean orExistsDir = FileUtils.createOrExistsDir(networkCacheDir);
        final File cookiePath = FileUtils.getFileByPath(networkCacheDir);
        Logging.i("缓存目录状态: %1$s, %2$s", networkCacheDir, orExistsDir);
        RxHttpPlugins.init(new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .cookieJar(new CookieStore(cookiePath, false))
                .addInterceptor(new UnzippingInterceptor())
                .build()
        ).setOnParamAssembly(param -> param.addHeader("User-Agent", DEFAULT_USER_AGENT)
                .addHeader("origin", REFERER)
                .addHeader("referer", REFERER));
    }

    public static NetKit getInstance() {
        if (mInstance == null) {
            mInstance = new NetKit();
        }
        return mInstance;
    }

    public String exportCookie() {
        List<String> cookieStr = new ArrayList<>();
        for (Cookie cookie : AppConfigRepository.getInstance().fetchCookies()) {
            cookieStr.add(cookie.toString());
        }
        return GsonUtils.toJson(cookieStr);
    }

    public void importCookie(String json) {
        ICookieJar iCookieJar = (ICookieJar) RxHttpPlugins.getOkHttpClient().cookieJar();

        final String[] cookieStringArrays = GsonUtils.fromJson(json, String[].class);
        List<Cookie> cookies = new ArrayList<>();
        HttpUrl httpUrl = HttpUrl.parse(Urls.LOGIN_DOMAIN);
        for (String s : cookieStringArrays) {
            cookies.add(Cookie.parse(httpUrl, s));
        }
        iCookieJar.saveCookie(httpUrl, cookies);

        for (String domain : Urls.OTHER_DOMAIN_LIST) {
            httpUrl = HttpUrl.parse(domain);
            iCookieJar.saveCookie(httpUrl, cookies);
        }

        AppConfigRepository.getInstance().processCookie();
    }
}
