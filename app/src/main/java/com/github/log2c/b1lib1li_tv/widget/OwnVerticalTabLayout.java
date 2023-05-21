package com.github.log2c.b1lib1li_tv.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xuexiang.xui.widget.tabbar.VerticalTabLayout;
import com.xuexiang.xui.widget.tabbar.vertical.TabView;
import com.xuexiang.xui.widget.tabbar.vertical.XTabView;

public class OwnVerticalTabLayout extends VerticalTabLayout {
    private static final String TAG = OwnVerticalTabLayout.class.getSimpleName();

    public OwnVerticalTabLayout(Context context) {
        super(context);
    }

    public OwnVerticalTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OwnVerticalTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        Log.i(TAG, "requestChildFocus");
        super.requestChildFocus(child, focused);
        if (focused instanceof XTabView) {
            int focusedPosition = getPositionByTabView((TabView) focused);
            int oldPosition = getSelectedTabPosition();
            if (focusedPosition != oldPosition) {
                setTabSelected(focusedPosition);
            }
        }
    }


    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (previouslyFocusedRect == null && direction == View.FOCUS_LEFT) {
            Log.i(TAG, "requestFocus: set focus");
            getTabAt(getSelectedTabPosition()).requestFocus();
            return true;
        }
        Log.i(TAG, "requestFocus");
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        Log.i(TAG, "onRequestFocusInDescendants");
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        Log.i(TAG, "onWindowFocusChanged");
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    public void dispatchWindowFocusChanged(boolean hasFocus) {
        Log.i(TAG, "dispatchWindowFocusChanged");
        super.dispatchWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean dispatchUnhandledMove(View focused, int direction) {
        Log.i(TAG, "dispatchUnhandledMove");
        return super.dispatchUnhandledMove(focused, direction);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        Log.i(TAG, "focusSearch");
        return super.focusSearch(focused, direction);
    }

    @Override
    protected boolean dispatchGenericFocusedEvent(MotionEvent event) {
        Log.i(TAG, "dispatchGenericFocusedEvent");
        return super.dispatchGenericFocusedEvent(event);
    }
}
