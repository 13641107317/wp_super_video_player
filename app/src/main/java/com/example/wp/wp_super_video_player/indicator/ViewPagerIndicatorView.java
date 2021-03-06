package com.example.wp.wp_super_video_player.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.List;

/**
 * Created by WangPeng on 2018/6/19.
 * 指示器
 */
public class ViewPagerIndicatorView extends View implements IPagerIndicatorView {
    private Paint mPaint;
    private int mVerticalPadding;
    private int mHorizontalPadding;
    private List<PositionData> mPositionDataList;
    private RectF mRect = new RectF();
    //控制动画执行的速率
    private Interpolator  mStartInterpolator = new LinearInterpolator();
    private Interpolator  mEndInterpolator = new LinearInterpolator();
    // 指示器左右圆半径
    private int mRoundRadius;
    // 指示器颜色
    private int mFillColor;

    public ViewPagerIndicatorView(Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {
        //抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mVerticalPadding = dip2px(context, 6);
        mHorizontalPadding = dip2px(context, 8);
    }

    /**
     * 转换成像素
     *
     * @param context
     * @param dip
     * @return
     */
    private int dip2px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);

    }

    @Override
    public void setPositionData(List<PositionData> list) {
        mPositionDataList = list;
    }

    @Override
    public void onPagerSelected(int position) {

    }

    @Override
    public void onPagerScrolled(int position, float positionOffsetPercent, int positionOffsetPixel) {
        if (mPositionDataList == null || mPositionDataList.isEmpty()) {
            return;
        }
        int currentPosition = Math.min(mPositionDataList.size() - 1, position);
        int nextPosition = Math.min(mPositionDataList.size() - 1, position + 1);
        PositionData current = mPositionDataList.get(currentPosition);
        PositionData next = mPositionDataList.get(nextPosition);
        mRect.left = current.mContentLeft - mHorizontalPadding + (next.mContentLeft - current.mContentLeft) * mEndInterpolator.getInterpolation(positionOffsetPercent);
        mRect.top = current.mContentTop - mVerticalPadding;
        // 计算下一个和上个的差值*速率=滑动过距离(next.mContentRight - current.mContentRight)*mStartInterpolator.getInterpolation(positionOffsetPercent);
        mRect.right = current.mContentRight + mHorizontalPadding + (next.mContentRight - current.mContentRight)*mStartInterpolator.getInterpolation(positionOffsetPercent);
        mRect.bottom = current.mContentBottom + mVerticalPadding;
        mRoundRadius = (int) mRect.height() / 2;
        invalidate();
    }

    @Override
    public void onPagerScrollStateChanged(int position) {

    }

    public void setFillColor(int fillColor) {
        this.mFillColor = fillColor;
    }

    /**
     * 画指示器背景
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mFillColor);
        canvas.drawRoundRect(mRect,mRoundRadius,mRoundRadius,mPaint);
    }
}
