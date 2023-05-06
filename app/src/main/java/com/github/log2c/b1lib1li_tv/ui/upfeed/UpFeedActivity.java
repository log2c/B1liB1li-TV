package com.github.log2c.b1lib1li_tv.ui.upfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.adapter.UpFeedAdapter;
import com.github.log2c.b1lib1li_tv.databinding.ActivityUpFeedBinding;
import com.github.log2c.b1lib1li_tv.model.UpFeedModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.ui.detail.DetailActivity;
import com.github.log2c.base.base.BaseCoreActivity;

import java.util.ArrayList;

public class UpFeedActivity extends BaseCoreActivity<UpFeedViewModel, ActivityUpFeedBinding> implements OnItemClickListener {
    private static final String TAG = UpFeedActivity.class.getSimpleName();
    private UpFeedAdapter mAdapter;
    public static final String INTENT_HOST_MID = "hostMid";

    @Override
    public int getLayoutId() {
        return R.layout.activity_up_feed;
    }


    @Override
    public void initData() {
        viewModel.hostMid = getIntent().getStringExtra(INTENT_HOST_MID);

        viewModel.loadFeedInfo();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        mAdapter = new UpFeedAdapter();
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
            mAdapter.addData(feedModel.getArchives());
            mAdapter.getLoadMoreModule().loadMoreComplete();
            boolean hasMore = feedModel.getArchives().size() == UpFeedViewModel.PAGE_SIZE;
            Log.i(TAG, "initView: hasMore: " + hasMore);
            mAdapter.getLoadMoreModule().setEnableLoadMore(hasMore);
        });
        viewModel.refreshEvent.observe(this, s -> mAdapter.setNewInstance(new ArrayList<>()));
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        final UpFeedModel.ArchivesBean model = mAdapter.getData().get(position);
        DetailActivity.showActivity(this, model.getBvid(), model.getAid());
    }

    public static void showActivity(Activity context, String hostMid) {
        final Intent intent = new Intent(context, UpFeedActivity.class);
        intent.putExtra(INTENT_HOST_MID, hostMid);
        context.startActivity(intent);
    }
}