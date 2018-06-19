package com.example.wp.wp_super_video_player.entity;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by WangPeng on 2018/6/16.
 */
public class AlbumList extends ArrayList<Albnm> {
    private static final String TAG = AlbumList.class.getSimpleName();

    public void deBug() {
        for (Albnm a : this) {
            Log.i(TAG, "albumList: " + a.toString());
        }
    }
}
