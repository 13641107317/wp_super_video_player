package com.example.wp.wp_super_video_player;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wp.wp_super_video_player.api.Router;
import com.example.wp.wp_super_video_player.fragment.FragmentManagerWrapper;

import butterknife.BindView;

@Route(path = Router.HOME_ACTIVITY)
public class HomeActivity extends BaseActivity {
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.fl_home_content)
    FrameLayout mFrameLayout;
    @BindView(R.id.nav)
    NavigationView mNav;
    private ActionBarDrawerToggle toggle;

    private MenuItem mMenuItem;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    private long clickTime = 0;

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        setSupportActionBar();
        setActionBarIcon(R.drawable.ic_drawer_home);
        setTitle("首页");
        //toolbar 和drawerlayout关联
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        //侧滑menu
        mMenuItem = mNav.getMenu().getItem(0);
        mMenuItem.setChecked(true);
        initFragment();
        handNavigationViewItem();
    }

    private void initFragment() {
        mCurrentFragment = FragmentManagerWrapper.getInstance().createFragment(HomeFragment.class, true);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_home_content, mCurrentFragment).commit();
    }

    private void handNavigationViewItem() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                }
                switch (item.getItemId()) {
                    case R.id.navigation_item_video:
                        switchFragment(HomeFragment.class);
                        mToolbar.setTitle(R.string.home_title);
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(BlogFragment.class);
                        mToolbar.setTitle(R.string.blog_title);
                        break;
                    case R.id.navigation_item_about:
                        switchFragment(AboutFragment.class);
                        mToolbar.setTitle(R.string.about_title);
                        break;
                    default:
                        break;
                }
                mMenuItem = item;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                item.setChecked(true);
                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        Fragment fragment = FragmentManagerWrapper.getInstance().createFragment(clazz);
        if (fragment.isAdded()) {
            mFragmentManager.beginTransaction()
                    .hide(mCurrentFragment)
                    .show(fragment).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment)
                    .add(R.id.fl_home_content, fragment)
                    .commitAllowingStateLoss();
        }
        mCurrentFragment = fragment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * back键2秒退出
     */
    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            this.finish();
            //     System.exit(0);
        }
    }
}
