package com.github.log2c.b1lib1li_tv.network;

import com.github.log2c.b1lib1li_tv.api.Apis;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("InstantiationOfUtilityClass")
public class RetrofitClient {
    private static final String TAG = RetrofitClient.class.getSimpleName();
    private static final String BASE_API_URL = "http://api.bilibili.com/";
    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;

    private RetrofitClient() {
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //适配RxJava2.0, RxJava1.x则为RxJavaCallAdapterFactory.create()
                .baseUrl(BASE_API_URL)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(200L, TimeUnit.SECONDS)
                .connectTimeout(200L, TimeUnit.SECONDS)
                .writeTimeout(200L, TimeUnit.SECONDS)
                .cookieJar(BiliBiliCookie.create())
//                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .addInterceptor(new HttpLoggingInterceptor()).build();
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public Apis getApis() {
        return retrofit.create(Apis.class);
    }

}
