package com.example.wp.wp_super_video_player.indicator;

import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.util.SparseBooleanArray;

/**
 * Created by wangpeng .
 */
public class ViewPagerIndicatorHelper {
    private IPagerTitle mScrollListener;
    private int mCurrentIndex;
    private int mTotalCount;
    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;
    private int mLaseIndex;
    private float mLastPositionOffsetSum;
    private SparseArray<Float> mLeavePercents = new SparseArray<>();
    private SparseBooleanArray mDisSelectedItems = new SparseBooleanArray();

    public void setScrollListener(IPagerTitle listener) {
        this.mScrollListener = listener;
    }

    public ViewPagerIndicatorHelper() {
    }

    public void onPagerScrolled(int position, float positionOffset, int positionPixel) {
        float currentPositionOffsetSum = position + positionOffset;
        boolean isLeftToRight = currentPositionOffsetSum > mLastPositionOffsetSum;
        int safePosition = getSafeIndex(position);
        //不是就绪状态
        if (mScrollState != ViewPager.SCROLL_STATE_IDLE) {
            int enterIndex, leaveIndex;
            float enterPercent, leavePercent;
            if (isLeftToRight) {
                enterIndex = getSafeIndex(position + 1);
                enterPercent = positionOffset;
                leaveIndex = safePosition;
                leavePercent = positionOffset;

            } else {
                enterIndex = safePosition;
                enterPercent = 1.0f - positionOffset;
                leaveIndex = getSafeIndex(safePosition + 1);
                leavePercent = 1.0f - positionOffset;
            }
            for (int i = 0; i < mTotalCount; i++) {
                if (i == enterIndex || i == leaveIndex) {
                    continue;
                }
                leavePercent = mLeavePercents.get(i, 0.0f);
                if (leavePercent != 1.0f) {
                    mScrollListener.onLeave(i, mTotalCount, 1.0f, isLeftToRight);
                    mLeavePercents.put(i, 1.0f);

                }
            }
            if (enterIndex == leaveIndex) {
                if (enterIndex == mTotalCount - 1 && mLeavePercents.get(enterIndex, 0.0f) != 0.0f &&
                        enterPercent == 0.0f && isLeftToRight) {
                    boolean dispatchEnterEvent = mScrollState ==
                            ViewPager.SCROLL_STATE_DRAGGING || enterIndex == mCurrentIndex;
                    if (dispatchEnterEvent) {
                        mScrollListener.onEnter(enterIndex, mTotalCount, 1.0f, true);
                        mLeavePercents.put(enterIndex, 0.0f);
                    }
                }
                return;
            }
            if (1.0f - mLeavePercents.get(enterIndex, 0.0f) != enterPercent) {
                boolean dispatchEnterEvent = mScrollState ==
                        ViewPager.SCROLL_STATE_DRAGGING || enterIndex == mCurrentIndex;
                if (dispatchEnterEvent) {
                    mScrollListener.onEnter(enterIndex, mTotalCount, enterPercent, isLeftToRight);
                    mLeavePercents.put(enterIndex, 1.0f - enterPercent);
                }
            }
            if (mLeavePercents.get(leaveIndex, 0.0f) != leavePercent) {
                if (isLeftToRight && leaveIndex == getSafeIndex(mCurrentIndex) && leavePercent == 0.0f) {
                    boolean dispatchEnterEvent = mScrollState ==
                            ViewPager.SCROLL_STATE_DRAGGING || leaveIndex == mCurrentIndex;
                    if (dispatchEnterEvent) {
                        mScrollListener.onEnter(leaveIndex, mTotalCount, 1.0f, true);
                        mLeavePercents.put(leaveIndex, 0.0f);
                    }
                } else {
                    boolean dispatchLeaveEvent = mScrollState ==
                            ViewPager.SCROLL_STATE_DRAGGING || leaveIndex == mLaseIndex
                            || (leaveIndex == mCurrentIndex - 1) && mLeavePercents.get(leaveIndex, 0.0f) != 1.0f
                            || (leaveIndex == mCurrentIndex + 1) && mLeavePercents.get(leaveIndex, 0.0f) != 1.0f;
                    if (dispatchLeaveEvent) {
                        mScrollListener.onLeave(leaveIndex, mTotalCount, leavePercent, isLeftToRight);
                    }
                }
            }
            //滚动状态时
        } else {
            for (int i = 0; i < mTotalCount; i++) {
                if (i == mCurrentIndex) {
                    continue;
                }
                boolean disSelected = mDisSelectedItems.get(i);
                if (!disSelected) {
                    mScrollListener.onDisSelected(i, mTotalCount);
                    Float leavePercent = mLeavePercents.get(i, 0.0f);
                    if (leavePercent != 1.0f) {
                        mScrollListener.onLeave(i, mTotalCount, 1.0f, isLeftToRight);
                        mLeavePercents.put(i, 1.0f);
                    }
                }
                mScrollListener.onEnter(mCurrentIndex, mTotalCount, 1.0f, false);
                mLeavePercents.put(mCurrentIndex, 0.0f);
                mScrollListener.onSelected(mCurrentIndex, mTotalCount);
                mDisSelectedItems.put(mCurrentIndex, false);
            }
            mLastPositionOffsetSum = currentPositionOffsetSum;
        }
    }

    private int getSafeIndex(int position) {
        return Math.max(Math.min(position, mTotalCount - 1), 0);

    }

    public void onPagerSelected(int position) {

    }
}
