package com.kt.simpleWallPaper.api.My;
import com.kt.simpleWallPaper.api.My.base.AllDataGatherBase;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;
import com.kt.simpleWallPaper.api.My.base.ClickBase;
import com.kt.simpleWallPaper.api.My.base.PictureBase;
import com.kt.simpleWallPaper.api.My.base.TypeBase;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MyHttpInterface {

    /*
    * 查询数据集合
    * */
    @GET("get/gather")
    Call<BaseResponseEntity<AllDataGatherBase>> getGather();


    /*
     获取更新(最新)
     */
    @GET("get/update/new")
    Call<BaseResponseEntity<UpDateBase>> getUpData();

    @GET("get/update/all")
    Call<BaseResponseEntity<List<UpDateBase>>> getUpDataAll();


    /*
     * 获取单个数据
     * */
    @GET("get/appdata/{tag}")
    Call<BaseResponseEntity<List<TypeBase>>> getData(@Path("tag") String tag);

    /*
     * 获取点击量
     * */
    @GET("get/click/{tag}")
    Call<BaseResponseEntity<ClickBase>> getClick(@Path("tag") String tag);

    @GET("get/click/all")
    Call<BaseResponseEntity<List<ClickBase>>> getClickAll();

    /*
     * 查询待审核的图片
     * */
    @GET("/get/ToAudit")
    Call<BaseResponseEntity<List<PictureBase>>> getToAudit();


    /*
     * 查询用户上传的图片
     * */
    @GET("get/upLoadPicList")
    Call<BaseResponseEntity<List<PictureBase>>> getUpLoadPic();


    /*
     * 创建更新内容
     * @param versionsNum 版本号
     *
     * */
    @FormUrlEncoded
    @POST("set/update")
    Call<BaseResponseEntity> setUpDate(@Field("versionsNum") String versionsNum,
                                       @Field("title") String title,
                                       @Field("text") String text,
                                       @Field("size") float size,
                                       @Field("urlType") String urlType,
                                       @Field("url") String url,
                                       @Field("must") String must);

    /*
    * 创建点击量（修改）
    * */
    @FormUrlEncoded
    @POST("set/click")
    Call<BaseResponseEntity> setClick(@Field("tag") String tag);


    /*
    *
    * 上传图片
    * */
    @POST("set/upload")
    @Multipart
    Call<BaseResponseEntity> setUpLoad(@Header("user") String user, @Part MultipartBody.Part file);



    /*
    * 审核图片
    * */
    @POST("set/audit")
    @Multipart
    Call<BaseResponseEntity> setAudit(@Header("user") String token, @Field("url") String url, @Field("md5Name") String md5Name);





}
