package com.github.log2c.b1lib1li_tv.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.model.ToViewModel;

public class ToviewAdapter extends BaseQuickAdapter<ToViewModel.ListModel, BaseViewHolder> implements LoadMoreModule {
    public ToviewAdapter() {
        this(R.layout.item_glance_dynamic);
    }

    public ToviewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ToViewModel.ListModel itemsModel) {
        View rootView = baseViewHolder.getView(R.id.item_root);
        ImageView avatar = baseViewHolder.getView(R.id.iv_avatar);
        ImageView cover = baseViewHolder.getView(R.id.iv_cover);
        TextView title = baseViewHolder.getView(R.id.tv_title);
        TextView name = baseViewHolder.getView(R.id.tv_name);
        TextView time = baseViewHolder.getView(R.id.tv_time);
        TextView duration = baseViewHolder.getView(R.id.tv_duration);

        try {
            Glide.with(avatar).load(itemsModel.getOwner().getFace()).transform(new CircleCrop()).into(avatar);
            Glide.with(avatar).load(itemsModel.getPic()).transform(new RoundedCorners(12)).into(cover);

            title.setText(itemsModel.getTitle());
            name.setText(itemsModel.getOwner().getName());
            time.setText(TimeUtils.millis2String(itemsModel.getPubdate(), "MM-dd HH:mm"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
