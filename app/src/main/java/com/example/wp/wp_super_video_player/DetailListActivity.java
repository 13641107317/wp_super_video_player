package com.example.wp.wp_super_video_player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_super_video_player.adapter.SitePagerAdapter;
import com.example.wp.wp_super_video_player.api.Router;
import com.example.wp.wp_super_video_player.entity.Channel;

import butterknife.BindView;

@Route(path = Router.DETAIL_ACTIVITY)
public class DetailListActivity extends BaseActivity {
    private static final String TAG = DetailListActivity.class.getSimpleName();
    @Autowired(name = "channel")
    Channel channel;
    @BindView(R.id.detail_viewpager)
    ViewPager mViewPager;

    @Override
    public int setLayoutId() {
        return R.layout.activity_detail_list;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        if (channel == null){
            new RuntimeException("channel is null!");
        }
        Log.i(TAG, "initView: "+channel.getChannelName());
        setSupportActionBar();
        setSupportArrowActionBar(true);
        setTitle(channel.getChannelName());

        mViewPager.setAdapter(new SitePagerAdapter(getSupportFragmentManager(),getApplicationContext(),channel.getChannelId()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
