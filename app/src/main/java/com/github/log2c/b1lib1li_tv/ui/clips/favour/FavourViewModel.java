package com.github.log2c.b1lib1li_tv.ui.clips.favour;


import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.FavourDetailModel;
import com.github.log2c.b1lib1li_tv.model.FavourListModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.toast.ToastUtils;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;

@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class FavourViewModel extends BaseCoreViewModel {
    private final UserRepository mUserRepository;
    private final Map<Long, FavourListModel.ListModel> mFavourModelMap = new HashMap<>();
    public final SingleLiveEvent<FavourDetailModel> favourData = new SingleLiveEvent<>();
    public SingleLiveEvent<List<ImmutablePair<String, Long>>> favourListEvent = new SingleLiveEvent<>();
    private FavourListModel mFavourListModel;
    public static final int PAGE_SIZE = 20;
    private int page = 1;
    public long mId;

    public FavourViewModel() {
        mUserRepository = new UserRepositoryImpl();
    }

    public void fetchFavourList() {
        mUserRepository.getFavourList(AppConfigRepository.getInstance().fetchUserMid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modeList -> {
                    if (modeList == null || modeList.getList() == null || modeList.getList().size() == 0) {
                        ToastUtils.warning("收藏夹为空或找不到数据.");
                        return;
                    }
                    for (FavourListModel.ListModel model : modeList.getList()) {
                        mFavourModelMap.put(model.getId(), model);
                    }
                    mFavourListModel = modeList;
                    favourListEvent.setValue(getFavourCategory());
                }, e -> {
                    e.printStackTrace();
                    ToastUtils.error("获取收藏夹信息时发生错误.");
                });
    }

    @Nullable
    public List<ImmutablePair<String, Long>> getFavourCategory() {
        if (mFavourListModel == null || mFavourListModel.getList() == null || mFavourListModel.getList().size() == 0) {
            return null;
        }
        List<ImmutablePair<String, Long>> data = new ArrayList<>();
        for (FavourListModel.ListModel model : mFavourListModel.getList()) {
            data.add(new ImmutablePair<>(model.getTitle(), model.getId()));
        }
        return data;
    }

    public void loadDetailData() {
        mUserRepository.getFavourDetailList(mId + "", PAGE_SIZE, page++)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favourData::setValue, e -> {
                    e.printStackTrace();
                    ToastUtils.error("数据错误.");
                    page--;
                });
    }
}
