package com.example.wp.wp_super_video_player.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wp.wp_super_video_player.R;

/**
 * Created by wangpeng .
 */
public class PullLoadRecycleView extends LinearLayout {

    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private View mFooter;
    private Context mContext;
    private boolean isRefresh = false;//是否刷新
    private boolean isLoadMore = false;//是否加载更多

    private IRecycleViewRefreshOrLoad mRefreshOrLoad;
    private AnimationDrawable mAnimationDrawable;

    public PullLoadRecycleView(Context context) {
        super(context);
        initView(context);
    }

    public PullLoadRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullLoadRecycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("ResourceAsColor")
    private void initView(Context context) {
        mContext = context;
        final View view = LayoutInflater.from(mContext).inflate(R.layout.pull_loadmore, null);
        mSwipeLayout = view.findViewById(R.id.swipe_layout);
        mRecyclerView = view.findViewById(R.id.rv_loadmore);
        mFooter = view.findViewById(R.id.footer_view);
        //设置空间刷新时progress颜色渐变
        mSwipeLayout.setColorSchemeColors(android.R.color.holo_green_light, android.R.color.holo_blue_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayoutOnRefresh());
        //设置固定大小
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //滚动条隐藏
        mRecyclerView.setVerticalScrollBarEnabled(false);

        mRecyclerView.addOnScrollListener(new MyRecycleViewOnScroll());
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return isLoadMore || isRefresh;
            }
        });
        //加载更多图片
        ImageView img = mFooter.findViewById(R.id.load_img);
        //loadMore文字
        TextView tv = mFooter.findViewById(R.id.load_tv);
        //加载更多的动画
        img.setBackgroundResource(R.drawable.imooc_loading);
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();

        mFooter.setVisibility(GONE);
        //包含swipeRefreshLayout,recycleView,footerView
        this.addView(view);
    }

    public void setRecycleViewRefreshOrLoadListener(IRecycleViewRefreshOrLoad listener) {
        mRefreshOrLoad = listener;
    }

    /**
     * 外部可以设置recycleView的列数
     *
     * @param spanCount
     */
    public void setGridLayout(int spanCount) {
        GridLayoutManager manager = new GridLayoutManager(mContext, spanCount);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    /**
     * 设置刷新完成
     */
    public void setRefreshCompleted() {
        isRefresh = false;
        setRefreshing(false);
    }

    public void setLoadMoreCompleted() {
        isRefresh = false;
        isLoadMore = false;
        setRefreshing(false);

    }

    //设置是否正在刷新数据
    private void setRefreshing(final boolean isRefreshing) {
        mSwipeLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(isRefreshing);
            }
        });
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if (!isRefresh) {
                refreshData();

            }
        }
    }

    class MyRecycleViewOnScroll extends RecyclerView.OnScrollListener {
        private int firstItem = 0;
        private int lastItem = 0;
        private int totalCount = 0;

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
            //什么时候触发加载更多
            if (!isLoadMore
                    && totalCount - 1 == lastItem
                    && mSwipeLayout.isEnabled() &&
                    !isRefresh
                    && (dx > 0 || dy > 0)) {
                //在加载更多时,禁止mSwipeRefreshLayout使用
                mSwipeLayout.setEnabled(false);
                loadMoreData();
            } else {
                mSwipeLayout.setEnabled(true);
            }
        }
    }

    private void refreshData() {
        if (mRefreshOrLoad != null) {
            mRefreshOrLoad.onRefresh();
        }
    }

    private void loadMoreData() {
        if (mRefreshOrLoad != null) {
            mFooter.animate()
                    .translationY(mFooter.getHeight())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            mFooter.setVisibility(View.VISIBLE);
                            //开始动画
                            mAnimationDrawable.start();
                        }
                    }).start();
            invalidate();
            mRefreshOrLoad.loadMore();
        }
    }

}
