package com.yuyh.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pullRefresh(View view){
        startActivity(new Intent(this, PullToRefreshActivity.class));
    }

    public void swipe(View view){
        startActivity(new Intent(this, SwipeBackActivity.class));
    }

    public void edittext(View view) {
        startActivity(new Intent(this, CustomEditTextActivity.class));
    }

    public void progress(View view) {
        startActivity(new Intent(this, CircleProgressActivity.class));
    }

    public void tagImageView(View view) {
        startActivity(new Intent(this, TagImageViewActivity.class));
    }

    public void checkBox(View view) {
        startActivity(new Intent(this, CheckButtonActivity.class));
    }

    public void gif(View view) {
        startActivity(new Intent(this, GifViewActivity.class));
    }

    public void loadding(View view) {
        startActivity(new Intent(this, LoaddingActivity.class));
    }
}
