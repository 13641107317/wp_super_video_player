package com.example.wp.wp_super_video_player.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.wp.wp_super_video_player.R;
import com.example.wp.wp_super_video_player.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by wangpeng .
 */
public class BlogFragment extends BaseFragment {
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;
    private static final int MAX_VALUE = 100;
    private static final String URL = "https://blog.csdn.net/qq_31079677/article/details/51160252";
    @Override
    protected Object setLayout() {
        return R.layout.fragment_blog;

    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        mProgressBar.setMax(MAX_VALUE);
        mWebView.loadUrl(URL);
        mWebView.setWebChromeClient(chromeClient);

    }
    private WebChromeClient chromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress == MAX_VALUE){
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);

        }
    };
}
