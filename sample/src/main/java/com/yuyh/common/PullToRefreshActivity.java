package com.yuyh.common;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuyh.library.view.list.RefreshListView;
import com.yuyh.library.view.listener.OnRefreshListener;

public class PullToRefreshActivity extends AppCompatActivity {

    private RefreshListView pullListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        pullListView = (RefreshListView) findViewById(R.id.pullListView);
        pullListView.setAdapter(new MyAdapter());
        pullListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullListView.onRefreshComplete();
                    }
                }, 2000);

            }
        });
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public String getItem(int position) {
            return "测试item"+position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(PullToRefreshActivity.this);
            tv.setText(getItem(position));
            return tv;
        }
    }

}
