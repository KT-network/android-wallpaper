package com.kt.simpleWallPaper.api.Phone;

import com.kt.simpleWallPaper.api.Phone.base.BaseWallInfo;
import com.kt.simpleWallPaper.api.Phone.base.BaseWallType;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PhoneHttpInterface {

    @GET("lightwp/category")
    Call<BaseWallType> typePhoneData();

    @GET("vertical/category/{id}/vertical?limit=15&order=new")
    Call<BaseWallInfo> infoPhoneData(@Path("id") String id, @Query("skip") int page);

    @GET
    Call<ResponseBody> download(@Url String url);

}
