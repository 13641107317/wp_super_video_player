package com.example.wp.wp_super_video_player.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.adapter.DetailAdapter;
import com.example.wp.wp_super_video_player.base.BaseFragment;
import com.example.wp.wp_super_video_player.entity.Site;
import com.example.wp.wp_super_video_player.widget.IRecycleViewRefreshOrLoad;
import com.example.wp.wp_super_video_player.widget.PullLoadRecycleView;

import butterknife.BindView;

/**
 * Created by WangPeng on 2018/6/14.
 */

public class DetailListFragment extends BaseFragment implements IRecycleViewRefreshOrLoad {
    private static final String CHANNEL_ID = "channelId";
    private static final String SITE_ID = "siteId";
    private static final int REFRESH_TIME = 1500;
    private static final int LOADMORE_TIME = 3000;
    @BindView(R.id.rv_fragment_detail)
    PullLoadRecycleView mRecycleView;
    @BindView(R.id.tv_failed)
    TextView mEmptyView;
    private int mSiteId;
    private int mChannelId;
    private int mColunms;
    private DetailAdapter adapter;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static DetailListFragment newInstance(int siteId, int channelId) {
        DetailListFragment fragment = new DetailListFragment();
        Bundle args = new Bundle();
        args.putInt(CHANNEL_ID, channelId);
        args.putInt(SITE_ID, siteId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_detail_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DetailAdapter(getActivity());
        if (mSiteId == Site.LETV) {
            mColunms = 2;
            adapter.setColunms(mColunms);
        }
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRecycleView.setGridLayout(3);
        mRecycleView.setAdapter(adapter);
        mRecycleView.setRecycleViewRefreshOrLoadListener(this);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                refreshData();
                mRecycleView.setRefreshCompleted();
            }
        }, REFRESH_TIME);
    }

    private void refreshData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

               loadData();
                mRecycleView.setLoadMoreCompleted();
            }
        }, LOADMORE_TIME);
    }

    private void loadData() {

    }
}
