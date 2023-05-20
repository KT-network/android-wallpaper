package com.kt.simpleWallPaper;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.kt.simpleWallPaper.api.NCallBack;
import com.ph.permissions.HPermissions;
import com.ph.permissions.OnPermissionCallback;
import com.ph.permissions.Permission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;

public class Tool {

    public static StateListDrawable clickResult(){
        String[] themes = {"0","170","170","170"};
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(20);
        shape.setColor(Color.argb(
                80,
                Integer.valueOf(themes[1]),
                Integer.valueOf(themes[2]),
                Integer.valueOf(themes[3])));

        StateListDrawable selector = new StateListDrawable();
        selector.addState(new int[]{android.R.attr.state_pressed},shape);
        return selector;
    }

    public static void setStatusTextColor(boolean isDark, Activity activity) {
        if (isDark) {
            //黑色字体
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                Window window = activity.getWindow();
                window.setStatusBarColor(activity.getColor(R.color.LightTopBackgroung));
            }
        } else {
            //白色字体
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

            // 背景
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = activity.getWindow();
                window.setStatusBarColor(activity.getColor(R.color.NightTopBackgroung));
            }


        }
    }


    public static String getDate(long time) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);

        return formatter.format(date);

    }


    // 设置更新弹窗
    public static void setUpDate() {

        if (Double.valueOf(Config.UPDATE.getVersionsNum()) > Config.versionsNUM) {

            Config.Utitle.setText(Config.UPDATE.getTitle());
            Config.Utext.setText(Config.UPDATE.getText());
            Config.UconfirmText.setText("更新(" + Config.UPDATE.getSize() + "Mb)");

            if (Config.UPDATE.getMust().equals("Y")) {
                Config.Udialog.setCancelable(false);
                Config.Ucancel.setVisibility(View.GONE);
            } else Config.Udialog.setCancelable(true);

            Config.Udialog.setView(Config.Uview);

            Config.Udialog.show();
        }


    }

    public static void Toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /*public interface onIncidentListener{
        void setDataIncidentListener();
    }
    public onIncidentListener mSetIncidentListener;
    public void setOnIncidentListener(onIncidentListener listener){
        mSetIncidentListener = listener;
    }*/


    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(o);
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }


    public static String getFile(String path, InputStream inputStream,Context context) {

        String name = getPath()+path+".jpg";
        try {
            FileOutputStream fout = new FileOutputStream(name);
            int len;
            byte[] bytes = new byte[4096];
            while ((len = inputStream.read(bytes)) != -1) {
                fout.write(bytes, 0, len);
            }

            fout.close();
            inputStream.close();
            return name;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void requestStoragePermission(Context context) {
        HPermissions.with(context).permission(Permission.Group.STORAGE)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {

                    }
                });
    }


    public static void requestStoragePermissionResolute(Context context) {

        HPermissions.with(context).permission(Permission.Group.STORAGE)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {

                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {

                        if (never){
                            Tool.Toast(context,"你永久拒绝了存储权限，请手动开启");
                            HPermissions.startPermissionActivity(context);
                        }else {
                            requestStoragePermissionResolute(context);
                        }

                    }
                });

    }


    public static String getPath(){

        return getSDPath()+"/"+Config.PATH+"/";
    }

    // 判断目录是否存在
    public static void isMakedir(){
        String path = getSDPath()+ "/" +Config.PATH+"/";
        File file = new File(path);
        if (!file.exists()){
            makedir(Config.PATH);
        }

    }


    // 判断文件是否已存在
    public static boolean isFile(String path){
        File file = new File(path);
        if (file.exists()){
            return true;
        }
        return false;
    }


    // 获取根目录地址
    private static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);// 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }

    // 创建文件夹
    private static String makedir(String path) {

        String sdPath = getSDPath();
        String[] dirs = path.replace(sdPath, "").split("/");
        StringBuffer filePath = new StringBuffer(sdPath);
        for (String dir : dirs) {
            if (!"".equals(dir) && !dir.equals(sdPath)) {
                filePath.append("/").append(dir);
                File destDir = new File(filePath.toString());
                if (!destDir.exists()) {
                    boolean b = destDir.mkdirs();
                    if (!b) {
                        return null;
                    }
                }
            }
        }
        return filePath.toString();
    }

    public static void getCopy(Context context,String text){
        Toast(context,"复制成功");
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        ClipData mClipData =ClipData.newPlainText("Label", text);
        manager.setPrimaryClip(mClipData);

    }

    public static void shapePic(Context context,String path){

        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("image/*");  //设置分享内容的类型
        share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        share_intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "简壁纸");
        context.startActivity(share_intent);

    }

    public static Uri getUri(String path){

        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        return uri;

    }


    public static String getAndroidId(Context context){

        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

    }

}
