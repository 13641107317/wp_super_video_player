package com.example.wp.wp_super_video_player.indicator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by wangpeng .
 * 供外部布局引用
 */

public class CoolIndicatorLayout extends FrameLayout {

    private IPagerIndicatorLayout mPagerIndicatorLayout;
    public CoolIndicatorLayout(@NonNull Context context) {
        super(context);
    }

    public CoolIndicatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPagerIndicatorLayout(IPagerIndicatorLayout pagerIndicatorLayout) {
        if (mPagerIndicatorLayout == pagerIndicatorLayout){
            return;
        }
        if (mPagerIndicatorLayout !=null){
            mPagerIndicatorLayout.onDetachCoolIndicatorLayout();
        }
        this.mPagerIndicatorLayout = pagerIndicatorLayout;
        removeAllViews();
        if (mPagerIndicatorLayout!=null){
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView((View) mPagerIndicatorLayout,lp);
            mPagerIndicatorLayout.onAttachCoolIndicatorLayout();
        }
    }
    public void onPagerSelected(int position) {
        if (mPagerIndicatorLayout != null) {
            mPagerIndicatorLayout.onPagerSelected(position);
        }
    }

    public void onPagerScrolled(int position, float positionOffsetPercent, int positionOffsetPixel) {
        if (mPagerIndicatorLayout != null) {
            mPagerIndicatorLayout.onPagerScrolled(position, positionOffsetPercent, positionOffsetPixel);
        }
    }

    public void onPagerScrollStateChanged(int state) {
        if (mPagerIndicatorLayout != null) {
            mPagerIndicatorLayout.onPagerScrollStateChanged(state);
        }
    }
}
