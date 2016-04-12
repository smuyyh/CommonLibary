package com.yuyh.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yuyh.library.view.common.Shimmer;
import com.yuyh.library.view.text.ShimmerTextView;

public class CustomEditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ShimmerTextView shimmerTextView = (ShimmerTextView) findViewById(R.id.tvShimmer);
        Shimmer shimmer = new Shimmer();
        //shimmer.start(shimmerTextView);
    }
}
