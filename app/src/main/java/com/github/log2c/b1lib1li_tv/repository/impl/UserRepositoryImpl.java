package com.github.log2c.b1lib1li_tv.repository.impl;

import com.blankj.utilcode.util.StringUtils;
import com.github.log2c.b1lib1li_tv.model.FavourDetailModel;
import com.github.log2c.b1lib1li_tv.model.FavourListModel;
import com.github.log2c.b1lib1li_tv.model.FeedModel;
import com.github.log2c.b1lib1li_tv.model.NavUserInfoModel;
import com.github.log2c.b1lib1li_tv.model.ToViewModel;
import com.github.log2c.b1lib1li_tv.model.UpFeedModel;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.network.RxHttp;
import rxhttp.network.RxHttpNoBodyParam;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Observable<String> getUserStat() {
        return RxHttp.get(Urls.NAV_STAT)
                .toObservableResponse(String.class);
    }

    @Override
    public Observable<NavUserInfoModel> getNavUserInfo() {
        return RxHttp.get(Urls.NAV_USER_INFO)
                .toObservableResponse(NavUserInfoModel.class)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getCoin() {
        return RxHttp.get(Urls.GET_COIN)
                .toObservableResponse(String.class);

    }

    @Override
    public Observable<FeedModel> getFeed(String type, int page, String offset) {
        RxHttpNoBodyParam req = RxHttp.get(Urls.FEED_VIDEO)
                .addQuery("page", page);
        if (!StringUtils.isTrimEmpty(offset)) {
            req.addQuery("offset", "" + offset);
        }
        return req.toObservableResponse(FeedModel.class)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UpFeedModel> getUpFeed(String hostMid, int page, int pageSize) {
        return RxHttp.get(Urls.SPACE_SEARCH_VIDEOS)
                .addQuery("mid", hostMid)
                .addQuery("pn", "" + page)
                .addQuery("ps", pageSize + "")
                .toObservableResponse(UpFeedModel.class)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<FavourListModel> getFavourList(String up_mid) {
        return RxHttp.get(Urls.CREATED_FAVOUR_LIST)
                .addQuery("up_mid", up_mid)
                .toObservableResponse(FavourListModel.class)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<FavourDetailModel> getFavourDetailList(String mediaId, int ps, int pn) {
        return RxHttp.get(Urls.FAVOUR_ALL_BY_ID)
                .addQuery("media_id", mediaId)
                .addQuery("ps", ps)
                .addQuery("pn", pn)
                .addQuery("type", 0)
                .toObservableResponse(FavourDetailModel.class)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ToViewModel> toView() {
        return RxHttp.get(Urls.TO_VIEW)
                .toObservableResponse(ToViewModel.class)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> history() {
        return RxHttp.get(Urls.HISTORY)
                .toObservableResponse(String.class);
    }
}
