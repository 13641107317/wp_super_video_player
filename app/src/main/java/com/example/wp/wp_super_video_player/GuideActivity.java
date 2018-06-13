package com.example.wp.wp_super_video_player;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_super_video_player.api.Router;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = Router.GUIDE_ACTIVITY)
public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String TAG = "ceshi";
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.ll_dots_layout)
    LinearLayout mDotLayout;

    private List<View> mViewList;
    private int lastSelect;
    private ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initView();
        initViewPager();
        initDots();
    }

    private void initDots() {
        dots = new ImageView[mViewList.size()];
        for (int i = 0; i < mViewList.size(); i++) {
            dots[i] = (ImageView) mDotLayout.getChildAt(i);
            dots[i].setEnabled(false);
        }
        lastSelect = 0;
        dots[0].setEnabled(true);
    }

    private void initViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mViewList = new ArrayList<>();
        mViewList.add(inflater.inflate(R.layout.guide_one, null));
        mViewList.add(inflater.inflate(R.layout.guide_two, null));
        mViewList.add(inflater.inflate(R.layout.guide_three, null));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentDotsPosition(position);
    }

    private void setCurrentDotsPosition(int position) {
        dots[position].setEnabled(true);
        dots[lastSelect].setEnabled(false);
        lastSelect = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter {
        private List<View> mList;

        public MyPagerAdapter(List<View> viewList) {
            this.mList = viewList;
        }

        @Override
        public int getCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            if (mList != null) {
                if (mList.size() > 0) {
                    container.addView(mList.get(position));
                    if (position == mList.size() - 1) {
                        ImageView iv = mList.get(position).findViewById(R.id.iv_login);
                        iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ARouter.getInstance().build(Router.HOME_ACTIVITY).navigation();
                                setGuide();
                                finish();
                            }
                        });
                    }
                    return mList.get(position);
                }
            }
            return 0;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (mList != null) {
                if (mList.size() >= 0) {
                    container.removeView(mList.get(position));
                }
            }


        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    private void setGuide() {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        sp.edit().putBoolean("isFirstIn", false).commit();

    }
}
