package com.sunkaisens.shiyangstudy.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author:shiyang
 * @date:2019-12-24
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super(MyIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("MyIntentService", "+++++++++++++++");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("MyIntentService", "---------------");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyIntentService", "onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("MyIntentService", "onStart");
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("MyIntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyIntentService", "onDestroy");
    }
}
