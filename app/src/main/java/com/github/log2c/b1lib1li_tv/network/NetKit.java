package com.github.log2c.b1lib1li_tv.network;

import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetKit {
    private static final String TAG = NetKit.class.getSimpleName();
    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1 Safari/605.1.15";
    private static HttpClient mHttpClient;
    private static NetKit mInstance;
    private static final Map<String, String> COMMON_HEADERS = new HashMap<>();

    static {
        COMMON_HEADERS.put("User-Agent", DEFAULT_USER_AGENT);
    }

    private NetKit() {
        init();
    }

    private void init() {
        mHttpClient = getHttpClient();
    }

    public static NetKit getInstance() {
        if (mInstance == null) {
            mInstance = new NetKit();
        }
        return mInstance;
    }

    public static HttpClient getHttpClient() {
        final DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.addRequestInterceptor(new HttpClientRequestInterceptor());
        httpClient.addResponseInterceptor(HttpClientResponseInterceptor.create());
        return httpClient;
    }

    public Observable<String> doGetWithFormBodyRx(String url, @Nullable Map<String, String> form, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams) {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            final String bodyString = doGetWithFormBody(url, form, headers, queryParams);
            emitter.onNext(bodyString);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public String doGetWithFormBody(String url, @Nullable Map<String, String> form, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams) throws Exception {
        HttpGetWithEntity getWithEntity = new HttpGetWithEntity();

        URI finalUri = addUrlParams(url, queryParams);
        addHeadersByMap(getWithEntity, COMMON_HEADERS);
        addHeadersByMap(getWithEntity, headers);

        final MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        if (form != null) {
            for (String key : form.keySet()) {
                entityBuilder.addTextBody(key, form.get(key));
            }
        }

        getWithEntity.setURI(finalUri);
        getWithEntity.setEntity(entityBuilder.build());
        final HttpResponse response;
        response = mHttpClient.execute(getWithEntity);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    public Observable<String> doGetRx(String url, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams) {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            final String bodyString = doGet(url, headers, queryParams);
            emitter.onNext(bodyString);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public String doGet(String url, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams) throws Exception {
        final HttpGet httpGet = new HttpGet(url);

        URI finalUri = addUrlParams(url, queryParams);
        addHeadersByMap(httpGet, COMMON_HEADERS);
        addHeadersByMap(httpGet, headers);

        httpGet.setURI(finalUri);

        final HttpResponse response;
        response = mHttpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    private URI addUrlParams(String originUrl, Map<String, String> queryParams) {
        try {
            final URIBuilder builder = new URIBuilder(originUrl);
            if (queryParams != null) {
                for (String key : queryParams.keySet()) {
                    builder.addParameter(key, queryParams.get(key));
                }
            }
            return builder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addHeadersByMap(HttpMessage httpMessage, Map<String, String> headers) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpMessage.setHeader(key, headers.get(key));
            }
        }
    }
}
