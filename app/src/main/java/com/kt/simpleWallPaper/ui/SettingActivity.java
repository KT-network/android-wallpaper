package com.kt.simpleWallPaper.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.kt.simpleWallPaper.BaseActivity;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.GlideCacheUtil;
import com.kt.simpleWallPaper.Dialog.InputDialog;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.Dialog.UpDateDialog;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;
import com.kt.simpleWallPaper.api.NCallBack;

public class SettingActivity extends BaseActivity {


    private String TAG = "SettingActivity";

    @Override
    protected void initUpDate() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_setting;
    }


    private Toolbar toolbar;
    private Switch switch_inspect_update, switch_main_bar;
    private CardView linear_developer_tool;
    private TextView text_theme_state, text_inspect_update_state, text_cache_size;

    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(toolBarOnClickListener);
        switch_inspect_update = findViewById(R.id.switch_inspect_update);
        switch_main_bar = findViewById(R.id.switch_main_bar);
        findViewById(R.id.linear_inspect_update).setOnClickListener(linearOnClickListener);
        findViewById(R.id.linear_help).setOnClickListener(linearOnClickListener);
        findViewById(R.id.linear_delete).setOnClickListener(linearOnClickListener);
        text_inspect_update_state = findViewById(R.id.text_inspect_update_state);
        text_cache_size = findViewById(R.id.text_cache_size);
        text_theme_state = findViewById(R.id.text_theme_state);

        switch_inspect_update.setOnClickListener(switchOnClickListener);
        switch_main_bar.setOnClickListener(switchOnClickListener);


        findViewById(R.id.linear_secret_entrance).setOnClickListener(linearOnClickListener);
        linear_developer_tool = findViewById(R.id.linear_developer_tool);
        findViewById(R.id.linear_developer).setOnClickListener(linearOnClickListener);
        findViewById(R.id.linear_administrator).setOnClickListener(linearOnClickListener);

    }


    @Override
    public void initData() {
        switch_inspect_update.setChecked(Config.mmkv.decodeBool("switch_inspect_update"));
        switch_main_bar.setChecked(Config.mmkv.decodeBool("switch_main_bar"));

        if (Config.UPDATE == null){
            return;
        }

        if (Double.valueOf(Config.UPDATE.getVersionsNum()) > Config.versionsNUM) {
            text_inspect_update_state.setText("发现新版本");
            text_inspect_update_state.setTextColor(getResources().getColor(R.color.red));
        }
        text_cache_size.setText(GlideCacheUtil.getInstance().getCacheSize(SettingActivity.this));

        linear_developer_tool.setVisibility(Config.mmkv.decodeBool("linear_developer_tool") ? View.VISIBLE : View.GONE);

    }


    private View.OnClickListener toolBarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private View.OnClickListener switchOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.switch_inspect_update:
                    Config.mmkv.encode("switch_inspect_update", switch_inspect_update.isChecked());
                    break;
                case R.id.switch_main_bar:
                    Config.mmkv.encode("switch_main_bar", switch_main_bar.isChecked());
                    break;
            }


        }
    };

    private View.OnClickListener linearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.linear_inspect_update:
                    getUpdate();
                    break;
                case R.id.linear_help:
                    startActivity(new Intent(SettingActivity.this, WebActivity.class));
                    break;
                case R.id.linear_delete:
                    GlideCacheUtil.getInstance().clearImageAllCache(SettingActivity.this);
                    Tool.Toast(getApplicationContext(), "清除成功");
                    text_cache_size.setText("0.0Byte");
                    break;
                case R.id.linear_secret_entrance:
                    continuousClick();
                    break;
                case R.id.linear_developer:
                    inputDialog = new InputDialog(SettingActivity.this);
                    inputDialog.show();
                    inputDialog.setTitle("开发者身份验证");
                    inputDialog.setConfirmOnClickListener(inputDialogOnClickListener);
                    break;
                case R.id.linear_administrator:
                    inputDialog = new InputDialog(SettingActivity.this);
                    inputDialog.show();
                    inputDialog.setTitle("管理员身份验证");
                    inputDialog.setConfirmOnClickListener(inputDialogOnClickListener);
                    break;
            }

        }
    };


    private void getUpdate() {

        Config.myNetWorkBusiness.getUpData(new NCallBack<BaseResponseEntity<UpDateBase>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<UpDateBase> response) {
                Config.UPDATE = response.getData();

                if (Double.valueOf(Config.UPDATE.getVersionsNum()) > Config.versionsNUM) {
                    UpDateDialog upDateDialog = new UpDateDialog(SettingActivity.this, Config.UPDATE);
                    upDateDialog.show();
                    upDateDialog.setConfirmOnClickListener(upDateConfirmOnClickListener);
                } else {
                    Tool.Toast(getApplicationContext(), "已是最新版本");
                }
            }

            @Override
            public void onNoNetwork() {

            }

        });
    }

    static int COUNTS = 15;//点击次数
    final static long DURATION = 3 * 1000;//规定有效时间
    long[] mHits = new long[COUNTS];

    private void continuousClick() {

        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于DURATION，即连续5次点击
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
            Tool.Toast(getApplicationContext(), "你已进入开发者模式");
            linear_developer_tool.setVisibility(View.VISIBLE);
            Config.mmkv.encode("linear_developer_tool", true);

        }

    }

    private InputDialog inputDialog;

    private View.OnClickListener inputDialogOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    protected View.OnClickListener upDateConfirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse(Config.UPDATE.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    };



}