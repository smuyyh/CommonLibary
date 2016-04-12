package com.yuyh.library.view.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

import com.yuyh.library.view.common.ShimmerViewBase;
import com.yuyh.library.view.common.ShimmerViewHelper;

/**
 * 文字闪烁发光的Button
 */
public class ShimmerButton extends Button implements ShimmerViewBase {

    private ShimmerViewHelper shimmerViewHelper;

    public ShimmerButton(Context context) {
        this(context, null);
    }

    public ShimmerButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShimmerButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        shimmerViewHelper = new ShimmerViewHelper(this, getPaint(), attrs);
        shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
        shimmerViewHelper.checkModel(this); // 新增：为了判断启动模式
    }

    @Override
    public float getGradientX() {
        return shimmerViewHelper.getGradientX();
    }

    @Override
    public void setGradientX(float gradientX) {
        shimmerViewHelper.setGradientX(gradientX);
    }

    @Override
    public boolean isShimmering() {
        return shimmerViewHelper.isShimmering();
    }

    @Override
    public void setShimmering(boolean isShimmering) {
        shimmerViewHelper.setShimmering(isShimmering);
    }

    @Override
    public boolean isSetUp() {
        return shimmerViewHelper.isSetUp();
    }

    @Override
    public void setAnimationSetupCallback(ShimmerViewHelper.AnimationSetupCallback callback) {
        shimmerViewHelper.setAnimationSetupCallback(callback);
    }

    @Override
    public int getPrimaryColor() {
        return shimmerViewHelper.getPrimaryColor();
    }

    @Override
    public void setPrimaryColor(int primaryColor) {
        shimmerViewHelper.setPrimaryColor(primaryColor);
    }

    @Override
    public int getReflectionColor() {
        return shimmerViewHelper.getReflectionColor();
    }

    @Override
    public void setReflectionColor(int reflectionColor) {
        shimmerViewHelper.setReflectionColor(reflectionColor);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (shimmerViewHelper != null) {
            shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
        if (shimmerViewHelper != null) {
            shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (shimmerViewHelper != null) {
            shimmerViewHelper.onSizeChanged();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (shimmerViewHelper != null) {
            shimmerViewHelper.onDraw();
        }
        super.onDraw(canvas);
    }
}
