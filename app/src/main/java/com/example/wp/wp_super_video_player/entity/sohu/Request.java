package com.example.wp.wp_super_video_player.entity.sohu;

import com.google.gson.annotations.Expose;

/**
 * Created by WangPeng on 2018/6/16.
 * 搜狐频道返回
 */
public class Request {

    @Expose
    private long status;

    @Expose
    private String statusText;

    //for 列表页
    @Expose
    private Data data;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
