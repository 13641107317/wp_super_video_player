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
@Route(path = Router.BLOG_FRAGMENT)
public class BlogFragment extends BaseFragment {
    @Override
    protected Object setLayout() {
        return R.layout.fragment_blog;

    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
