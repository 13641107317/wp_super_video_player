package com.example.wp.wp_super_video_player.indicator;

/**
 * Created by wangpeng .
 */
public interface IPagerIndicatorLayout extends IPagerChangeListener{
    /**
     * 绑定CoolIndicatorLayout
     */
    void onAttachCoolIndicatorLayout();
    /**
     * 解绑CoolIndircatorLayout
     */
    void onDetachCoolIndicatorLayout();

}
