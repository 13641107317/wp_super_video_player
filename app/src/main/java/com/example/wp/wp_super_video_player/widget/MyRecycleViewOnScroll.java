package com.example.wp.wp_super_video_player.widget;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wangpeng .
 */
public class MyRecycleViewOnScroll extends RecyclerView.OnScrollListener {
    private int firstItem = 0;
    private int lastItem = 0;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private boolean mIsLoadMore;
    private int totalCount = 0;
    private IRecycleViewRefreshOrLoad mRefreshOrLoad;

    public MyRecycleViewOnScroll(SwipeRefreshLayout mSwipeRefreshLayout, boolean isLoadMore, IRecycleViewRefreshOrLoad refreshOrLoad) {
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        this.mIsLoadMore = isLoadMore;
        this.mRefreshOrLoad = refreshOrLoad;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        totalCount = layoutManager.getItemCount();
        //如果是网格的
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        if (layoutManager instanceof GridLayoutManager) {
            //第一个完全可见
            firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
            //最后一个完全可见
            lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
            if (firstItem == 0 || firstItem == RecyclerView.NO_POSITION) {
                int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                lastItem = lastVisibleItemPosition;
            }
        }

        //什么时候出发加载更多
        if (mSwipeRefreshLayout.isEnabled()) {
            mSwipeRefreshLayout.setEnabled(true);
        } else {
            mSwipeRefreshLayout.setEnabled(false);
        }
        if (!mIsLoadMore && totalCount == lastItem && (dx > 0 || dy > 0)) {
            mRefreshOrLoad.loadMore();
        }
    }

}
