package com.example.wp.wp_super_video_player.indicator;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.Gravity;

/**
 * Created by wangpeng .
 * 自定义viewPager指示器
 */
public class ViewPagerTitleView extends android.support.v7.widget.AppCompatTextView implements IViewPagerTitleView {

    private int mNormalColor;
    private int mSelectedClor;


    public ViewPagerTitleView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        //居中显示
        setGravity(Gravity.CENTER);
        int padding = dip2px(context, 20);
        setPadding(padding, 0, padding, 0);
        setSingleLine();//单行
        /**
         *  android:ellipsize="end"　　   省略号在结尾
         *  android:ellipsize="start" 　　省略号在开头
         *  android:ellipsize="middle"   省略号在中间
         *  android:ellipsize="marquee"  跑马灯
         */
        setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * 转换成像素
     *
     * @param context
     * @param dip
     * @return
     */
    private int dip2px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);

    }

    @Override
    public int getContentLeft() {
        Rect bound = new Rect();
        //通过rect和画笔映射,取到对应宽高
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 - contentWidth / 2;
    }

    @Override
    public int getContentTop() {
        //字的测量
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 - contentHeight / 2);
    }

    @Override
    public int getContentRight() {
        Rect bound = new Rect();
        //通过rect和画笔映射,取到对应宽高
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 + contentWidth / 2;
    }

    @Override
    public int getContentBottom() {
        //字的测量
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 + contentHeight / 2);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        //选中颜色
        setTextColor(mSelectedClor);
    }

    @Override
    public void onDisSelected(int index, int totalCount) {
        setTextColor(mNormalColor);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean isLeftToRight) {

    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean isLeftToRight) {

    }

    public void setNormalColor(int normalColor) {
        this.mNormalColor = normalColor;
    }

    public void setSelectedClor(int selectedClor) {
        this.mSelectedClor = selectedClor;
    }
}
