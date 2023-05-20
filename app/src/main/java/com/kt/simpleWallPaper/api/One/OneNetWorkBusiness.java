package com.kt.simpleWallPaper.api.One;

import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.One.base.OneBase;
import com.kt.simpleWallPaper.api.Phone.PhoneHttpInterface;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class OneNetWorkBusiness {

    public interface OneHttpInterface {

        @GET("api/channel/one/{time}/西安")
        Call<OneBase> getOneData(@Path("time") String time);

    }

    private OneHttpInterface oneHttpInterface;
    public OneNetWorkBusiness(){
        this.oneHttpInterface = new Retrofit.Builder().baseUrl("http://v3.wufazhuce.com:8000/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(OneHttpInterface.class);
    }


    public void getOneData(String time, NCallBack<OneBase> callBack){
        oneHttpInterface.getOneData(time).enqueue(callBack);
    }


}
