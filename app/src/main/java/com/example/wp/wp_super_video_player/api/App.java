package com.example.wp.wp_super_video_player.api;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by wangpeng .
 */
public class App extends Application {
    private static Gson mGson;
    private static OkHttpClient mOkHttpClient;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
        mContext = this;
    }

    public static Gson getGson() {
        return mGson;
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static boolean isNetWorkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
