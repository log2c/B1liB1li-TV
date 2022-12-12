package com.github.log2c.b1lib1li_tv.api;

import com.github.log2c.b1lib1li_tv.model.BaseModel;
import com.github.log2c.b1lib1li_tv.model.GenerateModel;
import com.github.log2c.b1lib1li_tv.model.LoginModel;
import com.github.log2c.b1lib1li_tv.network.Urls;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Apis {

    @GET(Urls.GENERATE_QRCODE)
    Observable<BaseModel<GenerateModel>> generateQrcode();

    @GET(Urls.LOGIN)
        //    @HTTP(method = "GET", path = Urls.LOGIN, hasBody = true)
    Observable<BaseModel<LoginModel>> loginPoll();
}
