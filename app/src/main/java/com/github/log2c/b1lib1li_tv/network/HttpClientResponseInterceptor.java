package com.github.log2c.b1lib1li_tv.network;

import android.net.Uri;

import com.blankj.utilcode.util.RegexUtils;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.base.utils.Logging;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.List;

public class HttpClientResponseInterceptor implements HttpResponseInterceptor {
    private static final String SESSDATA_REGEX = "(?<=SESSDATA\\=)[^;]+";
    private static final String DEDE_USER_ID_REGEX = "(?<=DedeUserID\\=)\\d+";
    private static final String CSRF_REGEX = "(?<=bili_jct\\=)[^;]+";
    private static HttpClientResponseInterceptor instance;

    private HttpClientResponseInterceptor() {
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
//            final String hostName = ((HttpHost) context.getAttribute("http.target_host")).getHostName();
            if (Uri.parse(Urls.LOGIN).getPath().equals(((EntityEnclosingRequestWrapper) context.getAttribute("http.request")).getURI().toString())) {
                Logging.i("Login, Save SESSDATA.");
                for (Header header : response.getAllHeaders()) {
                    if (header.getName().equals("Set-Cookie")) {
                        final List<String> sessdataMatches = RegexUtils.getMatches(SESSDATA_REGEX, header.getValue());
                        final List<String> dedeUserIdMatches = RegexUtils.getMatches(DEDE_USER_ID_REGEX, header.getValue());
                        final List<String> csrfMatches = RegexUtils.getMatches(CSRF_REGEX, header.getValue());
                        if (!sessdataMatches.isEmpty()) {
                            Logging.i("Store cookie: " + sessdataMatches.get(0));
                            storeSessdata(sessdataMatches.get(0));
                        }
                        if (!dedeUserIdMatches.isEmpty()) {
                            Logging.i("Store DedeUserId: " + dedeUserIdMatches.get(0));
                            storeDedeUserId(dedeUserIdMatches.get(0));
                        }
                        if (!csrfMatches.isEmpty()) {
                            Logging.i("Store CSRF: " + csrfMatches.get(0));
                            storeCsrf(csrfMatches.get(0));
                        }
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private void storeCsrf(String csrf) {
        AppConfigRepository.getInstance().storeCsrf(csrf);
    }

    private void storeDedeUserId(String userId) {
        AppConfigRepository.getInstance().storeDedeUserId(userId);
    }

    private void storeSessdata(String data) {
        AppConfigRepository.getInstance().storeSessdata(data);
    }

    public String getSessdata() {
        return AppConfigRepository.getInstance().fetchSessdata();
    }

    public String getDedeUserId() {
        return AppConfigRepository.getInstance().fetchDedeUserId();
    }
}
