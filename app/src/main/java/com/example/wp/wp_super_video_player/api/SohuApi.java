package com.example.wp.wp_super_video_player.api;

import com.example.wp.wp_super_video_player.entity.Album;
import com.example.wp.wp_super_video_player.entity.AlbumList;
import com.example.wp.wp_super_video_player.entity.Channel;
import com.example.wp.wp_super_video_player.entity.ErrorInfo;
import com.example.wp.wp_super_video_player.entity.Site;
import com.example.wp.wp_super_video_player.entity.sohu.Request;
import com.example.wp.wp_super_video_player.entity.sohu.ResultAlbum;
import com.example.wp.wp_super_video_player.utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by WangPeng on 2018/6/15.
 */
public class SohuApi extends BaseSiteApi {
    private static final String TAG = SohuApi.class.getSimpleName();
    private static final int SOHU_CHANNELID_MOVIE = 1; //搜狐电影频道ID
    private static final int SOHU_CHANNELID_SERIES = 2; //搜狐电视剧频道ID
    private static final int SOHU_CHANNELID_VARIETY = 7; //搜狐综艺频道ID
    private static final int SOHU_CHANNELID_DOCUMENTRY = 8; //搜狐纪录片频道ID
    private static final int SOHU_CHANNELID_COMIC = 16; //搜狐动漫频道ID
    private static final int SOHU_CHANNELID_MUSIC = 24; //搜狐音乐频道ID

    //某一专辑详情
    //http://api.tv.sohu.com/v4/album/info/9112373.json?plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47
    private final static String API_KEY = "plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47";
    private final static String API_ALBUM_INFO = "http://api.tv.sohu.com/v4/album/info/";
    //http://api.tv.sohu.com/v4/search/channel.json?cid=2&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47&page=1&page_size=1
    private final static String API_CHANNEL_ALBUM_FORMAT = "http://api.tv.sohu.com/v4/search/channel.json" +
            "?cid=%s&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&" +
            "sver=6.2.0&sysver=4.4.2&partner=47&page=%s&page_size=%s";
    //http://api.tv.sohu.com/v4/album/videos/9112373.json?page=1&page_size=50&order=0&site=1&with_trailer=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47
    private final static String API_ALBUM_VIDOES_FORMAT = "http://api.tv.sohu.com/v4/album/videos/%s.json?page=%s&page_size=%s&order=0&site=1&with_trailer=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47";
    // 播放url
    //http://api.tv.sohu.com/v4/video/info/3669315.json?site=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=4.5.1&sysver=4.4.2&partner=47&aid=9112373
    private final static String API_VIDEO_PLAY_URL_FORMAT = "http://api.tv.sohu.com/v4/video/info/%s.json?site=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=4.5.1&sysver=4.4.2&partner=47&aid=%s";
    //真实url格式 m3u8
    //http://hot.vrs.sohu.com/ipad3669271_4603585256668_6870592.m3u8?plat=6uid=f5dbc7b40dad477c8516885f6c681c01&pt=5&prod=app&pg=1

    @Override
    void onGetChannelAlbums(Channel channel, int pagerNo, int pagerSize,
                            OnGetChannelAlbumListener listener) {

        String url = getChannelAlbumUrl(channel, pagerNo, pagerSize);
        doGetChannelAlbumByUrl(url, listener);
    }

    private void doGetChannelAlbumByUrl(final String url, final OnGetChannelAlbumListener listener) {
        OkHttpUtils.excute(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if (listener != null) {
                    ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumByUrl",
                            e, ErrorInfo.ERROR_TYPE_URL);
                    listener.onGetChannelAlunmsFailed(info);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful() && listener != null) {
                    ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumByUrl",
                            null, ErrorInfo.ERROR_TYPE_HTTP);
                    listener.onGetChannelAlunmsFailed(info);
                    return;
                }
                //取到数据映射model
                Request result = App.getGson().fromJson(response.body().string(), Request.class);
                AlbumList albnms = toConvertAlbumList(result);
                if (albnms != null && albnms.size() > 0 && listener != null) {
                    listener.onGetChannelAlunmsSuccess(albnms);
                } else {
                    ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumByUrl",
                            null, ErrorInfo.ERROR_TYPE_DATA_CONVERT);
                    listener.onGetChannelAlunmsFailed(info);
                }
            }
        });
    }

    private AlbumList toConvertAlbumList(Request request) {
        AlbumList list = null;
        if (request.getData().getResultAlbumList().size() > 0) {
            list = new AlbumList();
            for (ResultAlbum resultAlbum : request.getData().getResultAlbumList()) {
                Album album = new Album(Site.SOHU);
                album.setAlbumDesc(resultAlbum.getTvDesc());
                album.setAlbumId(resultAlbum.getAlbumId());
                album.setHorImgUrl(resultAlbum.getHorHighPic());
                album.setDirector(resultAlbum.getDirector());
                album.setMainActor(resultAlbum.getMainActor());
                album.setTip(resultAlbum.getTip());
                album.setTitle(resultAlbum.getAlbumName());
                album.setVerImgUrl(resultAlbum.getVerHighPic());

                list.add(album);
            }
            return list;
        }
        return null;
    }

    private ErrorInfo buildErrorInfo(String url, String functionName, Exception e,
                                     int type) {
        ErrorInfo errorInfo = new ErrorInfo(Site.SOHU, type);
        errorInfo.setFunctionName(functionName);
        errorInfo.setType(type);
        errorInfo.setUrl(url);
        errorInfo.setClassName(TAG);
        errorInfo.setTag(TAG);
        errorInfo.setExceptionString(e.getMessage());
        return errorInfo;
    }

    private String getChannelAlbumUrl(Channel channel, int pagerNo, int pagerSize) {

        return String.format(API_CHANNEL_ALBUM_FORMAT, toConvertChannelId(channel), pagerNo, pagerSize);

    }

    private int toConvertChannelId(Channel channel) {
        int channelId = -1;
        switch (channel.getChannelId()) {
            case Channel.SHOW:
                channelId = SOHU_CHANNELID_SERIES;
                break;
            case Channel.MOVIE:
                channelId = SOHU_CHANNELID_MOVIE;
                break;
            case Channel.COMIC:
                channelId = SOHU_CHANNELID_COMIC;
                break;
            case Channel.MUSIC:
                channelId = SOHU_CHANNELID_MUSIC;
                break;
            case Channel.DOCUMENTRY:
                channelId = SOHU_CHANNELID_DOCUMENTRY;
                break;
            case Channel.VARIETY:
                channelId = SOHU_CHANNELID_VARIETY;
                break;
        }
        return channelId;

    }
}
