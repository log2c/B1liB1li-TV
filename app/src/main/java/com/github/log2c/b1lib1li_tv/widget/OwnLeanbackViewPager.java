package com.github.log2c.b1lib1li_tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.tab.LeanbackViewPager;

public class OwnLeanbackViewPager extends LeanbackViewPager {
    private static final String TAG = OwnLeanbackViewPager.class.getSimpleName();
    private OwnVerticalTabLayout mVerticalTabLayout;

    public OwnLeanbackViewPager(@NonNull Context context) {
        super(context);
    }

    public OwnLeanbackViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean arrowScroll(int direction) {
        Log.i(TAG, "arrowScroll: " + direction);
        View currentFocused = findFocus();
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused,
                direction);
        if (nextFocused != null && nextFocused != currentFocused) {
        } else if (direction == FOCUS_LEFT || direction == FOCUS_BACKWARD) {
//            if (getParent() instanceof ViewGroup) {
//                for (int index = 0; index < ((ViewGroup) getParent()).getChildCount(); index++) {
//                    View view = ((ViewGroup) getParent()).getChildAt(index);
//                    Log.i(TAG, "arrowScroll: " + view.getClass().getSimpleName());
//                    if (view instanceof OwnVerticalTabLayout) {
//                        view.requestFocus();
//                        return true;
//                    }
//                }
//            }
            if (mVerticalTabLayout != null) {
                mVerticalTabLayout.requestFocus(View.FOCUS_LEFT);
                return true;
            }
        }
        return super.arrowScroll(direction);
    }

    public OwnVerticalTabLayout getVerticalTabLayout() {
        return mVerticalTabLayout;
    }

    public void setVerticalTabLayout(OwnVerticalTabLayout verticalTabLayout) {
        mVerticalTabLayout = verticalTabLayout;
    }
}
