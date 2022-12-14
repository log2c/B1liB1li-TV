package com.github.log2c.b1lib1li_tv.ui.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.ActivityDetailBinding;
import com.github.log2c.b1lib1li_tv.model.VideoViewModel;
import com.github.log2c.b1lib1li_tv.ui.player.PlayerActivity;
import com.github.log2c.base.base.BaseCoreActivity;

import java.util.Locale;

public class DetailActivity extends BaseCoreActivity<DetailViewModel, ActivityDetailBinding> {
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
        adapter = new BaseQuickAdapter<VideoViewModel.PagesModel, BaseViewHolder>(R.layout.item_tag_flow_adapter) {
            @Override
            protected void convert(@NonNull BaseViewHolder baseViewHolder, VideoViewModel.PagesModel page) {
                final String s = String.format(Locale.getDefault(), "%1$s  %2$s", page.getPart(), formatSeconds(page.getDuration()));
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
        mBinding.tvTitle.setText(videoViewModel.getTitle());
        Glide.with(this).load(videoViewModel.getOwner().getFace()).transform(new CircleCrop()).into(mBinding.ivAvatar);
        Glide.with(this).load(videoViewModel.getPic()).transform(new RoundedCorners(12)).into(mBinding.ivCover);

        mBinding.tvAuthor.setText(videoViewModel.getOwner().getName());
        mBinding.tvLike.setText(videoViewModel.getStat().getLike() + "");
        mBinding.tvCoin.setText(videoViewModel.getStat().getCoin() + "");
        mBinding.tvFavor.setText(videoViewModel.getStat().getFavorite() + "");

        mBinding.tvPlayCount.setText(videoViewModel.getStat().getView() + "");
        mBinding.tvDanmuku.setText(videoViewModel.getStat().getDanmaku() + "");
        mBinding.tvPubTime.setText(TimeUtils.millis2String(videoViewModel.getPubdate() * 1000L, "yyyy-MM-dd"));

        mBinding.tvDesc.setText(videoViewModel.getDesc());
        adapter.setNewInstance(videoViewModel.getPages());
    }

    public static String formatSeconds(long seconds) {
        String standardTime;
        if (seconds <= 0) {
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }
}