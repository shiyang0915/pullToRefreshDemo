package com.sunkaisens.pull_to_refresh;

import android.content.Context;
import android.view.View;

/**
 * @author:shiyang
 * @date:2020-08-10
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public abstract class BasePullToRefreshManager {

    public Context context;

    public BasePullToRefreshManager(Context context){
        this.context = context;
    }

    public abstract View initRefreshView();

    public abstract void downRefresh();

    public abstract void releaseRefresh();

    public abstract void refreshing();

    public abstract void refreshComplate();
}
