package com.yuyh.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yuyh.library.view.progress.ArcProgress;
import com.yuyh.library.view.progress.CircleProgress;
import com.yuyh.library.view.progress.DonutProgress;

import static com.yuyh.common.R.id.cir_progress;

public class CircleProgressActivity extends AppCompatActivity {

    ArcProgress arcProgress;
    CircleProgress circleProgress;
    DonutProgress donutProgress;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        arcProgress = (ArcProgress) findViewById(R.id.arc_progress);
        circleProgress = (CircleProgress) findViewById(cir_progress);
        donutProgress = (DonutProgress) findViewById(R.id.dou_progress);


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = 0; i <= 100; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            arcProgress.setProgress(i);
                            circleProgress.setProgress(i);
                            donutProgress.setProgress(i);
                        }
                    });

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i == 100)
                        i = 0;
                }
            }
        }).start();
    }
}
