package com.github.log2c.b1lib1li_tv.ui.clips.toview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.adapter.ToviewAdapter;
import com.github.log2c.b1lib1li_tv.databinding.ActivityToviewBinding;
import com.github.log2c.b1lib1li_tv.model.ToViewModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.ui.detail.DetailActivity;
import com.github.log2c.base.base.BaseCoreFragment;

import java.util.ArrayList;

public class ToViewFragment extends BaseCoreFragment<ToviewViewModel, ActivityToviewBinding> implements OnItemClickListener {
    private ToviewAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_toview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.fetchToview();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        ActivityToviewBinding mBinding = getMBinding();
        mAdapter = new ToviewAdapter();
        mBinding.recyclerview.setNumColumns(AppConfigRepository.getInstance().getDynamicSpanCount());
        mBinding.recyclerview.setAdapter(mAdapter);
        mAdapter.setNewInstance(new ArrayList<>());
        mAdapter.setOnItemClickListener(this);
        mAdapter.setEmptyView(R.layout.layout_empty);
        viewModel.dataEvent.observe(this, listModels -> mAdapter.setNewInstance(listModels));
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        final ToViewModel.ListModel model = mAdapter.getData().get(position);
        DetailActivity.showActivity(requireActivity(), model.getBvid(), model.getAid() + "");
    }
}