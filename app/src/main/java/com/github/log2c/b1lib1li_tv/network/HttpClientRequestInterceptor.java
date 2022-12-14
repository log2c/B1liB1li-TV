package com.github.log2c.b1lib1li_tv.network;

import android.net.Uri;

import com.blankj.utilcode.util.StringUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpClientRequestInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        try {
            if (StringUtils.isTrimEmpty(AppConfigRepository.getInstance().fetchSessdata()) || StringUtils.isTrimEmpty(AppConfigRepository.getInstance().fetchDedeUserId())) {
                return;
            }
            final String url = ((HttpHost) context.getAttribute("http.target_host")).getHostName();
            for (String allowDomain : Constants.COOKIE_ALLOWED_LIST) {
                if (Uri.parse(url).toString().equals(allowDomain)) {
                    request.setHeader("Cookie", "SESSDATA=" + AppConfigRepository.getInstance().fetchSessdata() + ";DedeUserID=" + AppConfigRepository.getInstance().fetchDedeUserId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
