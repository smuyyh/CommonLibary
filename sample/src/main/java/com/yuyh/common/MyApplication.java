package com.yuyh.common;

import android.app.Application;

import com.yuyh.library.AppUtils;

/**
 * @author yuyh.
 * @date 16/4/11.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
