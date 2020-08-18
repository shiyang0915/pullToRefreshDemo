package com.sunkaisens.shiyangstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author:shiyang
 * @date:2020-03-28
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class DashLineView extends View {

    private Paint mPaint;

    public DashLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#30333333"));
        mPaint.setStrokeWidth(10);
        mPaint.setPathEffect(new DashPathEffect(new float[] {20, 10}, 0));

    }
    @Override
    protected void onDraw(Canvas canvas) {
        int centerY = getHeight() / 2;
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        canvas.drawLine(0, centerY, getWidth(), centerY, mPaint);
    }

}
