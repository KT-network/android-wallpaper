package com.kt.simpleWallPaper;

import android.app.Application;
import android.os.Build;

import com.kt.simpleWallPaper.api.download.DownloadNetWorkBusiness;
import com.kt.simpleWallPaper.api.My.MyNetWorkBusiness;
import com.kt.simpleWallPaper.api.One.OneNetWorkBusiness;
import com.kt.simpleWallPaper.api.Phone.PhoneNetWorkBusiness;
import com.kt.simpleWallPaper.api.Sll.SllNetWorkBusiness;
import com.kt.simpleWallPaper.api.Unsplash.UnsplashNetWorkBusiness;
import com.kt.simpleWallPaper.api.Wallhaven.WallhavenNetWorkBusiness;
import com.kt.simpleWallPaper.api.bing.BingNetWorkBusiness;
import com.tencent.mmkv.MMKV;

public final class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

        }*/
        Config.myNetWorkBusiness = new MyNetWorkBusiness();
        Config.sllNetWorkBusiness = new SllNetWorkBusiness();
        Config.oneNetWorkBusiness = new OneNetWorkBusiness();
        Config.bingNetWorkBusiness = new BingNetWorkBusiness();
        Config.unsplashNetWorkBusiness = new UnsplashNetWorkBusiness();
        Config.wallhavenNetWorkBusiness = new WallhavenNetWorkBusiness();
        Config.phoneNetWorkBusiness = new PhoneNetWorkBusiness();
        Config.downloadNetWorkBusiness = new DownloadNetWorkBusiness();

        DarkModeUtils.init(this);

        String rootDir = MMKV.initialize(this);
        Config.mmkv = MMKV.defaultMMKV();


        if (!Config.mmkv.decodeBool("config")){

            Config.mmkv.encode("switch_inspect_update",true);
            Config.mmkv.encode("switch_main_bar",true);
            Config.mmkv.encode("config",true);

        }



    }




}
