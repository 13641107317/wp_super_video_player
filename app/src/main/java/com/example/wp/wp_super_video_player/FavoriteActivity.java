package com.example.wp.wp_super_video_player;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.example.wp.wp_super_video_player.base.BaseActivity;

public class FavoriteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }

    @Override
    public int setLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
