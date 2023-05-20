package com.kt.simpleWallPaper.ui;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.kt.simpleWallPaper.Adapter.LookPageAdapter.PhoneLookAdapter;
import com.kt.simpleWallPaper.Adapter.LookPageAdapter.WinLookAdapter;
import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.One.base.OneBase;
import com.kt.simpleWallPaper.api.Phone.base.BaseWallInfo;
import com.kt.simpleWallPaper.api.Sll.base.SllBase;
import com.kt.simpleWallPaper.api.Unsplash.base.UnsplashBase;
import com.kt.simpleWallPaper.api.Wallhaven.base.WallhavenBase;
import com.kt.simpleWallPaper.api.bing.base.BingBase;

import java.util.List;

public class LookPicActivity extends LookPicBaseActivity {
    @Override
    protected int initLayoutId() {
        return R.layout.activity_pic_look;
    }

    private Toolbar lookTool;
    private TextView lookTag;
    private LinearLayout ztlLinear, SetWallPaper, topTool, bottomTool;
    private ViewPager2 lookImagePage;

    @Override
    protected void initView() {
        lookTool = findViewById(R.id.lookTool);
        lookImagePage = findViewById(R.id.lookImagePage);
        SetWallPaper = findViewById(R.id.SetWallPaper);
        lookTag = findViewById(R.id.lookTag);
        topTool = findViewById(R.id.topTool);
        bottomTool = findViewById(R.id.bottomTool);
        ztlLinear = findViewById(R.id.ztlLinear);
        if (Config.PAGEINFOSIGN == 0 || Config.PAGEINFOSIGN == 6) {
            lookImagePage.registerOnPageChangeCallback(onPhonePageChangeCallback);
        } else if (Config.PAGEINFOSIGN == 1 || Config.PAGEINFOSIGN == 2 || Config.PAGEINFOSIGN == 3
                || Config.PAGEINFOSIGN == 4 || Config.PAGEINFOSIGN == 5) {
            lookImagePage.registerOnPageChangeCallback(onWinPageChangeCallback);
        }

        lookTool.setOnClickListener(ToolOnClickListener);
        SetWallPaper.setOnClickListener(SetWallPaperonClickListener);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ztlLinear.getLayoutParams();
        layoutParams.height = Tool.getStatusBarHeight(this) / 2;
        ztlLinear.setLayoutParams(layoutParams);

    }

    @Override
    protected void initPhoneData() {
        phoneLookAdapter = new PhoneLookAdapter(getApplicationContext());
        lookImagePage.setAdapter(phoneLookAdapter);
        lookImagePage.setCurrentItem(Config.numPicInfo);
        phoneLookAdapter.setOnPagerItemClickListener(onPhonePagerItemClickListener);
        phoneLookAdapter.setOnPagerItemLongClickListener(onPhonePagerItemLongClickListener);

        if (Config.PAGEINFOSIGN == 0) setPhoneTag();


    }

    @Override
    protected void initWinData() {
        winLookAdapter = new WinLookAdapter(getApplicationContext());
        lookImagePage.setAdapter(winLookAdapter);
        lookImagePage.setCurrentItem(Config.numPicInfo);
        winLookAdapter.setOnPagerItemClickListener(onWinPagerItemClickListener);
        winLookAdapter.setOnPagerItemLongClickListener(onWinPagerItemLongClickListener);

        if (Config.PAGEINFOSIGN == 1) setSllTag();
        if (Config.PAGEINFOSIGN == 2) setOneTag();

    }


