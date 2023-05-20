package com.kt.simpleWallPaper.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kt.simpleWallPaper.Config;

public abstract class PicTypeBaseActivity extends AppCompatActivity {
    public GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), initLayoutManagerLine());
        initView();
        setTitle();
        // 360
        if (Config.PAGETYPESIGN == 0){
            init360Data();
        }
        //U
        if (Config.PAGETYPESIGN == 1){
            initUnsplashData();
        }
        // W
        if (Config.PAGETYPESIGN == 2){
            initWallhavenData();
        }

    }
    public abstract int initLayoutId();
    protected abstract void initView();
    public abstract int initLayoutManagerLine();

    protected abstract void init360Data();
    protected abstract void initUnsplashData();
    protected abstract void initWallhavenData();
    public void setTitle(){

    }

    public View.OnClickListener ToolOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // 360
            if (Config.PAGEINFOSIGN == 0){
                init360RefreshData();
            }
            // Unsplash
            if (Config.PAGEINFOSIGN == 1){
                initUnsplashRefreshData();
            }
            // Wallhaven
            if (Config.PAGEINFOSIGN == 2){
                initWallhavenRefreshData();
            }


        }
    };

    protected abstract void initUnsplashRefreshData();

    protected abstract void initWallhavenRefreshData();

    protected abstract void init360RefreshData();

}
