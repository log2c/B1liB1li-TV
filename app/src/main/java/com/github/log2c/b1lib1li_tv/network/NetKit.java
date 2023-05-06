package com.github.log2c.b1lib1li_tv.network;

import static com.github.log2c.b1lib1li_tv.common.Constants.DEFAULT_USER_AGENT;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.github.log2c.b1lib1li_tv.common.Constants;

import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NetKit {
    private static final String TAG = NetKit.class.getSimpleName();
    private static HttpClient mHttpClient;
    private static NetKit mInstance;
    public static final Map<String, String> COMMON_HEADERS = new HashMap<>();

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
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwableObservable -> throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    int mRetryCount = 0;

                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        long waitTime = 0;
                        if (throwable instanceof IOException) {
                            waitTime = 2000;
                        }
                        Log.d(TAG, "发生错误，尝试等待时间=" + waitTime + ",当前重试次数=" + mRetryCount);
                        mRetryCount++;
                        return waitTime > 0 && mRetryCount <= Constants.NETWORK_REQUEST_RETRY_COUNT ? Observable.timer(waitTime, TimeUnit.MILLISECONDS) : Observable.error(throwable);
                    }
                }));
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
        String responseString;
        Header[] responseHeaders = response.getHeaders("Content-Encoding");
        boolean isGzip = false;
        boolean isDeflate = false;
        for (Header h : responseHeaders) {
            if (h.getValue().equals("gzip")) {
                isGzip = true;
            } else if (h.getValue().equals("deflate")) {
                isDeflate = true;
            }
        }
        if (isGzip) {
            responseString = EntityUtils.toString(new GzipDecompressingEntity(response.getEntity()), "UTF-8");
        } else if (isDeflate) {
            responseString = EntityUtils.toString(new DeflateDecompressingEntity(response.getEntity()), "UTF-8");
        } else {
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return responseString;
    }

    public Observable<String> doGetRx(String url, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams) {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    final String bodyString = doGet(url, headers, queryParams);
                    emitter.onNext(bodyString);
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwableObservable -> throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    int mRetryCount = 0;

                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        long waitTime = 0;
                        if (throwable instanceof IOException) {
                            waitTime = 2000;
                        }
                        Log.d(TAG, "发生错误，尝试等待时间=" + waitTime + ",当前重试次数=" + mRetryCount);
                        mRetryCount++;
                        return waitTime > 0 && mRetryCount <= Constants.NETWORK_REQUEST_RETRY_COUNT ? Observable.timer(waitTime, TimeUnit.MILLISECONDS) : Observable.error(throwable);
                    }
                }));
    }

    public Observable<String> doPostRx(String url, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams, @Nullable Map<String, String> formData) {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    final String bodyString = doPost(url, headers, queryParams, formData);
                    emitter.onNext(bodyString);
                    emitter.onComplete();
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwableObservable -> throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    int mRetryCount = 0;

                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        long waitTime = 0;
                        if (throwable instanceof IOException) {
                            waitTime = 2000;
                        }
                        Log.d(TAG, "发生错误，尝试等待时间=" + waitTime + ",当前重试次数=" + mRetryCount);
                        mRetryCount++;
                        return waitTime > 0 && mRetryCount <= Constants.NETWORK_REQUEST_RETRY_COUNT ? Observable.timer(waitTime, TimeUnit.MILLISECONDS) : Observable.error(throwable);
                    }
                }));
    }

    public String doGet(String url, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams) throws Exception {
        final HttpGet httpGet = new HttpGet(url);

        URI finalUri = addUrlParams(url, queryParams);
        addHeadersByMap(httpGet, COMMON_HEADERS);
        addHeadersByMap(httpGet, headers);

        httpGet.setURI(finalUri);

        final HttpResponse response;
        response = mHttpClient.execute(httpGet);
        String responseString;
        Header[] responseHeaders = response.getHeaders("Content-Encoding");
        boolean isGzip = false;
        boolean isDeflate = false;
        for (Header h : responseHeaders) {
            if (h.getValue().equals("gzip")) {
                isGzip = true;
            } else if (h.getValue().equals("deflate")) {
                isDeflate = true;
            }
        }
        if (isGzip) {
            responseString = EntityUtils.toString(new GzipDecompressingEntity(response.getEntity()), "UTF-8");
        } else if (isDeflate) {
            responseString = EntityUtils.toString(new DeflateDecompressingEntity(response.getEntity()), "UTF-8");
        } else {
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return responseString;
    }

    public String doPost(String url, @Nullable Map<String, String> headers, @Nullable Map<String, String> queryParams, @Nullable Map<String, String> formData) throws Exception {
        final HttpPost httpPost = new HttpPost(url);

        URI finalUri = addUrlParams(url, queryParams);
        addHeadersByMap(httpPost, COMMON_HEADERS);
        addHeadersByMap(httpPost, headers);
        httpPost.setURI(finalUri);

        List<NameValuePair> forms = new ArrayList<>();
        if (formData != null) {
            for (String key : formData.keySet()) {
                forms.add(new NameValuePair() {
                    @Override
                    public String getName() {
                        return key;
                    }

                    @Override
                    public String getValue() {
                        return formData.get(key);
                    }
                });
            }
        }

        httpPost.setEntity(new UrlEncodedFormEntity(forms));

        final HttpResponse response;
        response = mHttpClient.execute(httpPost);
        String responseString;
        Header[] responseHeaders = response.getHeaders("Content-Encoding");
        boolean isGzip = false;
        boolean isDeflate = false;
        for (Header h : responseHeaders) {
            if (h.getValue().equals("gzip")) {
                isGzip = true;
            } else if (h.getValue().equals("deflate")) {
                isDeflate = true;
            }
        }
        if (isGzip) {
            responseString = EntityUtils.toString(new GzipDecompressingEntity(response.getEntity()), "UTF-8");
        } else if (isDeflate) {
            responseString = EntityUtils.toString(new DeflateDecompressingEntity(response.getEntity()), "UTF-8");
        } else {
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return responseString;
    }

    private URI addUrlParams(String originUrl, Map<String, String> queryParams) {
        final Uri.Builder builder = Uri.parse(originUrl).buildUpon();
        if (queryParams != null) {
            for (String key : queryParams.keySet()) {
                builder.appendQueryParameter(key, queryParams.get(key));
            }
        }
        return URI.create((builder.build().toString()));
    }

    private void addHeadersByMap(HttpMessage httpMessage, Map<String, String> headers) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpMessage.setHeader(key, headers.get(key));
            }
        }
    }
}
