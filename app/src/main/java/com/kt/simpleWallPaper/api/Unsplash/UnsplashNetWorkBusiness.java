package com.kt.simpleWallPaper.api.Unsplash;

import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.Sll.SllHttpInterface;
import com.kt.simpleWallPaper.api.Unsplash.base.UnsplashBase;
import com.kt.simpleWallPaper.api.bing.base.BingBase;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UnsplashNetWorkBusiness {


    private interface UnsplashHttpInterface{
        // https://api.codelife.cc/wallpaper/unsplash?per_page=12&page=1&type=holidays
        @GET("unsplash?per_page=12")
        Call<UnsplashBase> getUnsplashData(@Query("page") int page, @Query("type") String type);

    }
    private UnsplashHttpInterface unsplashHttpInterface;
    public UnsplashNetWorkBusiness(){

        this.unsplashHttpInterface = new Retrofit.Builder().baseUrl("https://api.codelife.cc/wallpaper/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(UnsplashHttpInterface.class);

    }

    public void getUnsplashData(int page, String type, NCallBack<UnsplashBase> callBack){

        this.unsplashHttpInterface.getUnsplashData(page,type).enqueue(callBack);

    }

}
