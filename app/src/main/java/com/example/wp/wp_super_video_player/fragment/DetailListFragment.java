package com.example.wp.wp_super_video_player.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.base.BaseFragment;
import com.example.wp.wp_super_video_player.widget.IRecycleViewRefreshOrLoad;
import com.example.wp.wp_super_video_player.widget.PullLoadRecycleView;

import butterknife.BindView;

/**
 * Created by WangPeng on 2018/6/14.
 */

public class DetailListFragment extends BaseFragment implements IRecycleViewRefreshOrLoad{
    private static final String CHANNEL_ID = "channelId";
    private static final String SITE_ID = "siteId";
    @BindView(R.id.rv_fragment_detail)
    PullLoadRecycleView mRecycleView;

    public static DetailListFragment newInstance(int siteId, int channelId) {
        DetailListFragment fragment = new DetailListFragment();
        Bundle args = new Bundle();
        args.putInt(CHANNEL_ID,channelId);
        args.putInt(SITE_ID,siteId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_detail_list;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
            mRecycleView.setGridLayout(3);
            mRecycleView.setRecycleViewRefreshOrLoadListener(this);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
