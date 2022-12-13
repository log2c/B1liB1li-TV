package com.github.log2c.b1lib1li_tv.network;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.base.utils.Logging;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.List;

public class HttpClientResponseInterceptor implements HttpResponseInterceptor {
    private static final String SESSDATA_REGEX = "(?<=SESSDATA\\=)[^;]+";
    private static final String DEDE_USER_ID_REGEX = "(?<=DedeUserID\\=)\\d+";
    private static SPUtils spUtils;
    private static HttpClientResponseInterceptor instance;
    private String mSessdata;
    private String mDedeUserId;

    private HttpClientResponseInterceptor() {
        spUtils = SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API);
        mSessdata = spUtils.getString(Constants.SP_BILIBILI_API_SESSDATA, "");
        mDedeUserId = spUtils.getString(Constants.SP_BILIBILI_API_DEDEUSERID, "");
    }

    public static HttpClientResponseInterceptor create() {
        if (instance == null) {
            instance = new HttpClientResponseInterceptor();
        }
        return instance;
    }

    @Override
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        try {
            final String url = ((HttpHost) context.getAttribute("http.target_host")).getHostName();
            if (Urls.LOGIN.contains(url)) {
                Logging.i("Login, Save SESSDATA.");
                for (Header header : response.getAllHeaders()) {
                    if (header.getName().equals("Set-Cookie")) {
                        final List<String> sessdataMatches = RegexUtils.getMatches(SESSDATA_REGEX, header.getValue());
                        final List<String> dedeUserIdMatches = RegexUtils.getMatches(DEDE_USER_ID_REGEX, header.getValue());
                        if (!sessdataMatches.isEmpty()) {
                            Logging.i("Store cookie: " + sessdataMatches.get(0));
                            storeSessdata(sessdataMatches.get(0));
                        }
                        if (!dedeUserIdMatches.isEmpty()) {
                            Logging.i("Store DedeUserId: " + dedeUserIdMatches.get(0));
                            storeDedeUserId(dedeUserIdMatches.get(0));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void storeDedeUserId(String userId) {
        spUtils.put(Constants.SP_BILIBILI_API_DEDEUSERID, userId);
        mDedeUserId = userId;
    }

    private void storeSessdata(String data) {
        spUtils.put(Constants.SP_BILIBILI_API_SESSDATA, data);
        mSessdata = data;
    }

    public String getSessdata() {
        return mSessdata;
    }

    public String getDedeUserId() {
        return mDedeUserId;
    }
}
