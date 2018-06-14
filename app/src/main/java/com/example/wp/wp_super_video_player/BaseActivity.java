package com.example.wp.wp_super_video_player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wangpeng .
 */
public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData(savedInstanceState);
    }

    protected void setSupportActionBar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected void setActionBarIcon(int res) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(res);
        }
    }

    public abstract int setLayoutId();

    public abstract void initView();

    public abstract void initData(@Nullable Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

}
