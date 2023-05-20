package com.kt.simpleWallPaper.api.bing;

import com.kt.simpleWallPaper.api.My.MyHttpInterface;
import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.bing.base.BingBase;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BingNetWorkBusiness {
    public interface BingHttpInterface{
        @GET("list")
        Call<BingBase> getBingData(@Query("page") int page);
    }

    private BingHttpInterface bingHttpInterface;
    public BingNetWorkBusiness(){
        this.bingHttpInterface = new Retrofit.Builder().baseUrl("https://api.codelife.cc/bing/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(BingHttpInterface.class);
    }

    public void getBingData(int page, NCallBack<BingBase> callBack){
        bingHttpInterface.getBingData(page).enqueue(callBack);
    }

}
