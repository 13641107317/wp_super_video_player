package com.example.wp.wp_super_video_player.api;

import com.example.wp.wp_super_video_player.entity.AlbumList;
import com.example.wp.wp_super_video_player.entity.ErrorInfo;

/**
 * Created by WangPeng on 2018/6/15.
 */
public interface OnGetChannelAlunmListener {
    void onGetChannelAlunmsSuccess(AlbumList albnms);
    void onGetChannelAlunmsFailed(ErrorInfo errorInfo);
}
