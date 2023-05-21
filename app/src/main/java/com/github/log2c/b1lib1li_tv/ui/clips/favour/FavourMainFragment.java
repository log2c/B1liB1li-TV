package com.github.log2c.b1lib1li_tv.ui.clips.favour;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.FragmentFavourMainBinding;
import com.github.log2c.base.base.BaseCoreFragment;

public class FavourMainFragment extends BaseCoreFragment<FavourViewModel, FragmentFavourMainBinding> {
    private FavourFragmentAdapter mFragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favour_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.fetchFavourList();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        FragmentFavourMainBinding mBinding = getMBinding();
        mFragmentAdapter = new FavourFragmentAdapter(getChildFragmentManager());
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