package com.example.wp.wp_super_video_player.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
        mRecyclerView.setScrollbarFadingEnabled(true);
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return isLoadMore || isRefresh;
            }
        });

        ImageView img = mFooter.findViewById(R.id.load_img);
        TextView tv = mFooter.findViewById(R.id.load_tv);
        img.setBackgroundResource(R.drawable.imooc_loading);
        //加载更多的动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();

        mFooter.setVisibility(GONE);
        //包含swipeRefreshLayout,recycleView,footerView
        this.addView(view);
    }

    class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if (!isRefresh) {
                refreshData();
            }
        }
    }

    private void refreshData() {

    }
}
