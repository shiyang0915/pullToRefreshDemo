package com.sunkaisens.pull_to_refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author:shiyang
 * @date:2020-08-10
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class DefaultPullToRefreshManager extends BasePullToRefreshManager {

    private TextView textView;

    public DefaultPullToRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View initRefreshView() {
        View view = LayoutInflater.from(context).inflate(R.layout.default_pull_to_refresh, null);
        textView = view.findViewById(R.id.tv_show);
        return view;
    }

    @Override
    public void downRefresh() {
        //下拉刷新
        textView.setText("下拉刷新");
    }

    @Override
    public void releaseRefresh() {
        //释放刷新
        textView.setText("释放刷新");
    }

    @Override
    public void refreshing() {
        //正在刷新
        textView.setText("正在刷新");
    }

    @Override
    public void refreshComplate() {
        //刷新成功
        textView.setText("刷新成功");
    }

}
