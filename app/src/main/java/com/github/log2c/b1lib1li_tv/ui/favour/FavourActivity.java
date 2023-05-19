package com.github.log2c.b1lib1li_tv.ui.favour;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.ActivityFavourBinding;
import com.github.log2c.base.base.BaseCoreActivity;
import com.google.android.material.tabs.TabLayout;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Objects;

public class FavourActivity extends BaseCoreActivity<FavourViewModel, ActivityFavourBinding> implements OnItemClickListener {
//    private BaseQuickAdapter<ImmutablePair<String, Long>, BaseViewHolder> mHeaderAdapter;

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
        Palette.from(BitmapFactory.decodeResource(getResources(),
                        R.drawable.background_image))
                .generate(palette -> {
                    if (palette != null && palette.getDarkMutedSwatch() != null) {
                        mBinding.getRoot().setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
                    }
                });
//        mHeaderAdapter = new BaseQuickAdapter<ImmutablePair<String, Long>, BaseViewHolder>(R.layout.item_header_favour) {
//            @Override
//            protected void convert(@NonNull BaseViewHolder baseViewHolder, ImmutablePair<String, Long> data) {
//                baseViewHolder.setText(R.id.title, data.getKey());
//            }
//        };
//        mHeaderAdapter.setOnItemClickListener(this);
        mBinding.header.setupWithViewPager(mBinding.viewpager);
        viewModel.favourListEvent.observe(this, data -> {
            for (ImmutablePair<String, Long> pair : data) {
                TabLayout.Tab tab = mBinding.header.newTab();
                tab
                        .setText(pair.getKey())
                        .setTag(pair.getValue());
                mBinding.header.addTab(tab);
            }
            Objects.requireNonNull(mBinding.header.getTabAt(0)).select();
        });
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
//        if (adapter == mHeaderAdapter) {
//            // TODO
//        }
    }
}