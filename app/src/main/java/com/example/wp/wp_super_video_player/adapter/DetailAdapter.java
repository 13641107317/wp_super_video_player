package com.example.wp.wp_super_video_player.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.wp.wp_super_video_player.entity.Albnm;
import com.example.wp.wp_super_video_player.entity.Channel;

/**
 * Created by WangPeng on 2018/6/15.
 */
public class DetailAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private Channel mChannel;

    public DetailAdapter(Context context, Channel channel) {
        super();
        this.mChannel =channel;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    public void setColunms(int colunms) {

    }
    public void addData(Albnm albnms) {
        //TODO
    }
    @Override
    public int getItemCount() {
        return 0;
    }
}
