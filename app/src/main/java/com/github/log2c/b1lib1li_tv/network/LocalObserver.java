package com.github.log2c.b1lib1li_tv.network;

import com.blankj.utilcode.util.GsonUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.model.BaseModel;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//public abstract class LocalObserver<M, T extends BaseModel<M>> implements Observer<T> {
public abstract class LocalObserver<T> implements Observer<String> {

    public abstract void onSuccess(T model);

    public abstract void onException(Throwable e);

    @Override
    public void onSubscribe(Disposable d) {

    }

    public boolean isCodeError(int code) {
        return code != Constants.RESPONSE_CODE_OK;
    }

    @Override
    public void onNext(String json) {
        TypeToken<?> typeToken = getCompactTypeDynamic();
        if (typeToken != null) {
            BaseModel<T> model = GsonUtils.fromJson(json, typeToken.getType());
            if (!isCodeError(model.code())) {
                onSuccess(model.data());
            } else {
                onException(new BilibiliThrowable(model.getMessage()));
            }
        } else {
            onException(new BilibiliThrowable("TypeToken exception."));
        }
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public void onNext(BaseModel res) {
//        if (!isCodeError(res.code())) {
//            onSuccess((T) res.data());
//        } else {
//            onException(new BilibiliThrowable(res.getMessage()));
//        }
//    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!(e instanceof BilibiliThrowable)) {
            onException(e);
        }
    }

    @Override
    public void onComplete() {

    }

    private TypeToken<?> getCompactTypeDynamic() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        if (types.length == 0) {
            return null;
        }
        try {
            if (TypeToken.get(types[0]).getRawType().equals(List.class)) {
                ParameterizedType type = (ParameterizedType) types[0];
                Type actualTypeArgument = type.getActualTypeArguments()[0];
                return TypeToken.getParameterized(BaseModel.class, TypeToken.getParameterized(List.class, actualTypeArgument).getType());
            } else {
                return TypeToken.getParameterized(BaseModel.class, types[0]);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
