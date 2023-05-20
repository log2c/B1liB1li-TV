package com.github.log2c.b1lib1li_tv.ui.clips.relation;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.model.RelationDetailModel;

import java.util.ArrayList;
import java.util.List;

public class RelationAdapter extends BaseQuickAdapter<RelationDetailModel, BaseViewHolder> implements LoadMoreModule {
    public RelationAdapter() {
        this(R.layout.item_list_relation, new ArrayList<>());
    }

    public RelationAdapter(int layoutResId, @Nullable List<RelationDetailModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, RelationDetailModel relationDetailModel) {
        Glide.with(baseViewHolder.getView(R.id.iv_avatar)).load(relationDetailModel.getFace()).transform(new CircleCrop()).into((ImageView) baseViewHolder.getView(R.id.iv_avatar));
        baseViewHolder.setText(R.id.tv_name, relationDetailModel.getUname());
        baseViewHolder.setText(R.id.tv_sign, relationDetailModel.getSign());
    }
}
