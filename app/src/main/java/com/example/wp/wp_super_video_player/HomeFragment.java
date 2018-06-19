package com.example.wp.wp_super_video_player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_super_video_player.adapter.ChannelAdapter;
import com.example.wp.wp_super_video_player.api.Router;
import com.example.wp.wp_super_video_player.base.BaseFragment;
import com.example.wp.wp_super_video_player.entity.Channel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wangpeng .
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.gridview)
    GridView mGridView;
    private List<String> titles;
    private List<String> imageList;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
        initGridView();
    }

    private void initGridView() {
        mGridView.setAdapter(new ChannelAdapter(App.getmContext()));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 6:
                        //跳转直播
                        break;
                    case 7:
                        //跳转收藏
                        break;
                    case 8:
                        //跳转历史记录
                        break;
                    default:
                        //跳转对应频道
                        Channel channel = (Channel) adapterView.getAdapter().getItem(position);
                        ARouter.getInstance().build(Router.DETAIL_ACTIVITY).withParcelable("channel", channel).navigation();
                        break;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    private void initBanner() {
        imageList = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add(getString(R.string.a_name));
        titles.add(getString(R.string.b_name));
        titles.add(getString(R.string.c_name));
        titles.add(getString(R.string.d_name));
        imageList.add("http://pic1.qiyipic.com/lequ/20180614/7f47c05bb0904002b1394b3653456e95.jpg");
        imageList.add("http://pic1.qiyipic.com/lequ/20180614/f3019871bd89497b9e579778353b8d05.jpg");
        imageList.add("http://pic1.qiyipic.com/lequ/20180613/7cba262bdc18471fb34e464c739a0732.jpg");
        imageList.add("http://pic2.qiyipic.com/lequ/20180613/37ed22a3bcc04db9aed69da848e09690.jpg");
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(imageList);
        mBanner.setBannerTitles(titles);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }
}
