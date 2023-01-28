package com.github.log2c.b1lib1li_tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ReflectUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.xuexiang.xui.widget.flowlayout.BaseTagAdapter;
import com.xuexiang.xui.widget.flowlayout.DefaultFlowTagAdapter;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;

import java.util.List;

public class LocalFlowTagLayout extends FlowTagLayout {
    public LocalFlowTagLayout(Context context) {
        super(context);
    }

    public LocalFlowTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LocalFlowTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public <T> BaseTagAdapter setItems(@NonNull List<T> items) {
//        return super.setItems(items);
        BaseTagAdapter mAdapter = ReflectUtils.reflect(this).field("mAdapter").get();
        if (mAdapter != null) {
            mAdapter.clearAndAddTags(items);
        } else {
            BaseTagAdapter tagAdapter = new DefaultFlowTagAdapter(getContext()) {
                @Override
                protected int getLayoutId() {
                    return R.layout.adapter_flow_tag_item_setting_player;
                }
            };
            setAdapter(tagAdapter);
            tagAdapter.addTags(items);
        }
        return mAdapter;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int margin = ConvertUtils.dp2px(5);
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);
            childView.setMinimumHeight(ConvertUtils.dp2px(35));
            childView.setMinimumWidth(ConvertUtils.dp2px(65));
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            lp.leftMargin = margin;
            lp.rightMargin = margin;
            lp.topMargin = margin;
            lp.bottomMargin = margin;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
