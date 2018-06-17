package com.example.wp.wp_super_video_player.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by WangPeng on 2018/6/15.
 */
public class DetailAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public DetailAdapter(Context context) {
        super();
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

    @Override
    public int getItemCount() {
        return 0;
    }
}
