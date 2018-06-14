package com.example.wp.wp_super_video_player;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
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
        setSupportActionBar();
        setActionBarIcon(R.drawable.titlebar_return_white);
        if (channel != null){
            setTitle(channel.getChannelName());
        }
        mToolbar.setClickable(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
