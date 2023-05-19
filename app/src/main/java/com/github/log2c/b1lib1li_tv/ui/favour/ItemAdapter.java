package com.github.log2c.b1lib1li_tv.ui.favour;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.CommonUtils;
import com.github.log2c.b1lib1li_tv.model.FavourDetailModel;

public class ItemAdapter extends BaseQuickAdapter<FavourDetailModel.MediasModel, BaseViewHolder> implements LoadMoreModule {
    public ItemAdapter() {
        this(R.layout.item_glance_dynamic);
    }

    public ItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, FavourDetailModel.MediasModel itemsModel) {
        View rootView = baseViewHolder.getView(R.id.item_root);
        ImageView avatar = baseViewHolder.getView(R.id.iv_avatar);
        ImageView cover = baseViewHolder.getView(R.id.iv_cover);
        TextView title = baseViewHolder.getView(R.id.tv_title);
        TextView name = baseViewHolder.getView(R.id.tv_name);
        TextView time = baseViewHolder.getView(R.id.tv_time);
        TextView duration = baseViewHolder.getView(R.id.tv_duration);

        try {
            Glide.with(avatar).load(itemsModel.getUpper().getFace()).transform(new CircleCrop()).into(avatar);
            Glide.with(avatar).load(itemsModel.getCover()).transform(new RoundedCorners(12)).into(cover);

            title.setText(itemsModel.getTitle());
            name.setText(itemsModel.getUpper().getName());
            time.setText(TimeUtils.millis2String(itemsModel.getPubtime(), "MM-dd HH:mm"));
            duration.setText(CommonUtils.formatSeconds(itemsModel.getDuration()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
