package com.example.wp.wp_super_video_player.api;

import android.content.Context;

import com.example.wp.wp_super_video_player.entity.Channel;
import com.example.wp.wp_super_video_player.entity.Site;

/**
 * Created by WangPeng on 2018/6/15.
 */
public class SiteApi {
    public static void onGetChannelAlums(Context context, int pagerNo, int pagerSize, int sideId,
                                  int channelId, OnGetChannelAlunmListener listener) {
        switch (sideId) {
            case Site.LETV:
                new LetvApi().onGetChannelAlbums(new Channel(channelId, context),
                        pagerNo, pagerSize, listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetChannelAlbums(new Channel(channelId, context),
                        pagerNo, pagerSize, listener);
                break;
            default:
                break;
        }
    }
}
