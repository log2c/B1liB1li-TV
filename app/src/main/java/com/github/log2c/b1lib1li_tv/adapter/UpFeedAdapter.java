package com.github.log2c.b1lib1li_tv.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.model.UpFeedModel;

public class UpFeedAdapter extends BaseQuickAdapter<UpFeedModel.ItemsModel, BaseViewHolder> implements LoadMoreModule {
    public UpFeedAdapter() {
        super(R.layout.item_glance_dynamic);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, UpFeedModel.ItemsModel upFeedModel) {
        CardView cv = baseViewHolder.getView(R.id.cv_root);
        cv.setCardElevation(0f);
        cv.setOnFocusChangeListener((v, hasFocus) -> {
            CardView card = (CardView) v;
            if (hasFocus) {
                card.setScaleX(1.1f);
                card.setScaleY(1.1f);
                card.setCardElevation(ConvertUtils.dp2px(15));
            } else {
                card.setCardElevation(0f);
                card.setScaleX(1f);
                card.setScaleY(1f);
            }
        });

        View rootView = baseViewHolder.getView(R.id.item_root);
        ImageView avatar = baseViewHolder.getView(R.id.iv_avatar);
        ImageView cover = baseViewHolder.getView(R.id.iv_cover);
        TextView title = baseViewHolder.getView(R.id.tv_title);
        TextView name = baseViewHolder.getView(R.id.tv_name);
        TextView time = baseViewHolder.getView(R.id.tv_time);

        try {
            Glide.with(avatar).load(upFeedModel.getModules().getModule_author().getFace()).transform(new CircleCrop()).into(avatar);
            Glide.with(avatar).load(upFeedModel.getModules().getModule_dynamic().getMajor().getArchive().getCover()).transform(new RoundedCorners(12)).into(cover);

            title.setText(upFeedModel.getModules().getModule_dynamic().getMajor().getArchive().getTitle());
            name.setText(upFeedModel.getModules().getModule_author().getName());
            time.setText(upFeedModel.getModules().getModule_author().getPub_time());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
