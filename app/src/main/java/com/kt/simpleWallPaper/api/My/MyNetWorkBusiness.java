package com.kt.simpleWallPaper.api.My;

import com.kt.simpleWallPaper.api.My.base.ClickBase;
import com.kt.simpleWallPaper.api.My.base.PictureBase;
import com.kt.simpleWallPaper.api.My.base.TypeBase;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;
import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.My.base.AllDataGatherBase;
import com.kt.simpleWallPaper.api.My.base.BaseResponseEntity;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyNetWorkBusiness {
    private MyHttpInterface httpInterface;

    public MyNetWorkBusiness() {

        this.httpInterface = new Retrofit.Builder().baseUrl("http://192.168.1.7:2023/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(MyHttpInterface.class);

        /*this.httpInterface = new Retrofit.Builder().baseUrl("http://simplewallpaper.kt-network.cn/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(MyHttpInterface.class);*/

    }

    /*
     * 获取最近更新数据
     * */
    public void getUpData(NCallBack<BaseResponseEntity<UpDateBase>> callBack) {
        httpInterface.getUpData().enqueue(callBack);
    }

    /*
     * 获取所有历史更新数据
     * */
    public void getUpDataAll(NCallBack<BaseResponseEntity<List<UpDateBase>>> callBack) {
        httpInterface.getUpDataAll().enqueue(callBack);
    }

    /*
     * 获取单个数据集
     * */
    public void getData(String tag, NCallBack<BaseResponseEntity<List<TypeBase>>> callBack) {
        httpInterface.getData(tag).enqueue(callBack);
    }

    /*
     * 获取单个点击量
     * */
    public void getClick(String tag, NCallBack<BaseResponseEntity<ClickBase>> callBack) {
        httpInterface.getClick(tag).enqueue(callBack);
    }

    /*
     * 获取全部点击量
     * */
    public void getClickAll(NCallBack<BaseResponseEntity<List<ClickBase>>> callBack) {
        httpInterface.getClickAll().enqueue(callBack);
    }

    /*
     * 获取待审核的图片
     * */
    public void getToAudit(NCallBack<BaseResponseEntity<List<PictureBase>>> callBack) {
        httpInterface.getToAudit().enqueue(callBack);
    }

    /*
     * 获取用户上传的图片
     * */
    public void getUpLoadPic(NCallBack<BaseResponseEntity<List<PictureBase>>> callBack) {
        httpInterface.getUpLoadPic().enqueue(callBack);
    }

    /*
     * 创建（修改）更新内容
     * */
    public void setUpDate(String versionsNum,
                          String title,
                          String text,
                          float size,
                          String urlType,
                          String url,
                          String must, NCallBack<BaseResponseEntity> callBack) {
        httpInterface.setUpDate(versionsNum, title, text, size, urlType, url, must).enqueue(callBack);

    }


    /*
     * 创建（修改）点击量
     * */
    public void setClick(String tag, NCallBack<BaseResponseEntity> callBack) {
        httpInterface.setClick(tag).enqueue(callBack);
    }

    /*
     * 上传图片
     * */
    public void setUpLoad() {

    }


    /*
     * 审核图片
     * */
    public void setAudit(String token, String url, String md5Name, NCallBack<BaseResponseEntity> callBack) {
        httpInterface.setAudit(token, url, md5Name).enqueue(callBack);
    }


}
