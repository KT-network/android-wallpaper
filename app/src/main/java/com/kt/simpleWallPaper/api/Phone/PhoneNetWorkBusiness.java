package com.kt.simpleWallPaper.api.Phone;


import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.Phone.base.BaseWallInfo;
import com.kt.simpleWallPaper.api.Phone.base.BaseWallType;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneNetWorkBusiness {

    private PhoneHttpInterface phoneHttpInterface;

    public PhoneNetWorkBusiness() {
        this.phoneHttpInterface = new Retrofit.Builder().baseUrl("https://service.picasso.adesk.com/v1/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(PhoneHttpInterface.class);
    }

    public void getPhoneTypeData(NCallBack<BaseWallType> callBack){
        phoneHttpInterface.typePhoneData().enqueue(callBack);
    }

    public void getInfoPhoneData(String id,int pager,NCallBack<BaseWallInfo> callBack){
        phoneHttpInterface.infoPhoneData(id,pager).enqueue(callBack);
    }

    public void getDownload(String url, NCallBack<ResponseBody> callBack){
        phoneHttpInterface.download(url).enqueue(callBack);
    }


}
