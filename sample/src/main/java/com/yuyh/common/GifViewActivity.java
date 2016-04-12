package com.yuyh.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yuyh.library.view.image.GifView;

public class GifViewActivity extends AppCompatActivity {

    private GifView gif1, gif2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_view);

        gif1 = (GifView) findViewById(R.id.gif1);
        // 设置背景gif图片资源
        gif1.setMovieResource(R.raw.pao);
        gif2 = (GifView) findViewById(R.id.gif2);
        gif2.setMovieResource(R.raw.rabbit);
        gif2.setClickable(true);
        gif2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置暂停

                if (gif2.isPaused()) {
                    gif2.setPaused(false);
                } else {
                    gif2.setPaused(true);
                }
            }
        });
    }
}
