package com.github.log2c.b1lib1li_tv.ui.clips.relation;

import android.annotation.SuppressLint;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.RelationDetailModel;
import com.github.log2c.b1lib1li_tv.model.RelationTagModel;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.toast.ToastUtils;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class RelationViewModel extends BaseCoreViewModel {
    private static final String ORDER_TYPE_DEFAULT = "";
    private static final String ORDER_TYPE_ATTENTION = "attention";
    public long tagId = -1;
    private UserRepository mUserRepository;
    public static final int PAGE_SIZE = 50;
    private int mPage = 1;
    private String mOrderType = ORDER_TYPE_ATTENTION;
    public SingleLiveEvent<List<RelationDetailModel>> relationData = new SingleLiveEvent<>();
    public SingleLiveEvent<List<RelationTagModel>> relationTags = new SingleLiveEvent<>();
    private List<RelationTagModel> mRelationTags = new ArrayList<>();

    public RelationViewModel() {
        mUserRepository = new UserRepositoryImpl();
    }


    public void fetchRelationList() {
        mUserRepository.getRelationTagDetail(tagId, mOrderType, PAGE_SIZE, mPage++)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> relationData.setValue(list), e -> {
                    e.printStackTrace();
                    mPage--;
                    relationData.setValue(null);
                });
    }

    public List<ImmutablePair<String, Integer>> getRelationTags() {
        List<ImmutablePair<String, Integer>> data = new ArrayList<>();
        for (RelationTagModel model : mRelationTags) {
            data.add(new ImmutablePair<>(model.getName(), model.getTagId()));
        }
        return data;
    }

    public void fetchRelationTags() {
        mUserRepository.getRelationTags()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    if (list != null) {
                        mRelationTags.addAll(list);
                        relationTags.postValue(list);
                    }
                }, e -> {
                    e.printStackTrace();
                    ToastUtils.error("获取分组时发生错误");
                });
    }

}
