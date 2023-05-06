package com.github.log2c.b1lib1li_tv.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BackendObserver<T> implements Observer<T> {
    public abstract void onSuccess(T model);

    public abstract void onFinish();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
