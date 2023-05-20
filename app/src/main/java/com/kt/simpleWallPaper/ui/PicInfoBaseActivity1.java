package com.kt.simpleWallPaper.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kt.simpleWallPaper.Config;

public abstract class PicInfoBaseActivity1 extends AppCompatActivity {
    public GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), initLayoutManagerLine());

        initView();
        setTitle();

        // phone
        if (Config.PAGEINFOSIGN == 0){
            initPhoneData();
        }

        // 360
        if (Config.PAGEINFOSIGN == 1){
            init360Data();
        }

        // one
        if (Config.PAGEINFOSIGN == 2){
            initOneData();
        }

        // Bing
        if (Config.PAGEINFOSIGN == 3){
            initBingData();
        }

        // Wallhaven
        if (Config.PAGEINFOSIGN == 4){
            initWallhavenData();
        }

        // Unsplash
        if (Config.PAGEINFOSIGN == 5){
            initUnsplashData();
        }

    }

    public void setTitle(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Config.SllPage = 0;
        Config.PhonePage = 0;
        Config.BingPage = 0;
        Config.UnsplashPage = 1;
        Config.WallhavenPage = 1;
    }

    public abstract int initLayoutId();
    protected abstract void initView();
    public abstract int initLayoutManagerLine();

    protected abstract void init360Data();

    protected abstract void initPhoneData();

    protected abstract void initUnsplashData();

    protected abstract void initWallhavenData();

    protected abstract void initOneData();

    protected abstract void initBingData();

    protected abstract void initSimpleData();

    public View.OnClickListener ToolOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // phone
            if (Config.PAGEINFOSIGN == 0){
                initPhoneRefreshData();
            }

            // 360
            if (Config.PAGEINFOSIGN == 1){
                init360RefreshData();
            }

            // one
            if (Config.PAGEINFOSIGN == 2){
                initOneRefreshData();
            }

            // Bing
            if (Config.PAGEINFOSIGN == 3){
                initBingRefreshData();
            }

            // Wallhaven
            if (Config.PAGEINFOSIGN == 4){
                initWallhavenRefreshData();
            }

            // Unsplash
            if (Config.PAGEINFOSIGN == 5){
                initUnsplashRefreshData();
            }
        }
    };

    protected abstract void init360RefreshData();

    protected abstract void initOneRefreshData();

    protected abstract void initBingRefreshData();

    protected abstract void initWallhavenRefreshData();

    protected abstract void initUnsplashRefreshData();

    protected abstract void initPhoneRefreshData();

    public boolean loading;
    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0){
                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (!loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount){
                    
                    switch (Config.PAGEINFOSIGN){
                        case 0:
                            initGlidePhoneData();
                            break;
                        case 1:
                            initGlide360Data();
                            break;
                        case 2:
                            initGlideOneData();
                            break;
                        case 3:
                            initGlideBingData();
                            break;
                        case 4:
                            initGlideWallhavenData();
                            break;
                        case 5:
                            initGlideUnsplashData();
                            break;
                    }



                }

            }

        }
    };



    protected abstract void initGlide360Data();

    protected abstract void initGlidePhoneData();

    protected abstract void initGlideUnsplashData();

    protected abstract void initGlideWallhavenData();

    protected abstract void initGlideOneData();

    protected abstract void initGlideBingData();


}
