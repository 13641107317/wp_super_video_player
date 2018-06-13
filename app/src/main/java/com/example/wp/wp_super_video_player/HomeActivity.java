package com.example.wp.wp_super_video_player;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wp.wp_super_video_player.api.Router;

@Route(path = Router.HOME_ACTIVITY)
public class HomeActivity extends BaseActivity {



    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        setSupportActionBar();
        setActionBarIcon(R.drawable.ic_drawer_home);
        setTitle("首页");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

}
