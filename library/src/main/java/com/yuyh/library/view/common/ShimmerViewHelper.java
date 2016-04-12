package com.yuyh.library.view.common;

import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.yuyh.library.R;
import com.yuyh.library.view.button.ShimmerButton;
import com.yuyh.library.view.text.ShimmerTextView;

public class ShimmerViewHelper<T extends ShimmerViewBase> {

    public interface AnimationSetupCallback {
        void onSetupAnimation(View target);
    }

    private static final int DEFAULT_REFLECTION_COLOR = 0xFFFFFFFF;

    private View view;
    private Paint paint;
    // center position of the gradient
    private float gradientX;
    // shader applied on the text view ，only null until the first global layout
    private LinearGradient linearGradient;
    // shader's local matrix never null
    private Matrix linearGradientMatrix;
    private int primaryColor;
    // shimmer reflection color
    private int reflectionColor;

    /**
     * 新增：启动模式 0：正常模式，需Java代码控制开启动画
     * 1：直接开启：view初始化后直接显示动画
     */
    private int model;
    private final int NORMAL = 0;
    private final int START_RIGHT_AWAY = 1;

    /**
     * 新增：闪烁动画相关属性
     */
    private int repeatCount;
    private long duration;
    private long startDelay;
    private int direction;

    // true when animating
    private boolean isShimmering;
    // true after first global layout
    private boolean isSetUp;
    // callback called after first global layout
    private AnimationSetupCallback callback;

    public ShimmerViewHelper(View view, Paint paint, AttributeSet attributeSet) {
        this.view = view;
        this.paint = paint;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        reflectionColor = DEFAULT_REFLECTION_COLOR;
        model = NORMAL;
        if (attributeSet != null) {
            TypedArray a = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.ShimmerView, 0, 0);
            if (a != null) {
                try {
                    reflectionColor = a.getColor(R.styleable.ShimmerView_reflectionColor, DEFAULT_REFLECTION_COLOR);
                    model = a.getInteger(R.styleable.ShimmerView_model, NORMAL);
                    repeatCount = a.getInteger(R.styleable.ShimmerView_repeatCount, Shimmer.DEFAULT_REPEAT_COUNT);
                    duration = a.getInteger(R.styleable.ShimmerView_duration, (int) Shimmer.DEFAULT_DURATION);
                    startDelay = a.getInteger(R.styleable.ShimmerView_startDelay, (int) Shimmer.DEFAULT_START_DELAY);
                    direction = a.getInteger(R.styleable.ShimmerView_direction, Shimmer.ANIMATION_DIRECTION_LTR);
                } catch (Exception e) {
                    android.util.Log.e("ShimmerTextView", "Error while creating the view:", e);
                } finally {
                    a.recycle();
                }
            }
        }
        linearGradientMatrix = new Matrix();
    }

    private void resetLinearGradient() {
        linearGradient = new LinearGradient(-view.getWidth(), 0, 0, 0,
                new int[]{primaryColor, reflectionColor, primaryColor,},
                new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    public void onSizeChanged() {
        resetLinearGradient();
        if (!isSetUp) {
            isSetUp = true;
            if (callback != null) {
                callback.onSetupAnimation(view);
            }
        }
    }

    /**
     * content of the wrapping view's onDraw(Canvas)
     * MUST BE CALLED BEFORE SUPER STATEMENT
     */
    public void onDraw() {

        // only draw the shader gradient over the text while animating
        if (isShimmering) {

            // first onDraw() when shimmering
            if (paint.getShader() == null) {
                paint.setShader(linearGradient);
            }

            // translate the shader local matrix
            linearGradientMatrix.setTranslate(2 * gradientX, 0);

            // this is required in order to invalidate the shader's position
            linearGradient.setLocalMatrix(linearGradientMatrix);

        } else {
            // we're not animating, remove the shader from the paint
            paint.setShader(null);
        }
    }

    public float getGradientX() {
        return gradientX;
    }

    public void setGradientX(float gradientX) {
        this.gradientX = gradientX;
        view.invalidate();
    }

    public boolean isShimmering() {
        return isShimmering;
    }

    public void setShimmering(boolean isShimmering) {
        this.isShimmering = isShimmering;
    }

    public boolean isSetUp() {
        return isSetUp;
    }

    public void setAnimationSetupCallback(AnimationSetupCallback callback) {
        this.callback = callback;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    public int getReflectionColor() {
        return reflectionColor;
    }

    public void setReflectionColor(int reflectionColor) {
        this.reflectionColor = reflectionColor;
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    /**
     * 如果xml配置了立即启动，其他动画相关属性才会生效。否则需要Java代码自行配置
     *
     * @param t
     */
    public void checkModel(T t) {
        if (model == START_RIGHT_AWAY) { // 立即执行动画
            Shimmer shimmer = new Shimmer().setDirection(direction).setDuration(duration).setRepeatCount(repeatCount).setStartDelay(startDelay);
            if (t instanceof ShimmerTextView) {
                shimmer.start((ShimmerTextView) view);
            } else if (t instanceof ShimmerButton) {
                shimmer.start((ShimmerButton) view);
            }
        }
    }
}
