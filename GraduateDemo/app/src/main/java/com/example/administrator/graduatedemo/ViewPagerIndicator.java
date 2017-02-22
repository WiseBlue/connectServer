package com.example.administrator.graduatedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/2/9.
 * ViewPager
 */

public class ViewPagerIndicator extends LinearLayout {

    private Paint mPaint;

    private Path mPath;

    private int mTriangleWidth;
    private int mTriangleHeight;

    private static final float RADIO_TRIANGLE_WIDTH=1/6F;

    private int mInitTranslationX;
    private int mTranslationX;

    public ViewPagerIndicator(Context context) {
        this(context,null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();

        canvas.translate(mInitTranslationX+mTranslationX,getHeight()+2);
        canvas.drawPath(mPath,mPaint);

        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth=(int) (w/4*RADIO_TRIANGLE_WIDTH);

        mInitTranslationX=w/4/2-mTriangleWidth/2;

        initTriangle();
    }

    private void initTriangle() {

        mTriangleHeight=mTriangleWidth/2;
        //初始化三角形
        mPath=new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth/2,-mTriangleHeight);
        mPath.close();
    }

    public void scroll(int position, float offset) {
        int tabWidth=getWidth()/4;
        mTranslationX=(int)(tabWidth*offset+tabWidth*position);
        invalidate();
    }
}

