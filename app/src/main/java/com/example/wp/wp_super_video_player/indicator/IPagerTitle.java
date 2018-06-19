package com.example.wp.wp_super_video_player.indicator;

/**
 * Created by wangpeng .
 */
public interface IPagerTitle {
    /**
     *
     * @param index 第几个
     * @param totalCount 总共多少个
     */
    void onSelected(int index,int totalCount);
    void onDisSelected(int index,int totalCount);

    /**
     *
     * @param index
     * @param totalCount
     * @param leavePercent 取值0.0f - 1.0f(完全离开)
     * @param isLeftToRight 是否从左到右
     */
    void onLeave(int index,int totalCount,float leavePercent,boolean isLeftToRight);

    /**
     *
     * @param index
     * @param totalCount
     * @param enterPercent 取值0.0f - 1.0f(完全进入)
     * @param isLeftToRight
     */
    void onEnter(int index,int totalCount,float enterPercent,boolean isLeftToRight);
}
