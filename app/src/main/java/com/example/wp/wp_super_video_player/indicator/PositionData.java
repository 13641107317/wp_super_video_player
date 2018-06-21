package com.example.wp.wp_super_video_player.indicator;

/**
 * Created by wangpeng .
 */
public class PositionData {
    public int mLeft;
    public int mRight;
    public int mTop;
    public int mBottom;
    //内容区域
    public int mContentLeft;
    public int mContentRight;
    public int mContentTop;
    public int mContentBottom;

    /**
     * 控件自身宽度
     *
     * @return
     */
    public int width() {
        return mRight - mLeft;
    }

    /**
     * 控件自身高度
     *
     * @return
     */
    public int height() {
        return mBottom - mTop;
    }

    /**
     * 内容区域宽度
     *
     * @return
     */
    public int ContentWidth() {
        return mContentRight - mContentLeft;
    }

    /**
     * 内容区域高度
     *
     * @return
     */
    public int contentHeight() {
        return mContentBottom - mContentTop;
    }

    /**
     * 水平方向上终点位置
     *
     * @return
     */
    public int horizonalCenter() {
        return mLeft + width() / 2;
    }

    /**
     * 垂直方向上终点位置
     *
     * @return
     */
    public int verticalCenter() {
        return mTop + height() / 2;
    }
}
