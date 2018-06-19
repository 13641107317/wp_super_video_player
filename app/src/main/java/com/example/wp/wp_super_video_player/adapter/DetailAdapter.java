package com.example.wp.wp_super_video_player.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.entity.Album;
import com.example.wp.wp_super_video_player.entity.AlbumList;
import com.example.wp.wp_super_video_player.entity.Channel;
import com.example.wp.wp_super_video_player.utils.ImageUtils;

/**
 * Created by WangPeng on 2018/6/15.
 */
public class DetailAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private Channel mChannel;
    private AlbumList mAlbumList;
    private int mColunms;

    public DetailAdapter(Context context, Channel channel) {
        super();
        this.mChannel = channel;
        this.mContext = context;
        mAlbumList = new AlbumList();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.item_detail_list, null);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        view.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mAlbumList == null && mAlbumList.size() == 0) {
            return;
        }
        Album album = getItem(position);
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tv_name.setText(album.getTitle());
            if (album.getTip().isEmpty()) {
                itemViewHolder.tv_tip.setVisibility(View.GONE);
            } else {
                itemViewHolder.tv_tip.setText(album.getTip());
            }
            //重新计算宽高
            Point point = ImageUtils.getVerposterSize(mContext, mColunms);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(point.x, point.y);
            itemViewHolder.albumPoster.setLayoutParams(params);
            if (album.getVerImgUrl() != null) {
                ImageUtils.displayImage(itemViewHolder.albumPoster, album.getVerImgUrl(), point.x, point.y);
            } else {
                //TODO 默认图
            }

        }
    }

    private Album getItem(int position) {
        return mAlbumList.get(position);

    }

    //显示列数
    public void setColunms(int colunms) {
        mColunms = colunms;
    }

    public void addData(Album albnms) {
        mAlbumList.add(albnms);
    }

    @Override
    public int getItemCount() {
        if (mAlbumList != null && mAlbumList.size() > 0) {
            return mAlbumList.size();
        }
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout resultContainer;
        private ImageView albumPoster;
        private TextView tv_tip, tv_name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            resultContainer = itemView.findViewById(R.id.album_container);
            albumPoster = itemView.findViewById(R.id.iv_album_poster);
            tv_name = itemView.findViewById(R.id.tv_album_name);
            tv_tip = itemView.findViewById(R.id.tv_album_tip);
        }
    }
}
