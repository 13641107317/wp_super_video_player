package com.example.wp.wp_super_video_player.utils;

import com.example.wp.wp_super_video_player.App;

import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by WangPeng on 2018/6/16.
 */
public class OkHttpUtils {
    private static final String REQUEST_TAG = "okhttp";
    public static Request buildRequest(String url){
        if (App.isNetWorkAvailable()){
            Request request = new Request.Builder()
                    .tag(REQUEST_TAG)
                    .url(url)
                    .build();
            return request;
        }
        return null;
    }
    public static void excute(String url, Callback callback){
        Request request = buildRequest(url);
        excute(request,callback);
    }
    public static void excute(Request request,Callback callback){
        App.getOkHttpClient().newCall(request).enqueue(callback);
    }
}
