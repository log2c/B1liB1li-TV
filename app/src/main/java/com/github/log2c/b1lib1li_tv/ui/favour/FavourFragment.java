package com.github.log2c.b1lib1li_tv.ui.favour;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.FragmentFavourBinding;
import com.github.log2c.b1lib1li_tv.model.FavourDetailModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.ui.detail.DetailActivity;
import com.github.log2c.base.base.BaseCoreFragment;
import com.github.log2c.base.toast.ToastUtils;

public class FavourFragment extends BaseCoreFragment<FavourViewModel, FragmentFavourBinding> implements OnItemClickListener {
    public static final String INTENT_ID = "id";
    private ItemAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favour;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ItemAdapter();
        getMBinding().recyclerview.setAdapter(mAdapter);
        getMBinding().recyclerview.setNumColumns(AppConfigRepository.getInstance().getDynamicSpanCount());
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> viewModel.loadDetailData());
        viewModel.favourData.observe(getViewLifecycleOwner(), favourDetailModel -> {
            mAdapter.getLoadMoreModule().loadMoreComplete();
            if (favourDetailModel == null || favourDetailModel.getMedias() == null || favourDetailModel.getMedias().size() == 0) {
                mAdapter.getLoadMoreModule().loadMoreEnd(true);
                return;
            }
            mAdapter.addData(favourDetailModel.getMedias());
            if (favourDetailModel.getHasMore()) {
                mAdapter.getLoadMoreModule().loadMoreEnd(true);
            }
        });
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        if (getArguments() != null) {
            viewModel.mId = getArguments().getLong(INTENT_ID, -1);
            viewModel.loadDetailData();
        }
    }

    public static Fragment newInstance(long mediaId) {
        Bundle intent = new Bundle();
        intent.putLong(INTENT_ID, mediaId);
        FavourFragment fragment = new FavourFragment();
        fragment.setArguments(intent);
        return fragment;
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        FavourDetailModel.MediasModel model = mAdapter.getData().get(position);
        if (model.getAttr() == 1) {
            ToastUtils.error("视频已失效.");
            return;
        }
        DetailActivity.showActivity(requireActivity(), model.getBvid(), "");
    }
}
