package com.github.log2c.b1lib1li_tv.ui.clips.relation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.FragmentRelationListBinding;
import com.github.log2c.b1lib1li_tv.model.RelationDetailModel;
import com.github.log2c.b1lib1li_tv.ui.upfeed.UpFeedActivity;
import com.github.log2c.base.base.BaseCoreFragment;


public class RelationListFragment extends BaseCoreFragment<RelationViewModel, FragmentRelationListBinding> {
    private static final String ARG_PARAM_TAG_ID = "tag_id";
    private RelationAdapter mAdapter;

    public RelationListFragment() {
    }

    public static RelationListFragment newInstance(long tagId) {
        RelationListFragment fragment = new RelationListFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM_TAG_ID, tagId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewModel.tagId == -1 && getArguments() != null) {
            viewModel.tagId = getArguments().getLong(ARG_PARAM_TAG_ID);
        }

        mAdapter = new RelationAdapter();
        mAdapter.getLoadMoreModule().setEnableLoadMore(true);
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        getMBinding().recyclerview.setAdapter(mAdapter);
        getMBinding().recyclerview.setNumColumns(6);
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> viewModel.fetchRelationList());
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            RelationDetailModel model = mAdapter.getData().get(position);
            UpFeedActivity.showActivity(requireActivity(), model.getMid() + "");
        });
        viewModel.relationData.observe(getViewLifecycleOwner(), relationDetailModels -> {
            if (relationDetailModels == null || relationDetailModels.size() == 0) {
                mAdapter.getLoadMoreModule().loadMoreFail();
                mAdapter.getLoadMoreModule().loadMoreEnd();
                return;
            }
            mAdapter.addData(relationDetailModels);
            if (relationDetailModels.size() != RelationViewModel.PAGE_SIZE) {
                mAdapter.getLoadMoreModule().loadMoreEnd();
            }
            mAdapter.getLoadMoreModule().loadMoreComplete();
        });
        if (viewModel.relationData.getValue() != null && viewModel.relationData.getValue().size() > 0) {
            viewModel.relationData.postValue(viewModel.relationData.getValue());
        }
    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        viewModel.fetchRelationList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_relation_list;
    }
}