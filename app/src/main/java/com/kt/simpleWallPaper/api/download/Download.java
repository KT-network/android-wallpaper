package com.kt.simpleWallPaper.api.download;

import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.NCallBack;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;

public class Download {

    private String url ;
    private String name;
    private Context context;
    public Download(Context context){
        this.context = context;
        if (Config.PAGEINFOSIGN == 0) {
            this.url = Config.PhonePicInfo.get(Config.numPicInfo).getPreview();
            this.name = Config.PhonePicInfo.get(Config.numPicInfo).getId();
        }
        if (Config.PAGEINFOSIGN == 1) {
            this.url = Config.SllPicInfo.get(Config.numPicInfo).getImg_1600_900();
            this.name = Config.SllPicInfo.get(Config.numPicInfo).getId();
        }
        if (Config.PAGEINFOSIGN == 2) {
            this.url = Config.OnePicPicInfo.get(Config.numPicInfo).getImg_url();
            this.name = String.valueOf(System.currentTimeMillis());
        }

    }

    public void download(){

        Config.downloadNetWorkBusiness.download(url, new NCallBack<ResponseBody>(context) {
            @Override
            protected void onResponse(ResponseBody response) {

                String path = Tool.getFile(name, response.byteStream(), context);
                Tool.Toast(context,path);

                update(path);
            }

            @Override
            public void onNoNetwork() {

            }
        });

    }

    private String path;
    public String pathDownload(){
        Config.downloadNetWorkBusiness.download(url, new NCallBack<ResponseBody>(context) {
            @Override
            protected void onResponse(ResponseBody response) {
                path = Tool.getFile(name, response.byteStream(), context);
                update(path);
            }

            @Override
            public void onNoNetwork() {
                path = null;
            }
        });

        return path;

    }

    public void setWall(){

        Config.downloadNetWorkBusiness.download(url, new NCallBack<ResponseBody>(context) {
            @Override
            protected void onResponse(ResponseBody response) {
                String path = Tool.getFile(name, response.byteStream(), context);
                update(path);
                if (path != null){

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        try {
                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                            WallpaperManager mWallManager = WallpaperManager.getInstance(context);
                            mWallManager.setBitmap(bitmap, null, false);
                            Tool.Toast(context,"设置成功");
                        } catch (IOException e) {
                            Tool.Toast(context,"设置失败");
                            e.printStackTrace();
                        }

                    }

                }

            }

            @Override
            public void onNoNetwork() {

            }
        });

    }

    // 通知系统更新图库
    private void update(String uri){
        File file = new File(uri);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA,file.getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/*");
        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

    }

}
