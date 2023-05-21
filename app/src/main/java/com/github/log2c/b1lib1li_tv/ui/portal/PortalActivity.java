package com.github.log2c.b1lib1li_tv.ui.portal;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.contracts.KeyDownContract;
import com.github.log2c.b1lib1li_tv.databinding.ActivityPortalBinding;
import com.github.log2c.b1lib1li_tv.ui.clips.dynamic.DynamicFragment;
import com.github.log2c.b1lib1li_tv.ui.clips.favour.FavourMainFragment;
import com.github.log2c.b1lib1li_tv.ui.clips.relation.RelationMainFragment;
import com.github.log2c.b1lib1li_tv.ui.clips.toview.ToViewFragment;
import com.github.log2c.b1lib1li_tv.ui.login.LoginActivity;
import com.github.log2c.b1lib1li_tv.ui.setting.SettingsActivity;
import com.github.log2c.base.base.BaseCoreActivity;
import com.xuexiang.xui.widget.tabbar.VerticalTabLayout;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

public class PortalActivity extends BaseCoreActivity<PortalViewModel, ActivityPortalBinding> {
    private FragmentAdapter mFragmentAdapter;
    private List<ImmutablePair<String, Class<? extends Fragment>>> mFragmentList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_portal;
    }

    @Override
    public void initData() {
        mFragmentList.add(new ImmutablePair<>(getString(R.string.dynamic), DynamicFragment.class));
        mFragmentList.add(new ImmutablePair<>(getString(R.string.to_view), ToViewFragment.class));
        mFragmentList.add(new ImmutablePair<>(getString(R.string.favour), FavourMainFragment.class));
        mFragmentList.add(new ImmutablePair<>(getString(R.string.relation_follow), RelationMainFragment.class));
        mFragmentAdapter.setData(mFragmentList);
        viewModel.fetchUserInfo();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        Glide.with(this).load(R.drawable.ic_avatar_bilibili).transform(new CircleCrop()).into(mBinding.ivAvatar);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mBinding.header);
        mBinding.viewPager.setVerticalTabLayout(mBinding.header);
        mBinding.viewPager.setAdapter(mFragmentAdapter);
        mBinding.viewPager.setKeyEventsEnabled(true);
        mBinding.viewPager.setOffscreenPageLimit(4);
        mBinding.header.setupWithViewPager(mBinding.viewPager);
        mBinding.ivAvatar.setOnClickListener(v -> navLogin());
        mBinding.btnSetting.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));

        viewModel.navUserInfoEvent.observe(this, model -> {
            Glide.with(this).load(model.getFace()).transform(new CircleCrop()).into(mBinding.ivAvatar);
//            mBinding.btLogin.setText(model.getUname());
        });
        viewModel.unLoginEvent.observe(this, unused -> navLogin());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            Fragment fragment = mFragmentAdapter.getCurrentFragment();
            if (fragment instanceof KeyDownContract && fragment.isVisible()) {
                ((KeyDownContract) fragment).onRefreshKeyDown();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void navLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }


    @SuppressWarnings("deprecation")
    public static class FragmentAdapter extends FragmentPagerAdapter {
        private final List<ImmutablePair<String, Class<? extends Fragment>>> mData;
        private VerticalTabLayout mTabLayout;

        private Fragment mCurrentFragment;

        public FragmentAdapter(@NonNull FragmentManager fm, VerticalTabLayout tabLayout) {
            super(fm, BEHAVIOR_SET_USER_VISIBLE_HINT);
            mTabLayout = tabLayout;
            mData = new ArrayList<>();
        }

        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            try {
                return mData.get(position).getValue().newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
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

        @Override
        public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((Fragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        public void setData(List<ImmutablePair<String, Class<? extends Fragment>>> data) {
            if (data != null) {
                mData.clear();
                mData.addAll(data);
                notifyDataSetChanged();
            }
        }
    }
}