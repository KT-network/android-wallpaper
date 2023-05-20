package com.kt.simpleWallPaper.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.kt.simpleWallPaper.Dialog.LongAlertDialog;
import com.kt.simpleWallPaper.Adapter.LookPageAdapter.PhoneLookAdapter;
import com.kt.simpleWallPaper.Adapter.LookPageAdapter.WinLookAdapter;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.api.download.Download;

public abstract class LookPicBaseActivity extends AppCompatActivity {

    PhoneLookAdapter phoneLookAdapter;
    WinLookAdapter winLookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        initView();

        if (Config.PAGEINFOSIGN == 0 || Config.PAGEINFOSIGN == 6) {
            initPhoneData();
        }
        // 360
        if (Config.PAGEINFOSIGN == 1 || Config.PAGEINFOSIGN == 2 || Config.PAGEINFOSIGN == 3
                || Config.PAGEINFOSIGN == 4 || Config.PAGEINFOSIGN == 5) {
            initWinData();
        }

    }


    protected abstract int initLayoutId();

    protected abstract void initView();


    protected abstract void initPhoneData();

    protected abstract void initWinData();


    public View.OnClickListener ToolOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    protected ViewPager2.OnPageChangeCallback onPhonePageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            if (phoneLookAdapter.getItemCount() - 1 == position) {
                switch (Config.PAGEINFOSIGN) {
                    case 0:
                        initGlidePhoneData();
                        break;
                    case 6:
                        break;
                }
            }

        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);

            // 退回到上一界面后定位的位置
            Config.numPicInfo = position;
            if (Config.PAGEINFOSIGN == 0){
                setPhoneTag();
            }else {
                setNullTag();
            }
            // 匹配信息
//            lookTag.setText(getTag(position));

        }
    };


    protected ViewPager2.OnPageChangeCallback onWinPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            if (winLookAdapter.getItemCount() - 1 == position) {


                switch (Config.PAGEINFOSIGN) {
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

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            Config.numPicInfo = position;
            if (Config.PAGEINFOSIGN == 1){
                setSllTag();
            }
            else if (Config.PAGEINFOSIGN == 2){
                setOneTag();
            }else {
                setNullTag();
            }

        }
    };

    protected abstract void initGlidePhoneData();

    protected abstract void initGlide360Data();

    protected abstract void initGlideOneData();

    protected abstract void initGlideBingData();

    protected abstract void initGlideUnsplashData();

    protected abstract void initGlideWallhavenData();

    // phone 监听
    protected PhoneLookAdapter.OnPhonePagerItemClickListener onPhonePagerItemClickListener = new PhoneLookAdapter.OnPhonePagerItemClickListener() {
        @Override
        public void OnPhonePagerItemClick(int position) {
            Animation();
        }
    };

    protected abstract void Animation();

    protected PhoneLookAdapter.OnPhonePagerItemLongClickListener onPhonePagerItemLongClickListener = new PhoneLookAdapter.OnPhonePagerItemLongClickListener() {
        @Override
        public void OnPhonePagerItemLongClick(int position) {
            new LongAlertDialog(LookPicBaseActivity.this).show();
        }
    };

    // win监听
    protected WinLookAdapter.OnWinPagerItemClickListener onWinPagerItemClickListener = new WinLookAdapter.OnWinPagerItemClickListener() {
        @Override
        public void OnWinPagerItemClick(int position) {
            Animation();
        }
    };
    protected WinLookAdapter.OnWinPagerItemLongClickListener onWinPagerItemLongClickListener = new WinLookAdapter.OnWinPagerItemLongClickListener() {
        @Override
        public void OnWinPagerItemLongClick(int position) {
            new LongAlertDialog(LookPicBaseActivity.this).show();
        }
    };


    protected TranslateAnimation translateAniShow, translateAniHide;

    // 创建动画
    private void translateAnimation() {
        // 向上位移动画
        translateAniShow = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1,
                Animation.RELATIVE_TO_SELF, 0);
        translateAniShow.setRepeatMode(Animation.REVERSE);
        translateAniShow.setDuration(100);

        // 向上位移动画
        translateAniHide = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1);
        translateAniHide.setRepeatMode(Animation.REVERSE);
        translateAniHide.setDuration(100);
    }

    protected abstract void setPhoneTag();
    protected abstract void setSllTag();
    protected abstract void setOneTag();
    protected abstract void setNullTag();


    protected View.OnClickListener SetWallPaperonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (Config.PAGEINFOSIGN == 0){
                Download download = new Download(getApplicationContext());
                download.setWall();
            }else{

                Download download = new Download(getApplicationContext());
                download.setWall();

//                Tool.Toast(getApplicationContext(),"该图片暂不支持设置为壁纸");
            }

        }
    };

}
