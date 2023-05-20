package com.kt.simpleWallPaper.api.Sll;

import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.Sll.base.SllBase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SllNetWorkBusiness {

    private SllHttpInterface sslHttpInterface;

    public SllNetWorkBusiness(){
        this.sslHttpInterface = new Retrofit.Builder().baseUrl("http://wallpaper.apc.360.cn/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(SllHttpInterface.class);
    }

    public void getSslInfoData(int cid, int start, int count,String from, NCallBack<SllBase> callBack){
        sslHttpInterface.getSslInfoData(cid,start,count,from).enqueue(callBack);
    }


}
