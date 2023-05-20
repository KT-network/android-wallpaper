package com.kt.simpleWallPaper;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());

        // 改变状态栏颜色
        Tool.setStatusTextColor(true, this);


//        DarkModeUtils.applyNightMode(this);

        initView();
        initData();
        initUpDate();

    }

    protected abstract void initUpDate();

    public abstract int initLayoutId();
    public abstract void initView();
    public abstract void initData();

}
