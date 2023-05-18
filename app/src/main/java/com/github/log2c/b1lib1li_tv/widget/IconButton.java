package com.github.log2c.b1lib1li_tv.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.github.log2c.b1lib1li_tv.R;

public class IconButton extends androidx.appcompat.widget.AppCompatButton {
    private @DrawableRes int mIconRes;
    private float mIconSize;
    private float mIconPadding;

    public IconButton(@NonNull Context context) {
        this(context, null);
    }

    public IconButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public IconButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.icon_button);
            mIconRes = ta.getResourceId(R.styleable.icon_button_icon, -1);
            mIconSize = ta.getDimension(R.styleable.icon_button_icon_size, -1);
            mIconPadding = ta.getDimension(R.styleable.icon_button_icon_padding, 0);
            ta.recycle();
            setIconInternal();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mIconSize == -1) {
            mIconSize = (float) (Math.min(w, h) * 0.5f);
//            mIconPadding = mIconSize * 0.1f;
            setIconInternal();
        }
    }

    public void setIcon(@DrawableRes int iconRes) {
        setIcon(iconRes, (int) mIconSize);
    }

    public void setIcon(@DrawableRes int iconRes, int dimension) {
        mIconRes = iconRes;
        mIconSize = dimension;
        setIconInternal();
    }

    protected void setIconInternal() {
        if (mIconRes == -1 || mIconSize == -1) {
            return;
        }
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), mIconRes, null);
        WrappedDrawable wrappedDrawable = new WrappedDrawable(drawable);
        wrappedDrawable.setBounds(0, 0, (int) mIconSize, (int) mIconSize);
        setPadding((int) (mIconSize + mIconPadding + mIconPadding), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
    }

    public static class WrappedDrawable extends Drawable {

        private final Drawable _drawable;

        protected Drawable getDrawable() {
            return _drawable;
        }

        public WrappedDrawable(Drawable drawable) {
            super();
            _drawable = drawable;
        }

        @Override
        public void setBounds(int left, int top, int right, int bottom) {
            //update bounds to get correctly
            super.setBounds(left, top, right, bottom);
            Drawable drawable = getDrawable();
            if (drawable != null) {
                drawable.setBounds(left, top, right, bottom);
            }
        }

        @Override
        public void setAlpha(int alpha) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                drawable.setAlpha(alpha);
            }
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }

        @Override
        public int getOpacity() {
            Drawable drawable = getDrawable();
            return drawable != null
                    ? drawable.getOpacity()
                    : PixelFormat.UNKNOWN;
        }

        @Override
        public void draw(Canvas canvas) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        @Override
        public int getIntrinsicWidth() {
            Drawable drawable = getDrawable();
            return drawable != null
                    ? drawable.getBounds().width()
                    : 0;
        }

        @Override
        public int getIntrinsicHeight() {
            Drawable drawable = getDrawable();
            return drawable != null ?
                    drawable.getBounds().height()
                    : 0;
        }
    }
}
