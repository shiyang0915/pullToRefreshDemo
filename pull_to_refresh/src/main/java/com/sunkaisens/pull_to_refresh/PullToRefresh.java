package com.sunkaisens.pull_to_refresh;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;

/**
 * @author:shiyang
 * @date:2020-08-10
 * @email:shiyang@sunkaisens.com
 * @Description: 自定义下拉刷新框架
 */
public class PullToRefresh extends LinearLayout {

    //android 事件的消费会优先让子view去处理
    //如果ViewGroup很想处理这个事件怎么办  onInterceptTouchEvent  返回true
    //requestDisallowedInterceptTouchEvent(boolean disallowed) 请求自己的父布局不要拦截该事件

    private BasePullToRefreshManager pullToRefreshManager;
    private View viewPullToRefresh;
    int downY;
    private int minHeadViewHeight;
    //头部布局最小高度
    private int mHeadViewHeight;
    //头部布局最大高度
    private int maxHeadViewHeight;

    //初始化状态默认是静止状态
    private PullState mCurrentState = PullState.IDDLE;

    private OnRefreshListener onRefreshListener;

    private int downInterceptX;
    private int downInterceptY;
    private ScrollView mScrollView;
    private boolean isScrollToTop = true;


    /**
     * 刷新完毕
     */
    public void refreshComplate() {

        pullToRefreshManager.refreshComplate();
        mCurrentState = PullState.REFRESH_COMPLATE;

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                overRefresh();
            }
        }, 500);


    }

    public void overRefresh() {
        final LinearLayout.LayoutParams params = (LayoutParams) viewPullToRefresh.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(params.topMargin, minHeadViewHeight);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int values = (int) animation.getAnimatedValue();
                params.topMargin = values;
                viewPullToRefresh.setLayoutParams(params);
            }
        });


        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentState = PullState.IDDLE;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });

        animator.start();
    }


    public interface OnRefreshListener {
        void onRefreshing();
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }


    public PullToRefresh(Context context) {
        super(context);
    }

    public PullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setPullToRefreshManager(BasePullToRefreshManager pullToRefreshManager) {
        setOrientation(VERTICAL);
        this.pullToRefreshManager = pullToRefreshManager;

        initData();
    }


    private void initData() {

        viewPullToRefresh = pullToRefreshManager.initRefreshView();
        viewPullToRefresh.measure(0, 0);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewPullToRefresh.getMeasuredHeight());
        addView(viewPullToRefresh, 0, params);
        mHeadViewHeight = viewPullToRefresh.getMeasuredHeight();
        minHeadViewHeight = -mHeadViewHeight;
        maxHeadViewHeight = (int) (mHeadViewHeight * 0.3f);
        params.topMargin = -mHeadViewHeight;
        viewPullToRefresh.setLayoutParams(params);

    }


    public LinearLayout.LayoutParams getPullToFreshViewParams() {
        return (LayoutParams) viewPullToRefresh.getLayoutParams();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View childAt = this.getChildAt(0);
        if (childAt instanceof ScrollView) {
            mScrollView = (ScrollView) childAt;
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (mScrollView.getScrollY() == 0) {
                        //顶部
                        isScrollToTop = true;
                    } else {
                        isScrollToTop = false;
                    }

                    View contentView = mScrollView.getChildAt(0);
                    if (contentView != null && contentView.getMeasuredHeight() == (mScrollView.getScrollY() + mScrollView.getHeight())) {
                        //底部
                        isScrollToTop = false;
                    }

                }
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mCurrentState == PullState.IDDLE) {
                    downY = (int) event.getY();
                    return true;
                }

            case MotionEvent.ACTION_MOVE:
                if (mCurrentState == PullState.REFRESHING || mCurrentState == PullState.REFRESH_COMPLATE) {
                    return false;
                }
                int moveY = (int) event.getY();
                int dy = moveY - downY;
                if (dy > 0) {
                    //向下滑动
                    LinearLayout.LayoutParams params = (LayoutParams) viewPullToRefresh.getLayoutParams();
                    int topMargin = (int) Math.min(minHeadViewHeight + dy / 1.8f, maxHeadViewHeight);
                    params.topMargin = topMargin;

                    if (topMargin < 0) {
                        //headView没有完全显示出来之前
                        if (mCurrentState != PullState.DOWN_REFRESH) {
                            //这里改变状态只执行一遍，提高不必要的回调，提升程序性能。
                            mCurrentState = PullState.DOWN_REFRESH;
                            pullToRefreshManager.downRefresh();
                        }
                    } else {
                        //headView完整的显示出来。
                        if (mCurrentState != PullState.RELEASE_REFRESH) {
                            mCurrentState = PullState.RELEASE_REFRESH;
                            pullToRefreshManager.releaseRefresh();
                        }
                    }

                    viewPullToRefresh.setLayoutParams(params);

                }
                return true;

            case MotionEvent.ACTION_UP:
                if (handleEventUp()) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean handleEventUp() {
        final LinearLayout.LayoutParams params = (LayoutParams) viewPullToRefresh.getLayoutParams();
        if (mCurrentState == PullState.DOWN_REFRESH) {
            //当在下拉刷新状态的时候执行了up事件的话，就让刷新状态变为静止状态。
            overRefresh();
        } else if (mCurrentState == PullState.RELEASE_REFRESH) {
            params.topMargin = 0;
            viewPullToRefresh.setLayoutParams(params);
            mCurrentState = PullState.REFRESHING;
            pullToRefreshManager.refreshing();
            onRefreshListener.onRefreshing();
        }
        return params.topMargin > minHeadViewHeight;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downInterceptX = (int) ev.getX();
                downInterceptY = (int) ev.getY();
                downY = downInterceptY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (ev.getX() - downInterceptX);
                int dy = (int) (ev.getY() - downInterceptY);
                if (Math.abs(dy) > Math.abs(dx) && dy > 0 && isScrollToTop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;

            default:
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    enum PullState {

        /**
         * 静止
         */
        IDDLE,

        /**
         * 下拉刷新
         */
        DOWN_REFRESH,

        /**
         * 释放刷新
         */
        RELEASE_REFRESH,

        /**
         * 正在刷新
         */
        REFRESHING,

        /**
         * 刷新完毕
         */
        REFRESH_COMPLATE;

    }
}
