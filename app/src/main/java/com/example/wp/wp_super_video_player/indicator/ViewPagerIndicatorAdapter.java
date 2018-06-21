package com.example.wp.wp_super_video_player.indicator;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * Created by wangpeng .
 */
public abstract class ViewPagerIndicatorAdapter {
    public abstract int getCount();
    public abstract IPagerTitle getPagerTitle(Context context,int index);
    public abstract IPagerIndicatorView getIndicator(Context context);
    public float getTitlweight(){
        return 1;
    }
    private final DataSetObservable mDataSetObservable = new DataSetObservable();
    public final void registerDataSetObservable(DataSetObserver observable){
        mDataSetObservable.registerObserver(observable);
    }
    public final void unRegisterDataSetObservable(DataSetObserver observable){
        mDataSetObservable.unregisterObserver(observable);
    }
    public final void notifySetDataChange(){
        mDataSetObservable.notifyChanged();
    }
}
