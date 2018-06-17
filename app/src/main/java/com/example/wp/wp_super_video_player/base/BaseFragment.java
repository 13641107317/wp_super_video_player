package com.example.wp.wp_super_video_player.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wangpeng .
 */
public abstract class BaseFragment extends Fragment {
    private View rootView;
    private Unbinder mUnbinder = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (rootView == null){
           rootView = inflater.inflate((int) setLayout(),container,false);
           mUnbinder = ButterKnife.bind(this, rootView);
           onBindView(savedInstanceState, rootView);
           return rootView;
       }
       return null;

    }

    protected abstract Object setLayout();


    protected abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
