package com.example.wp.wp_super_video_player.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.base.BaseFragment;

/**
 * Created by WangPeng on 2018/6/14.
 */
@SuppressLint("ValidFragment")
public class DetailListFragment extends BaseFragment {
    private int mSiteId;
    private int mChannelId;

    @SuppressLint("ValidFragment")
    public DetailListFragment(int siteId, int channelId) {
        this.mChannelId = channelId;
        this.mSiteId = siteId;
    }

    public static DetailListFragment newInstance(int siteId, int channelId) {
        DetailListFragment fragment = new DetailListFragment(siteId, channelId);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_detail_list;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
