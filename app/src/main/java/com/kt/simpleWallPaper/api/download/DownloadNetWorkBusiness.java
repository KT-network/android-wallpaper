package com.kt.simpleWallPaper.api.download;

import com.kt.simpleWallPaper.api.NCallBack;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class DownloadNetWorkBusiness {

    public interface DownloadHttpInterface {
        @GET
        Call<ResponseBody> download(@Url String url);
    }

    private DownloadHttpInterface downloadHttpInterface;

    public DownloadNetWorkBusiness() {
        this.downloadHttpInterface = new Retrofit.Builder().baseUrl("http://baidu.com/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(DownloadHttpInterface.class);
    }


    public void download(String url, NCallBack<ResponseBody> callBack){
        downloadHttpInterface.download(url).enqueue(callBack);
    }


}