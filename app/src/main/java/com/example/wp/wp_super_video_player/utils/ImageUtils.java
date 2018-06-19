package com.example.wp.wp_super_video_player.utils;

import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wp.wp_super_video_player.R;


/**
 * Created by WangPeng on 2018/6/18.
 */
public class ImageUtils {
    public static void displayImage(ImageView imageView, String url, int width, int height) {

        if (imageView != null && url != null && width > 0 && height > 0) {

            if (width>height){
                GlideApp
                        .with(imageView.getContext())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .override(height,width)
                        .error(R.drawable.ic_loading_hor)
                        .into(imageView);

            }
        }
    }
}
