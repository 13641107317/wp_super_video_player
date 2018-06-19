package com.example.wp.wp_super_video_player.indicator;

/**
 * Created by wangpeng .
 */
public interface IPagerChangeListener {
    /**
     * 页面选中
     * @param position
     */
    void onPagerSelected(int position);

    /**
     *当滚动时回调
     * @param position
     * @param positionOffsetPercent 0.0f - 1.0f 滚动的百分比
     * @param positionOffsetPixel 距离
     */
    void onPagerScrolled(int position,float positionOffsetPercent,int positionOffsetPixel);

    /**
     * 页面滑动状态发生变化时的回调
     * 如静止到滑动,滑动到静止
     * @param position
     */
    void onPagerScrollStateChanged(int position);

}
