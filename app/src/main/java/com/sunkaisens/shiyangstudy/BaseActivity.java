package com.sunkaisens.shiyangstudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

/**
 * @author:shiyang
 * @date:2019-08-30
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void go(Activity activity, Class<?> clzss, String tag, Parcelable parcelable) {
        Intent intent = new Intent(activity, clzss);
        intent.putExtra(tag, parcelable);
        startActivity(intent);
    }


}
