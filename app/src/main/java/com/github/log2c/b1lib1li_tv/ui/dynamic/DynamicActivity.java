package com.github.log2c.b1lib1li_tv.ui.dynamic;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.adapter.FeedAdapter;
import com.github.log2c.b1lib1li_tv.databinding.ActivityDynamicBinding;
import com.github.log2c.b1lib1li_tv.model.FeedModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.ui.detail.DetailActivity;
import com.github.log2c.base.base.BaseCoreActivity;

import java.util.ArrayList;

public class DynamicActivity extends BaseCoreActivity<DynamicViewModel, ActivityDynamicBinding> implements OnItemClickListener {
    private FeedAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dynamic;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        mAdapter = new FeedAdapter();
        mBinding.recyclerview.setLayoutManager(new GridLayoutManager(this, AppConfigRepository.getInstance().getDynamicSpanCount()));
        mBinding.recyclerview.setAdapter(mAdapter);
        mAdapter.setNewInstance(new ArrayList<>());
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> viewModel.loadFeedInfo());
        mAdapter.setOnItemClickListener(this);
        mAdapter.setEmptyView(R.layout.layout_empty);
        viewModel.feedModelEvent.observe(this, feedModel -> {
            if (feedModel == null) {
                mAdapter.getLoadMoreModule().loadMoreFail();
                return;
            }
            mAdapter.addData(feedModel.getItems());
            mAdapter.getLoadMoreModule().loadMoreComplete();
            mAdapter.getLoadMoreModule().setEnableLoadMore(feedModel.getHas_more());
        });
        viewModel.refreshEvent.observe(this, s -> {
            mAdapter.setNewInstance(new ArrayList<>());
        });

        viewModel.loadFeedInfo();
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        final FeedModel.ItemsBean model = mAdapter.getData().get(position);
        DetailActivity.showActivity(this, model.getBvid(), model.getAid());
    }
}
