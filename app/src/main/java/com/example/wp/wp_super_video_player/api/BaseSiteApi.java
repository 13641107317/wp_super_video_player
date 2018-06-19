package com.example.wp.wp_super_video_player.api;

import com.example.wp.wp_super_video_player.entity.Channel;

/**
 * Created by WangPeng on 2018/6/15.
 */
public abstract class BaseSiteApi {
    abstract void onGetChannelAlbums(Channel channel,int pagerNo,int pagerSize,
                                     OnGetChannelAlbumListener listener);
}
