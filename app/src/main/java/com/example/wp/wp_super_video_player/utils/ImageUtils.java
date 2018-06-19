package com.example.wp.wp_super_video_player.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wp.wp_super_video_player.R;


/**
 * Created by WangPeng on 2018/6/18.
 */
public class ImageUtils {

    private static final float VER_POSTER_RATIO = 0.73f;
    private static final float HOR_POSTER_RATIO = 1.5f;
    public static void displayImage(ImageView imageView, String url, int width, int height) {
        if (imageView != null && url != null && width > 0 && height > 0) {
            if (width > height) {
                GlideApp.with(imageView.getContext())
                        .load(url) //加载图片url
                        .diskCacheStrategy(DiskCacheStrategy.ALL)// 设置缓存
                        .error(R.drawable.ic_loading_hor)//出错时使用默认图
                        .fitCenter()//设置图片居中, centerCrop会截断大图,不会自适应, fitCenter居中自适应
                        .override(height,width)//重写宽高
                        .into(imageView);//加载imageview上
            }else{
                GlideApp.with(imageView.getContext())
                        .load(url) //加载图片url
                        .diskCacheStrategy(DiskCacheStrategy.ALL)// 设置缓存
                        .error(R.drawable.ic_loading_hor)//出错时使用默认图
                        .centerCrop()//设置图片居中
                        .override(width,height)//重写宽高
                        .into(imageView);//加载imageview上
            }
        }
    }

    /**
     * 获取图片最佳比例
     * @param context
     * @param colunms
     * @return
     */
    public static Point getVerposterSize(Context context, int colunms) {
        int width = getScreenWidthPixel(context) / colunms;
        width = width - (int)context.getResources().getDimension(R.dimen.dimen_8dp);
        int height = Math.round((float)width/VER_POSTER_RATIO);
        Point point = new Point();
        point.x = width;
        point.y = height;
        return point;
    }

    private static int getScreenWidthPixel(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        return width;

    }
}
