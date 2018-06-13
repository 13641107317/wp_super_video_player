package com.example.wp.wp_super_video_player;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_super_video_player.api.Router;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences = null;

    private static final int GO_HOME = 1;
    private static final int GO_GUIDE = 2;
    private static final int END_TIME = 2000;
    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    ARouter.getInstance().build(Router.HOME_ACTIVITY).navigation();
                    finish();
                    break;
                case GO_GUIDE:
                    ARouter.getInstance().build(Router.GUIDE_ACTIVITY).navigation();
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        init();
    }

    private void init() {
        boolean isFirstIn = mSharedPreferences.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, END_TIME);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_HOME, END_TIME);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler = null;
    }
}
