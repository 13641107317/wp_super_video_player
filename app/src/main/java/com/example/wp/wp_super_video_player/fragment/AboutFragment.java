package com.example.wp.wp_super_video_player.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.api.Router;
import com.example.wp.wp_super_video_player.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by wangpeng .
 */
public class AboutFragment extends BaseFragment {
    @BindView(R.id.app_desc)
    TextView mTextView;
    @Override
    protected Object setLayout() {
        return R.layout.fragment_about;

    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        //表示文字中有连接可点
        mTextView.setAutoLinkMask(Linkify.ALL);
        //表示文字可以滚动
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
