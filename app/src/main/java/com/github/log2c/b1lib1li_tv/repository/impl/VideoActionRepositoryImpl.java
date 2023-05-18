package com.github.log2c.b1lib1li_tv.repository.impl;

import com.github.log2c.b1lib1li_tv.model.AddTripleModel;
import com.github.log2c.b1lib1li_tv.model.HasAddCoinStateModel;
import com.github.log2c.b1lib1li_tv.model.HasFavourStateModel;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoActionRepository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.network.RxHttp;

public class VideoActionRepositoryImpl implements VideoActionRepository {

    @Override
    public Observable<Boolean> isLike(String aid, String bvid) {
        return RxHttp.get(Urls.HAS_LIKE)
                .addQuery("aid", aid)
                .addQuery("bvid", bvid)
                .toObservableResponse(Integer.class)
                .observeOn(AndroidSchedulers.mainThread())
                .map((a) -> a == 1);
    }

    @Override
    public Observable<Boolean> isAddCoin(String aid, String bvid) {
        return RxHttp.get(Urls.HAS_ADD_COIN)
                .addQuery("aid", aid)
                .addQuery("bvid", bvid)
                .toObservableResponse(HasAddCoinStateModel.class)
                .observeOn(AndroidSchedulers.mainThread())
                .map((a) -> a.getMultiply() != 0);
    }

    @Override
    public Observable<Boolean> isFavoured(String aid, String bvid) {
        return RxHttp.get(Urls.HAS_FAVOUR)
                .addQuery("aid", aid)
                .addQuery("bvid", bvid)
                .toObservableResponse(HasFavourStateModel.class)
                .observeOn(AndroidSchedulers.mainThread())
                .map(HasFavourStateModel::isFavoured);
    }

    @Override
    public Observable<Boolean> like(String aid, String bvid, boolean like) {
        return RxHttp.postForm(Urls.ADD_LIKE)
                .add("aid", aid)
                .add("bvid", bvid)
                .add("csrf", AppConfigRepository.getInstance().fetchCsrf())
                .add("like", like ? 1 : 2)
                .toObservable(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .map((a) -> true);
    }

    @Override
    public Observable<Boolean> addCoin(String aid, String bvid, int multiply) {
        return RxHttp.postForm(Urls.ADD_COIN)
                .add("aid", aid)
                .add("bvid", bvid)
                .add("multiply", multiply)
                .add("csrf", AppConfigRepository.getInstance().fetchCsrf())
                .toObservable(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .map((a) -> true);
    }

    @Override
    public Observable<Boolean> addFavor(String aid, String bvid, long media_id) {
        return RxHttp.postForm(Urls.ADD_FAVOUR)
                .add("rid", aid)
                .add("type", 2)
                .add("add_media_ids", media_id)
                .add("del_media_ids", "")
                .add("csrf", AppConfigRepository.getInstance().fetchCsrf())
                .toObservable(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .map((a) -> true);
    }

    @Override
    public Observable<Boolean> triple(String aid, String bvid) {
        return RxHttp.postForm(Urls.ADD_TRIPLE)
                .add("aid", aid)
                .add("bvid", bvid)
                .add("csrf", AppConfigRepository.getInstance().fetchCsrf())
                .toObservableResponse(AddTripleModel.class)
                .observeOn(AndroidSchedulers.mainThread())
                .map((a) -> true);
    }
}
