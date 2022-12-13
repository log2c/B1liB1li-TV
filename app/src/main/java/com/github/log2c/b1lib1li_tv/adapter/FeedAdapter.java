package com.github.log2c.b1lib1li_tv.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.model.FeedModel;

public class FeedAdapter extends BaseQuickAdapter<FeedModel.ItemsModel, BaseViewHolder> implements LoadMoreModule {
    public FeedAdapter() {
        this(R.layout.item_glance_dynamic);
    }

    public FeedAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, FeedModel.ItemsModel itemsModel) {
        View rootView = baseViewHolder.getView(R.id.item_root);
        ImageView avatar = baseViewHolder.getView(R.id.iv_avatar);
        ImageView cover = baseViewHolder.getView(R.id.iv_cover);
        TextView title = baseViewHolder.getView(R.id.tv_title);
        TextView subTitle = baseViewHolder.getView(R.id.tv_sub_title);
        TextView time = baseViewHolder.getView(R.id.tv_time);

        try {
            Glide.with(avatar).load(itemsModel.getModules().getModule_author().getFace()).transform(new CircleCrop()).into(avatar);
            Glide.with(avatar).load(itemsModel.getModules().getModule_dynamic().getMajor().getArchive().getCover()).into(cover);

            title.setText(itemsModel.getModules().getModule_dynamic().getMajor().getArchive().getTitle());
            subTitle.setText(itemsModel.getModules().getModule_dynamic().getMajor().getArchive().getDesc());
            time.setText(itemsModel.getModules().getModule_author().getPub_time());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
