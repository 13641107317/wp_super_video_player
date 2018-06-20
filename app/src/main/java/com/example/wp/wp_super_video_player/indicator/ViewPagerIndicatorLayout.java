package com.example.wp.wp_super_video_player.indicator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

/**
 * Created by wangpeng .
 */
public class ViewPagerIndicatorLayout extends FrameLayout implements IPagerIndicatorLayout,IPagerTitle {
    private ViewPagerIndicatorHelper mViewPagerIndicatorHelper;
    public ViewPagerIndicatorLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mViewPagerIndicatorHelper = new ViewPagerIndicatorHelper();
        mViewPagerIndicatorHelper.setScrollListener(this);
    }

    @Override
    public void onAttachCoolIndicatorLayout() {

    }

    @Override
    public void onDetachCoolIndicatorLayout() {

    }

    @Override
    public void onSelected(int index, int totalCount) {

    }

    @Override
    public void onDisSelected(int index, int totalCount) {

    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean isLeftToRight) {

    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean isLeftToRight) {

    }
}
