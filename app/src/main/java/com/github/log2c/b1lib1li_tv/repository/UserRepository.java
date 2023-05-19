package com.github.log2c.b1lib1li_tv.repository;

import com.github.log2c.b1lib1li_tv.model.FavourDetailModel;
import com.github.log2c.b1lib1li_tv.model.FavourListModel;
import com.github.log2c.b1lib1li_tv.model.FeedModel;
import com.github.log2c.b1lib1li_tv.model.NavUserInfoModel;
import com.github.log2c.b1lib1li_tv.model.ToViewModel;
import com.github.log2c.b1lib1li_tv.model.UpFeedModel;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<String> getUserStat();

    Observable<NavUserInfoModel> getNavUserInfo();

    Observable<String> getCoin();

    Observable<FeedModel> getFeed(String type, int page, String offset);

    Observable<ToViewModel> toView();

    Observable<String> history();

    Observable<UpFeedModel> getUpFeed(String hostMid, int page, int pageSize);

    Observable<FavourListModel> getFavourList(String up_mid);

    Observable<FavourDetailModel> getFavourDetailList(String mediaId, int ps, int pn);
}