    @Override
    protected void initGlidePhoneData() {
        Config.phoneNetWorkBusiness.getInfoPhoneData(Config.PhoneTypeItem.getId(), Config.PhonePage += 15, new NCallBack<BaseWallInfo>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseWallInfo response) {

                if (response.getres().getVertical().size() == 0)
                    Tool.Toast(getApplicationContext(), "暂无更多");
                else {
                    phoneLookAdapter.addPhone(phoneLookAdapter.getItemCount(), response.getres().getVertical());
                }
            }

            @Override
            public void onNoNetwork() {

            }
        });
    }

    @Override
    protected void initGlide360Data() {

        Config.sllNetWorkBusiness.getSslInfoData(Integer.parseInt(Config.SllTypeItem.getTag()), Config.SllPage, Config.SllPage += 15, "", new NCallBack<SllBase>(getApplicationContext()) {
            @Override
            protected void onResponse(SllBase response) {
                if (response.getData().size() == 0) Tool.Toast(getApplicationContext(), "暂无更多");
                else winLookAdapter.addSll(winLookAdapter.getItemCount(), response.getData());
            }

            @Override
            public void onNoNetwork() {

            }
        });

    }

    @Override
    protected void initGlideOneData() {

        Config.oneNetWorkBusiness.getOneData(Tool.getDate(Config.date -= 86400000), new NCallBack<OneBase>(getApplicationContext()) {
            @Override
            protected void onResponse(OneBase response) {
                if (response.getData().getContent_list().size() == 0)
                    Tool.Toast(getApplicationContext(), "暂无更多");
                else
                    winLookAdapter.addOne(winLookAdapter.getItemCount(), response.getData().getContent_list());
            }

            @Override
            public void onNoNetwork() {

            }
        });
    }

    @Override
    protected void initGlideBingData() {
        Config.bingNetWorkBusiness.getBingData(Config.BingPage += 1, new NCallBack<BingBase>(getApplicationContext()) {
            @Override
            protected void onResponse(BingBase response) {
                if (response.getData().size() == 0) Tool.Toast(getApplicationContext(), "暂无更多");
                else winLookAdapter.addBing(winLookAdapter.getItemCount(), response.getData());

            }

            @Override
            public void onNoNetwork() {

            }
        });
    }

    @Override
    protected void initGlideUnsplashData() {
        Config.unsplashNetWorkBusiness.getUnsplashData(Config.UnsplashPage += 1, Config.UnsplashTypeItem.getTag(), new NCallBack<UnsplashBase>(getApplicationContext()) {
            @Override
            protected void onResponse(UnsplashBase response) {
                if (response.getData().size() == 0) Tool.Toast(getApplicationContext(), "暂无更多");
                else winLookAdapter.addUnsplash(winLookAdapter.getItemCount(), response.getData());
            }

            @Override
            public void onNoNetwork() {

            }
        });
    }

    @Override
    protected void initGlideWallhavenData() {
        Config.wallhavenNetWorkBusiness.getWallhavenData(Config.WallhavenPage += 1, Config.WallhavenTypeItem.getTag(), new NCallBack<WallhavenBase>(getApplicationContext()) {
            @Override
            protected void onResponse(WallhavenBase response) {

                if (response.getData().size() == 0) Tool.Toast(getApplicationContext(), "暂无更多");
                else winLookAdapter.addWallhaven(winLookAdapter.getItemCount(), response.getData());
            }

            @Override
            public void onNoNetwork() {

            }
        });
    }

    @Override
    protected void Animation() {
        setAnimation();
    }

    @Override
    protected void setPhoneTag() {

        List<String> data = Config.PhonePicInfo.get(Config.numPicInfo).getTag();
        if (data.size() != 0) {
            String tag = "";
            for (int i = 0; i < data.size(); i++) {
                tag = " #" + data.get(i) + "#" + tag;
            }
            lookTag.setText(tag);

        }
        else lookTag.setText("#"+Config.PhoneTypeItem.getName()+"#");
//        return "#" + Config.PhoneTypeName + "#";

    }

    @Override
    protected void setSllTag() {
        String[] tags = Config.SllPicInfo.get(Config.numPicInfo).getUtag().split(" ");
        if (tags.length != 0){
            String tag = "";
            for (String s : tags){
                tag = " #" + s + "#" +tag;
            }
            lookTag.setText(tag);
        }else lookTag.setText("#"+Config.SllTypeItem.getName()+"#");


    }

    @Override
    protected void setOneTag() {
        lookTag.setText(Config.OnePicPicInfo.get(Config.numPicInfo).getForward());

    }

    @Override
    protected void setNullTag() {

    }

    private boolean isAnimation = true;

    public void setAnimation() {

        if (isAnimation) {

            topTool.setAnimation(translateAniShow);
            bottomTool.setAnimation(translateAniHide);
            topTool.setVisibility(View.GONE);
            bottomTool.setVisibility(View.GONE);
            isAnimation = false;

        } else {
            topTool.setAnimation(translateAniHide);
            bottomTool.setAnimation(translateAniShow);
            topTool.setVisibility(View.VISIBLE);
            bottomTool.setVisibility(View.VISIBLE);
            isAnimation = true;
        }

    }

}
