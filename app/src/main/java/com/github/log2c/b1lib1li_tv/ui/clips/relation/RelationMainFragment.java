package com.github.log2c.b1lib1li_tv.ui.clips.relation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.FragmentRelationMainBinding;
import com.github.log2c.base.base.BaseCoreFragment;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

public class RelationMainFragment extends BaseCoreFragment<RelationViewModel, FragmentRelationMainBinding> {
    private FragmentAdapter mFragmentAdapter;

    public RelationMainFragment() {
    }

    public static RelationMainFragment newInstance() {
        RelationMainFragment fragment = new RelationMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_relation_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        getMBinding().viewpager.setAdapter(mFragmentAdapter);
        getMBinding().viewpager.setKeyEventsEnabled(true);
        getMBinding().viewpager.setOffscreenPageLimit(4);
        getMBinding().header.setupWithViewPager(getMBinding().viewpager);
        viewModel.relationTags.observe(getViewLifecycleOwner(), data -> mFragmentAdapter.setData(viewModel.getRelationTags()));
        viewModel.fetchRelationTags();
    }

    @SuppressWarnings("deprecation")
    public static class FragmentAdapter extends FragmentPagerAdapter {
        private final List<ImmutablePair<String, Integer>> mData;

        public FragmentAdapter(@NonNull FragmentManager fm) {
            this(fm, BEHAVIOR_SET_USER_VISIBLE_HINT);
        }

        public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            mData = new ArrayList<>();
        }

        public void setData(List<ImmutablePair<String, Integer>> data) {
            if (data != null) {
                mData.clear();
                mData.addAll(data);
                notifyDataSetChanged();
            }
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return RelationListFragment.newInstance(mData.get(position).getValue());
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mData.get(position).getKey();
        }
    }
}