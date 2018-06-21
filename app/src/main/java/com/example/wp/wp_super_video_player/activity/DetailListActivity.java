package com.example.wp.wp_super_video_player.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.adapter.SitePagerAdapter;
import com.example.wp.wp_super_video_player.api.Router;
import com.example.wp.wp_super_video_player.entity.Channel;
import com.example.wp.wp_super_video_player.indicator.CoolIndicatorLayout;
import com.example.wp.wp_super_video_player.indicator.IPagerIndicatorView;
import com.example.wp.wp_super_video_player.indicator.IPagerTitle;
import com.example.wp.wp_super_video_player.indicator.ViewPagerIndicatorAdapter;
import com.example.wp.wp_super_video_player.indicator.ViewPagerIndicatorLayout;
import com.example.wp.wp_super_video_player.indicator.ViewPagerIndicatorView;
import com.example.wp.wp_super_video_player.indicator.ViewPagerTitleView;
import com.example.wp.wp_super_video_player.indicator.ViewPagerWrapper;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

@Route(path = Router.DETAIL_ACTIVITY)
public class DetailListActivity extends BaseActivity {
    private static final String TAG = DetailListActivity.class.getSimpleName();
    @Autowired(name = "channel")
    Channel channel;
    @BindView(R.id.detail_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.viewpager_indicator)
    CoolIndicatorLayout mCoolIndicatorLayout;

    private String[] mSiteNames = new String[]{"搜狐视频", "乐视视频"};
    private List<String> mDataSet = Arrays.asList(mSiteNames);

    @Override
    public int setLayoutId() {
        return R.layout.activity_detail_list;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        if (channel == null) {
            new RuntimeException("channel is null!");
        }
        Log.i(TAG, "initView: " + channel.getChannelName());
        setSupportActionBar();
        setSupportArrowActionBar(true);
        setTitle(channel.getChannelName());

        ViewPagerIndicatorLayout indicatorLayout = new ViewPagerIndicatorLayout(this);
        indicatorLayout.setAdapter(new ViewPagerIndicatorAdapter() {
            @Override
            public int getCount() {
                return mDataSet.size();
            }

            @Override
            public IPagerTitle getPagerTitle(Context context, final int index) {
                ViewPagerTitleView titleView = new ViewPagerTitleView(context);
                titleView.setText(mDataSet.get(index));
                titleView.setNormalColor(Color.parseColor("#333333"));
                titleView.setSelectedClor(Color.parseColor("#e94220"));
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicatorView getIndicator(Context context) {
                ViewPagerIndicatorView viewPagerIndicatorView = new ViewPagerIndicatorView(context);
                viewPagerIndicatorView.setFillColor(Color.parseColor("#ebe4e3"));
                return viewPagerIndicatorView;
            }
        });
        mCoolIndicatorLayout.setPagerIndicatorLayout(indicatorLayout);
        ViewPagerWrapper.with(mCoolIndicatorLayout, mViewPager).compose();
        mViewPager.setAdapter(new SitePagerAdapter(getSupportFragmentManager(), getApplicationContext(), channel.getChannelId()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
