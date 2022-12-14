package com.github.log2c.b1lib1li_tv.model;

import androidx.annotation.NonNull;

import com.aleyn.mvvm.base.IBaseResponse;
import com.github.log2c.b1lib1li_tv.common.Constants;

public class BaseModel<T> extends com.aleyn.mvvm.base.BaseModel implements IBaseResponse<T> {
    private int code;
    private String message;
    private int ttl;
    private T data;

    @Override
    public int code() {
        return code;
    }

    @Override
    public T data() {
        return data;
    }

    @Override
    public boolean isSuccess() {
        return code == Constants.RESPONSE_CODE_OK;
    }

    @NonNull
    @Override
    public String msg() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
