package com.yuyh.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yuyh.library.circleprogress.ArcProgress;

public class CircleProgressActivity extends AppCompatActivity {

    ArcProgress arcProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        arcProgress = (ArcProgress) findViewById(R.id.arc_progress);
    }
}
