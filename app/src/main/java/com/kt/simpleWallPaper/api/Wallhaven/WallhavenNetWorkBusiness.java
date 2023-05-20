package com.kt.simpleWallPaper.api.Wallhaven;

import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.Unsplash.UnsplashNetWorkBusiness;
import com.kt.simpleWallPaper.api.Wallhaven.base.WallhavenBase;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WallhavenNetWorkBusiness {

    private interface WallhavenHttpInterface{
        // https://api.codelife.cc/wallhaven/search?page=1&sorting=order&topRange=6M&q=id:2319
        @GET("search?sorting=order&topRange=6M")
        Call<WallhavenBase> getWallhavenData(@Query("page") int page, @Query("q") String id);

    }

    private WallhavenHttpInterface wallhavenHttpInterface;
    public WallhavenNetWorkBusiness(){

        this.wallhavenHttpInterface = new Retrofit.Builder().baseUrl("https://api.codelife.cc/wallhaven/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(WallhavenHttpInterface.class);

    }

    public void getWallhavenData(int page, String id, NCallBack<WallhavenBase> callBack){
        wallhavenHttpInterface.getWallhavenData(page,id).enqueue(callBack);
    }

}
