package com.github.log2c.b1lib1li_tv.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.CommonUtils;
import com.github.log2c.b1lib1li_tv.model.UpFeedModel;

public class UpFeedAdapter extends BaseQuickAdapter<UpFeedModel.ArchivesBean, BaseViewHolder> implements LoadMoreModule {
    public UpFeedAdapter() {
        super(R.layout.item_glance_dynamic);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, UpFeedModel.ArchivesBean upFeedModel) {
        View rootView = baseViewHolder.getView(R.id.item_root);
        ImageView avatar = baseViewHolder.getView(R.id.iv_avatar);
        ImageView cover = baseViewHolder.getView(R.id.iv_cover);
        TextView title = baseViewHolder.getView(R.id.tv_title);
        TextView name = baseViewHolder.getView(R.id.tv_name);
        TextView time = baseViewHolder.getView(R.id.tv_time);
        TextView duration = baseViewHolder.getView(R.id.tv_duration);

        try {
            avatar.setVisibility(View.GONE);
            Glide.with(avatar).load(upFeedModel.getPic()).transform(new RoundedCorners(12)).into(cover);

            title.setText(upFeedModel.getTitle());
            name.setVisibility(View.GONE);
            time.setText(TimeUtils.millis2String(upFeedModel.getPubdate() * 1000L, "yyyy-MM-dd"));
            duration.setText(CommonUtils.formatSeconds(upFeedModel.getDuration()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
