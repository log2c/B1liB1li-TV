package com.github.log2c.b1lib1li_tv.ui.favour;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.ActivityFavourBinding;
import com.github.log2c.base.base.BaseCoreActivity;

public class FavourActivity extends BaseCoreActivity<FavourViewModel, ActivityFavourBinding> {
    private FavourFragmentAdapter mFragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_favour;
    }

    @Override
    public void initData() {
        viewModel.fetchFavourList();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        mFragmentAdapter = new FavourFragmentAdapter(getSupportFragmentManager());
        Palette.from(BitmapFactory.decodeResource(getResources(),
                        R.drawable.background_image))
                .generate(palette -> {
                    if (palette != null && palette.getDarkMutedSwatch() != null) {
                        mBinding.getRoot().setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
                    }
                });
        mBinding.viewpager.setAdapter(mFragmentAdapter);
        mBinding.viewpager.setKeyEventsEnabled(true);
        mBinding.header.setupWithViewPager(mBinding.viewpager);
        viewModel.favourListEvent.observe(this, data -> mFragmentAdapter.setData(data));
    }
}