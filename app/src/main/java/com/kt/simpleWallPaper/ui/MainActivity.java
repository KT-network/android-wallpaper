package com.kt.simpleWallPaper.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.kt.simpleWallPaper.Adapter.MyFragment.ThreeFragment;
import com.kt.simpleWallPaper.Adapter.MyFragment.TwoFragment;
import com.kt.simpleWallPaper.Adapter.MyPagerAdapter;
import com.kt.simpleWallPaper.Adapter.MyFragment.OneFragment;
import com.kt.simpleWallPaper.BaseActivity;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.DarkModeUtils;
import com.kt.simpleWallPaper.R;

import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;
import com.kt.simpleWallPaper.api.NCallBack;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "TAG";
    private ImageView[] icon;
    private ViewPager viewPage;
    private Toolbar toolbar;
    private CardView card_bottom_bar;


    /*
     * 初始化更新弹窗
     * */
    @Override
    protected void initUpDate() {

        Config.Udialog = new AlertDialog.Builder(MainActivity.this).create();
        Config.Uview = View.inflate(MainActivity.this, R.layout.activity_update_dialog, null);
        Config.Utitle = Config.Uview.findViewById(R.id.title);
        Config.Utext = Config.Uview.findViewById(R.id.text);
        Config.Ulinear = Config.Uview.findViewById(R.id.linear);
        Config.Ucancel = Config.Uview.findViewById(R.id.cancel);
        Config.Uconfirm = Config.Uview.findViewById(R.id.confirm);
        Config.UconfirmText = Config.Uview.findViewById(R.id.confirmText);
        Config.UcancelText = Config.Uview.findViewById(R.id.cancelText);

        Config.Ulinear.setBackgroundColor(getResources().getColor(R.color.LightTopBackgroung));
        Config.Utitle.setTextColor(getResources().getColor(R.color.LightTextMajorColour));
        Config.Utext.setTextColor(getResources().getColor(R.color.LightTextMajorColour));

        Config.UconfirmText.setTextColor(getResources().getColor(R.color.LightTextMajorColour));
        Config.UcancelText.setTextColor(getResources().getColor(R.color.LightTextMajorColour));

        Config.Ucancel.setOnClickListener(Config.UOnCancelClickListener);
        Config.Uconfirm.setOnClickListener(Config.UOnConfirmClickListener);

    }


    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    /*
     * 初始化控件
     * */
    @Override
    public void initView() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        card_bottom_bar = findViewById(R.id.card_bottom_bar);
        card_bottom_bar.setVisibility(Config.mmkv.decodeBool("switch_main_bar") ? View.VISIBLE : View.GONE);

        findViewById(R.id.phone).setOnClickListener(bottomAppBarClickListener);
        findViewById(R.id.win).setOnClickListener(bottomAppBarClickListener);
        findViewById(R.id.test).setOnClickListener(bottomAppBarClickListener);
        icon = new ImageView[]{
                findViewById(R.id.phoneIcon),
                findViewById(R.id.winIcon),
                findViewById(R.id.testIcon)};
        viewPage = findViewById(R.id.viewPage);

        setBottomAppBarIconColor(0, 1, 2);



        // 权限
        Tool.requestStoragePermission(MainActivity.this);

        // 检查文件夹
        Tool.isMakedir();

    }


    /*
     * 初始化数据
     * */
    @Override
    public void initData() {

        Log.i(TAG, "initData: "+Tool.getAndroidId(this));

        /*
         * 启动统计
         * */
        /*Config.myNetWorkBusiness.setClick("start", new NCallBack<BaseResponseEntity>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity response) {
            }

            @Override
            public void onNoNetwork() {

            }

        });*/

        /*
         * 获取更新
         * */
        /*Config.myNetWorkBusiness.getUpData(new NCallBack<BaseResponseEntity<UpDateBase>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<UpDateBase> response) {
                Config.UPDATE = response.getData();

                if (Config.mmkv.decodeBool("switch_inspect_update")){
                    UpDateDialog upDateDialog = new UpDateDialog(MainActivity.this,Config.UPDATE);
                    upDateDialog.show();
//                    Tool.setUpDate();
                }else {
                    if ( Config.UPDATE.getMust().equals("Y")){

                        UpDateDialog upDateDialog = new UpDateDialog(MainActivity.this,Config.UPDATE);
                        upDateDialog.show();

//                        Tool.setUpDate();
                    }
                }

            }

            @Override
            public void onNoNetwork() {

            }

        });*/

        MyInitData();

    }


    /*
     * 监听viewpager界面变化更改底部导航栏的颜色
     * */
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

            if (position == 0) setBottomAppBarIconColor(0, 1, 2);

            if (position == 1) setBottomAppBarIconColor(1, 0, 2);

            if (position == 2) setBottomAppBarIconColor(2, 1, 0);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    };

    /*
     * 改变底部导航栏的颜色
     * */
    private void setBottomAppBarIconColor(int dark, int tint1, int tint2) {

        icon[dark].setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.black)));
        icon[tint1].setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.IconGray)));
        icon[tint2].setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.IconGray)));

    }


    // 监听底部导航栏 点击事件
    private View.OnClickListener bottomAppBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.phone) viewPage.setCurrentItem(0);
            if (id == R.id.win) viewPage.setCurrentItem(1);
            if (id == R.id.test) viewPage.setCurrentItem(2);
        }
    };


    private List<Fragment> mFragments;
    private MyPagerAdapter myPagerAdapter;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;

    /*
     * viewpager适配器
     * */
    private void MyInitData() {
        mFragments = new ArrayList<>();

        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();

        mFragments.add(oneFragment);
        mFragments.add(twoFragment);
        mFragments.add(threeFragment);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragments);
        viewPage.setAdapter(myPagerAdapter);
        viewPage.setOffscreenPageLimit(mFragments.size());
        viewPage.addOnPageChangeListener(onPageChangeListener);

    }


    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            /*BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(MainActivity.this);
            View view1 = getLayoutInflater().inflate(R.layout.activity_bottom_layout, null);
            mBottomSheetDialog.setContentView(view1);
            mBottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
            mBottomSheetDialog.show();
*/

            startActivity(new Intent(MainActivity.this,SettingActivity.class));

            return true;
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        card_bottom_bar.setVisibility(Config.mmkv.decodeBool("switch_main_bar") ? View.VISIBLE : View.GONE);

    }
}