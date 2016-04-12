package com.yuyh.library.view.common;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;

public class Shimmer {

    /**
     * 闪烁动画方向 默认：左 -> 右
     */
    public static final int ANIMATION_DIRECTION_LTR = 0; // 左 -> 右
    public static final int ANIMATION_DIRECTION_RTL = 1; // 右 -> 左
    private static final int DEFAULT_DIRECTION = ANIMATION_DIRECTION_LTR;
    /**
     * 重复次数。默认-1一直重复
     */
    public static final int DEFAULT_REPEAT_COUNT = ValueAnimator.INFINITE;
    /**
     * 动画时长 默认1.5秒
     */
    public static final long DEFAULT_DURATION = 1500;
    /**
     * 延迟多少毫秒后执行动画
     */
    public static final long DEFAULT_START_DELAY = 0;

    private int repeatCount;
    private long duration;
    private long startDelay;
    private int direction;
    private Animator.AnimatorListener animatorListener;

    private ObjectAnimator animator;

    public Shimmer() {
        repeatCount = DEFAULT_REPEAT_COUNT;
        duration = DEFAULT_DURATION;
        startDelay = DEFAULT_START_DELAY;
        direction = DEFAULT_DIRECTION;
    }

    public <V extends View & ShimmerViewBase> void start(final V shimmerView) {
        if (isAnimating()) { // 如果动画已经在执行
            return;
        }

        final Runnable animate = new Runnable() {
            @Override
            public void run() {
                shimmerView.setShimmering(true);
                float fromX = 0;
                float toX = shimmerView.getWidth();
                if (direction == ANIMATION_DIRECTION_RTL) {
                    fromX = shimmerView.getWidth();
                    toX = 0;
                }

                animator = ObjectAnimator.ofFloat(shimmerView, "gradientX", fromX, toX);
                animator.setRepeatCount(repeatCount);
                animator.setDuration(duration);
                animator.setStartDelay(startDelay);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        shimmerView.setShimmering(false);  // 设置动画状态
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            shimmerView.postInvalidate();
                        } else {
                            shimmerView.postInvalidateOnAnimation();
                        }
                        animator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });

                if (animatorListener != null) {
                    animator.addListener(animatorListener); // 可以同时有多个监听
                }

                animator.start();
            }
        };

        if (!shimmerView.isSetUp()) {
            shimmerView.setAnimationSetupCallback(new ShimmerViewHelper.AnimationSetupCallback() {
                @Override
                public void onSetupAnimation(final View target) {
                    animate.run();
                }
            });
        } else {
            animate.run();
        }
    }

    /**
     * 取消闪烁动画
     */
    public void cancel() {
        if (animator != null) {
            animator.cancel();
        }
    }

    /**
     * 是否在执行闪烁动画
     *
     * @return
     */
    public boolean isAnimating() {
        return animator != null && animator.isRunning();
    }

    /**
     * 闪烁重复次数
     *
     * @param repeatCount
     * @return
     */
    public Shimmer setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    /**
     * 动画时长
     *
     * @param duration 动画时长 单位ms
     * @return
     */
    public Shimmer setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    /**
     * 延时执行动画
     *
     * @param startDelay 延时时间 单位ms
     * @return
     */
    public Shimmer setStartDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    /**
     * 设置闪烁方向
     *
     * @param direction ANIMATION_DIRECTION_LTR = 0; // 左 -> 右
     *                  ANIMATION_DIRECTION_RTL = 1; // 右 -> 左
     * @return
     */
    public Shimmer setDirection(int direction) {
        if (direction != ANIMATION_DIRECTION_LTR && direction != ANIMATION_DIRECTION_RTL) {
            this.direction = ANIMATION_DIRECTION_LTR;
        } else {
            this.direction = direction;
        }
        return this;
    }

    /**
     * 设置动画监听（一般不需要）
     *
     * @param animatorListener
     * @return
     */
    public Shimmer setAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
        return this;
    }

}
