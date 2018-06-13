package com.example.wp.wp_super_video_player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wp.wp_super_video_player.api.Router;
import com.example.wp.wp_super_video_player.base.BaseFragment;

/**
 * Created by wangpeng .
 */
@Route(path = Router.HOME_FRAGMENT)
public class HomeFragment extends BaseFragment {
    @Override
    protected Object setLayout() {
        return R.layout.fragment_home;

    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
