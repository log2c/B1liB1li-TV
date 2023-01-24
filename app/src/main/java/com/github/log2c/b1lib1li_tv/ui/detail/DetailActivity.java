package com.github.log2c.b1lib1li_tv.ui.detail;

import static com.github.log2c.b1lib1li_tv.common.CommonUtils.formatNumbers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.CommonUtils;
import com.github.log2c.b1lib1li_tv.databinding.ActivityDetailBinding;
import com.github.log2c.b1lib1li_tv.model.VideoViewModel;
import com.github.log2c.b1lib1li_tv.ui.player.PlayerActivity;
import com.github.log2c.b1lib1li_tv.ui.upfeed.UpFeedActivity;
import com.github.log2c.base.base.BaseCoreActivity;

import java.util.Locale;

public class DetailActivity extends BaseCoreActivity<DetailViewModel, ActivityDetailBinding> implements View.OnClickListener {
    public static final String TAG = DetailActivity.class.getSimpleName();
    public static final String INTENT_BVID = "bvid";
    public static final String INTENT_AID = "aid";
    private BaseQuickAdapter<VideoViewModel.PagesModel, BaseViewHolder> adapter;

    public static void showActivity(Activity context, @Nullable String bvid, @Nullable String aid) {
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(INTENT_BVID, bvid);
        intent.putExtra(INTENT_AID, aid);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initData() {
        viewModel.bvid = getIntent().getStringExtra(INTENT_BVID);
        viewModel.aid = getIntent().getStringExtra(INTENT_AID);

        viewModel.getVideoView();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        mBinding.tvAuthor.setOnClickListener(this);
        adapter = new BaseQuickAdapter<VideoViewModel.PagesModel, BaseViewHolder>(R.layout.item_tag_flow_adapter) {
            @Override
            protected void convert(@NonNull BaseViewHolder baseViewHolder, VideoViewModel.PagesModel page) {
                final String s = String.format(Locale.getDefault(), "%1$s  %2$s", page.getPart(), CommonUtils.formatSeconds(page.getDuration()));
                baseViewHolder.setText(R.id.tv_tag_item, s);
            }
        };
        viewModel.viewModelLiveEvent.observe(this, this::fillData);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            final VideoViewModel viewModel = DetailActivity.this.viewModel.viewModelLiveEvent.getValue();
            final VideoViewModel.PagesModel model = viewModel.getPages().get(position);
            PlayerActivity.showActivity(DetailActivity.this, viewModel.getBvid(), viewModel.getAid() + "", model.getCid() + "");
        });
    }

    @SuppressLint("SetTextI18n")
    private void fillData(VideoViewModel videoViewModel) {
        if (videoViewModel == null) {
            adapter.getLoadMoreModule().loadMoreFail();
            return;
        }
        mBinding.tvTitle.setText(videoViewModel.getTitle());
        Glide.with(this).load(videoViewModel.getOwner().getFace()).transform(new CircleCrop()).into(mBinding.ivAvatar);
        Glide.with(this).load(videoViewModel.getPic()).transform(new RoundedCorners(12)).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.i(TAG, "onLoadFailed: ");
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof BitmapDrawable) {
                    Palette.from(((BitmapDrawable) resource).getBitmap())
                            .generate(palette -> {
                                if (palette != null && palette.getDarkMutedSwatch() != null) {
                                    mBinding.container.setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
                                }
                            });
                }
                Log.i(TAG, "onResourceReady: ");
                return false;
            }
        }).into(mBinding.ivCover);

        mBinding.tvAuthor.setText(videoViewModel.getOwner().getName());
        mBinding.tvLike.setText(formatNumbers(videoViewModel.getStat().getLike()));
        mBinding.tvCoin.setText(formatNumbers(videoViewModel.getStat().getCoin()));
        mBinding.tvFavor.setText(formatNumbers(videoViewModel.getStat().getFavorite()));

        mBinding.tvPlayCount.setText(formatNumbers(videoViewModel.getStat().getView()));
        mBinding.tvDanmuku.setText(formatNumbers(videoViewModel.getStat().getDanmaku()));
        mBinding.tvPubTime.setText(TimeUtils.millis2String(videoViewModel.getPubdate() * 1000L, "yyyy-MM-dd"));

        mBinding.tvDesc.setText(videoViewModel.getDesc());
        adapter.setNewInstance(videoViewModel.getPages());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_author) {
            UpFeedActivity.showActivity(this, viewModel.hostMid);
        }
    }
}