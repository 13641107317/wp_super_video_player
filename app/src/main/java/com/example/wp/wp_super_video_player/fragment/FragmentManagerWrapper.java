package com.example.wp.wp_super_video_player.fragment;

import android.support.v4.app.Fragment;

import com.example.wp.wp_super_video_player.base.BaseFragment;

import java.util.HashMap;

/**
 * Created by wangpeng .
 */
public class FragmentManagerWrapper {
    private FragmentManagerWrapper() {
    }

    public static FragmentManagerWrapper getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static FragmentManagerWrapper instance = new FragmentManagerWrapper();
    }

    private HashMap<String, Fragment> mHashMap = new HashMap<>();

    public Fragment createFragment(Class<?> clazz) {
        return createFragment(clazz, true);
    }

    public Fragment createFragment(Class<?> clazz, boolean isObtain) {
        Fragment resultFragment = null;
        String className = clazz.getName();
        if (mHashMap.containsKey(className)) {
            resultFragment = mHashMap.get(className);
        } else {
            try {
                resultFragment = (Fragment) Class.forName(className).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (isObtain) {
            mHashMap.put(className, resultFragment);
        }
        return resultFragment;
    }
}
