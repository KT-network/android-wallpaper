package com.kt.simpleWallPaper.ui;

import static android.view.KeyEvent.KEYCODE_BACK;

import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.kt.simpleWallPaper.BaseActivity;
import com.kt.simpleWallPaper.DarkModeUtils;
import com.kt.simpleWallPaper.R;

public class WebActivity extends BaseActivity {

    private final static String TAG = "WebActivity";

    @Override
    protected void initUpDate() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_web;
    }

    private Toolbar toolbar;
    private WebView webView;
    private LinearLayout linear_web_progress_bar;
    private CardView card_web_progress_bar;
    private int width;
    @Override
    public void initView() {

        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web);
        linear_web_progress_bar = findViewById(R.id.linear_web_progress_bar);
        card_web_progress_bar = findViewById(R.id.card_web_progress_bar);


        toolbar.inflateMenu(R.menu.web_menu);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        toolbar.setOnClickListener(toolBarOnClickListener);

        // 获取手机屏幕的宽度
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width = outMetrics.widthPixels;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        // 设置web监听
        webView.setWebChromeClient(new MyWebChromeClient());
    }

    @Override
    public void initData() {

        webView.setWebViewClient(new MyBrowser());
        webView.loadUrl("https://support.qq.com/product/363974");

    }

    private View.OnClickListener toolBarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();

            switch (id){
                case R.id.refresh:
                    webView.reload();
                    toolbar.setTitle("加载中...");
                    linear_web_progress_bar.setVisibility(View.VISIBLE);
                    break;
                case R.id.open_browser:
                    Uri uri = Uri.parse("https://support.qq.com/product/363974");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;
            }

            return true;
        }
    };


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    private class MyWebChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if (newProgress == 100){
                linear_web_progress_bar.setVisibility(View.GONE);
                card_web_progress_bar.setLayoutParams(new LinearLayout.LayoutParams(0,10));
            }else {
                double a = width * (newProgress * 0.01);
                card_web_progress_bar.setLayoutParams(new LinearLayout.LayoutParams((int) a,10));
            }

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            toolbar.setTitle(title+"");
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5界面


    }





    //    setDefaultNightMode

}
