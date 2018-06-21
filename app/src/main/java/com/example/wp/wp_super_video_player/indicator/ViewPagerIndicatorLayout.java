package com.example.wp.wp_super_video_player.indicator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.wp.wp_super_video_player.R;

import java.util.List;

/**
 * Created by wangpeng .
 */
public class ViewPagerIndicatorLayout extends FrameLayout implements IPagerIndicatorLayout, IPagerTitle {
    private HorizontalScrollView mHorizontalScrollView;
    private LinearLayout mTitleContainer, mIndcatorContainer;
    private ViewPagerIndicatorHelper mViewPagerIndicatorHelper;
    private ViewPagerIndicatorAdapter mAdapter;
    private IPagerIndicatorView mIndicatorView;
    private List<PositionEntity> mPositionData;

    public ViewPagerIndicatorLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mViewPagerIndicatorHelper = new ViewPagerIndicatorHelper();
        mViewPagerIndicatorHelper.setScrollListener(this);
        removeAllViews();
        final View view = LayoutInflater.from(context).inflate(R.layout.pager_indicator_layout, null);
        mHorizontalScrollView = view.findViewById(R.id.horizomtal_scrollview);
        mTitleContainer = view.findViewById(R.id.ll_title_container);
        mIndcatorContainer = view.findViewById(R.id.ll_indicator_container);
        initTitleAndIndicator();
    }

    /**
     * 添加title及indicator到对应容器
     */
    private void initTitleAndIndicator() {
        for (int i = 0, j = mViewPagerIndicatorHelper.getTotalCount(); i < j; i++) {
            if (mAdapter != null) {
                IPagerTitle v = mAdapter.getPagerTitle(getContext(), i);
                if (v instanceof ViewPagerTitleView) {
                    View view = (View) v;
                    LinearLayout.LayoutParams params = new LinearLayout.
                            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                    mTitleContainer.addView(view, params);
                }
            }
            if (mAdapter != null) {
                mIndicatorView = mAdapter.getIndicator(getContext());
                if (mIndicatorView instanceof View) {
                    LinearLayout.LayoutParams params = new LinearLayout.
                            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                    mIndcatorContainer.addView((View) mIndicatorView, params);
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mAdapter !=null){
            preParePositionData();
            if (mIndicatorView !=null){
                mIndicatorView.setPositionData(mPositionData);
            }
            if (mViewPagerIndicatorHelper.getScrollState() == ViewPager.SCROLL_STATE_IDLE){
                //TODO 通知出去
            }
        }
    }

    /**
     * 给indicator准备数据
     */
    private void preParePositionData() {
        mPositionData.clear();
        for (int i = 0, j = mViewPagerIndicatorHelper.getTotalCount(); i < j; i++) {
            PositionEntity data = new PositionEntity();
            View child = mTitleContainer.getChildAt(i);
            if (child != null) {
                data.mLeft = child.getLeft();
                data.mRight = child.getRight();
                data.mBottom = child.getBottom();
                data.mTop = child.getTop();
            }
            //文字
            if (child instanceof IViewPagerTitleView) {
                data.mContentLeft = ((IViewPagerTitleView) child).getContentLeft();
                data.mContentRight = ((IViewPagerTitleView) child).getContentRight();
                data.mContentTop = ((IViewPagerTitleView) child).getContentTop();
                data.mContentBottom = ((IViewPagerTitleView) child).getContentBottom();
            } else {
                data.mContentLeft = child.getLeft();
                data.mContentRight = child.getRight();
                data.mContentTop = child.getTop();
                data.mContentBottom = child.getBottom();
            }
            mPositionData.add(data);
        }
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
