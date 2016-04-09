package com.yuyh.library.utils;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

import com.yuyh.library.AppUtils;

/**
 * @author yuyuhang.
 * @date 16/4/9.
 */
public class WidgetUtils {
    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return AppUtils.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return AppUtils.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    public static int dpToPx(float dp) {
        final float scale = AppUtils.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值
     *
     * @param pxValue
     * @return
     */
    public static int pxToSp(float pxValue) {
        return (int) (pxValue / AppUtils.getAppContext().getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @param spValue
     * @return
     */
    public static int spToPx(float spValue) {
        return (int) (spValue * AppUtils.getAppContext().getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    /**
     * 测量控件
     *
     * @param view
     */
    public static void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 测量控件高度
     *
     * @param view
     * @return
     */
    public static int getMeasuredHeight(View view) {
        measureView(view);
        return view.getMeasuredHeight();
    }

    /**
     * 测量控件宽度
     *
     * @param view
     * @return
     */
    public static int getMeasuredWidth(View view) {
        measureView(view);
        return view.getMeasuredWidth();
    }
}
