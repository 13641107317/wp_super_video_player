package com.example.wp.wp_super_video_player.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.wp.wp_super_video_player.fragment.DetailListFragment;

import java.util.HashMap;

/**
 * Created by WangPeng on 2018/6/14.
 */
public class SitePagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private int channelId;
    private HashMap<Integer,DetailListFragment>mPagerMap;
    public SitePagerAdapter(FragmentManager fm, Context context,int channelId) {
        super(fm);
        this.channelId = channelId;
        this.mContext = context;
        mPagerMap = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new DetailListFragment(1,channelId);

        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj =  super.instantiateItem(container, position);
        if (obj instanceof DetailListFragment){
            mPagerMap.put(position, (DetailListFragment) obj);
        }
        return obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        mPagerMap.remove(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
