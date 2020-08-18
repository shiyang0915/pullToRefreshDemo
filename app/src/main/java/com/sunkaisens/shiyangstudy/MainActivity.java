package com.sunkaisens.shiyangstudy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textAddress;
    private TextView textTime;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSystemBarBg();
        textAddress = findViewById(R.id.tv_address);
        textAddress.setText(getIntent().getStringExtra("address"));


        textTime = findViewById(R.id.tv_time);
        textTime.setText("登记时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
        imageView=findViewById(R.id.iv_pass);


        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        //开始动画
        animationDrawable.start();

    }

    private void setSystemBarBg(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#5890ED"));
        }
    }



}
