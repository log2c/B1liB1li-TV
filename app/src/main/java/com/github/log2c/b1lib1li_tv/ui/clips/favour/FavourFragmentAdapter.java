package com.github.log2c.b1lib1li_tv.ui.clips.favour;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class FavourFragmentAdapter extends FragmentPagerAdapter {
    private List<ImmutablePair<String, Long>> mData;
    private Fragment mCurrentFragment;

    public FavourFragmentAdapter(@NonNull FragmentManager fm) {
        this(fm, BEHAVIOR_SET_USER_VISIBLE_HINT);
    }

    public FavourFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mData = new ArrayList<>();
    }

    public void setData(List<ImmutablePair<String, Long>> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FavourFragment.newInstance(mData.get(position).getValue());
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
}
