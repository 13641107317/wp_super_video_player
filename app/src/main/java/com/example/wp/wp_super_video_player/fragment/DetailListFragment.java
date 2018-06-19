package com.example.wp.wp_super_video_player.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wp.wp_super_video_player.App;
import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.adapter.DetailAdapter;
import com.example.wp.wp_super_video_player.api.OnGetChannelAlbumListener;
import com.example.wp.wp_super_video_player.api.SiteApi;
import com.example.wp.wp_super_video_player.base.BaseFragment;
import com.example.wp.wp_super_video_player.entity.Album;
import com.example.wp.wp_super_video_player.entity.AlbumList;
import com.example.wp.wp_super_video_player.entity.Channel;
import com.example.wp.wp_super_video_player.entity.ErrorInfo;
import com.example.wp.wp_super_video_player.entity.Site;
import com.example.wp.wp_super_video_player.widget.IRecycleViewRefreshOrLoad;
import com.example.wp.wp_super_video_player.widget.PullLoadRecycleView;

import butterknife.BindView;

/**
 * Created by WangPeng on 2018/6/14.
 */

public class DetailListFragment extends BaseFragment implements IRecycleViewRefreshOrLoad {
    private static final String TAG = DetailListFragment.class.getSimpleName();
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
    private int mColumns;
    private DetailAdapter adapter;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private int pagerNo;
    private int pagerSize = 30;

    private Context mContext;

    public static DetailListFragment newInstance(int siteId, int channelId) {
        DetailListFragment fragment = new DetailListFragment();
        Bundle args = new Bundle();
        args.putInt(CHANNEL_ID, channelId);
        args.putInt(SITE_ID, siteId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_detail_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            throw new RuntimeException("getArguments() is null!!");
        }
        mSiteId = getArguments().getInt(SITE_ID);
        mChannelId = getArguments().getInt(CHANNEL_ID);
        adapter = new DetailAdapter(mContext, new Channel(mChannelId, mContext));
        loadData();
        if (mSiteId == Site.LETV) {
            mColumns = 2;
            adapter.setColunms(mColumns);
        } else {
            mColumns = 3;
            adapter.setColunms(mColumns);
        }
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mEmptyView.setText("正在加载数据...");
        mRecycleView.setGridLayout(mColumns);
        mRecycleView.setAdapter(adapter);
        mRecycleView.setRecycleViewRefreshOrLoadListener(this);

    }

    @Override
    public void loadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                mRecycleView.setLoadMoreCompleted();
            }
        }, LOADMORE_TIME);
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
        pagerNo = 0;
        adapter = null;
        adapter = new DetailAdapter(mContext, new Channel(mChannelId, mContext));
        loadData();
        if (mSiteId == Site.LETV) {
            mColumns = 2;
            adapter.setColunms(mColumns);
        } else {
            mColumns = 3;
            adapter.setColunms(mColumns);
        }
        mRecycleView.setAdapter(adapter);
        Toast.makeText(mContext, "已经是最新数据了", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        pagerNo++;
        SiteApi.onGetChannelAlums(App.getmContext(), pagerNo, pagerSize, mSiteId, mChannelId, new OnGetChannelAlbumListener() {
            @Override
            public void onGetChannelAlunmsSuccess(AlbumList albnms) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mEmptyView.setVisibility(View.GONE);
                    }
                });
                for (Album album : albnms) {
                    adapter.addData(album);
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onGetChannelAlunmsFailed(ErrorInfo errorInfo) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mEmptyView.setText(getActivity().getResources().getString(R.string.data_failed_tip));
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler = null;
        mContext = null;
    }
}
