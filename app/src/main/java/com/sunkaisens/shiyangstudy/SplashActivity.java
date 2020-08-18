package com.sunkaisens.shiyangstudy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.sunkaisens.pull_to_refresh.DefaultPullToRefreshManager;
import com.sunkaisens.pull_to_refresh.PullToRefresh;
import com.sunkaisens.shiyangstudy.domain.Test01;
import com.sunkaisens.shiyangstudy.domain.Test02;

import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    PullToRefresh pullToRefresh;
    ScrollView scrollView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pullToRefresh = findViewById(R.id.pull_to_refresh);
        scrollView = findViewById(R.id.scrollView);
        final LinearLayout childAt = (LinearLayout) scrollView.getChildAt(0);

        pullToRefresh.setPullToRefreshManager(new DefaultPullToRefreshManager(this));
        pullToRefresh.setOnRefreshListener(new PullToRefresh.OnRefreshListener() {
            @Override
            public void onRefreshing() {
                pullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = new TextView(SplashActivity.this);
                        textView.setText("shiyangTest");
                        textView.setGravity(Gravity.CENTER);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.topMargin = 10;
                        params.bottomMargin = 10;
                        childAt.addView(textView, params);
                        pullToRefresh.refreshComplate();
                    }
                }, 1500);
            }
        });
    }


}
