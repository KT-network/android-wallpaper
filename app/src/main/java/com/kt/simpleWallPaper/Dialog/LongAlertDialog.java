package com.kt.simpleWallPaper.Dialog;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kt.simpleWallPaper.Config;
import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.NCallBack;
import com.kt.simpleWallPaper.api.download.Download;
import com.kt.simpleWallPaper.ui.MainActivity;

import java.io.IOException;

import okhttp3.ResponseBody;

public class LongAlertDialog {

    private Context context;
    private AlertDialog dialog;
    private View view;
    private LinearLayout cancel, SetWallDialog, downloadRaw, shape, linear, copy;
    private TextView cancelText, setText, downloadText, shapeText, copyText;

    public LongAlertDialog(Context context) {

        this.context = context;
        this.dialog = new AlertDialog.Builder(context).create();
        this.view = View.inflate(context, R.layout.activity_pic_dialog_long, null);
        this.cancel = view.findViewById(R.id.cancel);
        this.SetWallDialog = view.findViewById(R.id.SetWallDialog);
        this.downloadRaw = view.findViewById(R.id.downloadRaw);
        this.shape = view.findViewById(R.id.shape);
        this.linear = view.findViewById(R.id.linear);
        this.copy = view.findViewById(R.id.copy);

        this.shapeText = view.findViewById(R.id.shapeText);
        this.setText = view.findViewById(R.id.setText);
        this.downloadText = view.findViewById(R.id.downloadText);
        this.cancelText = view.findViewById(R.id.cancelText);
        this.copyText = view.findViewById(R.id.copyText);

        this.shape.setOnClickListener(onShapeClickListener);
        this.cancel.setOnClickListener(onCancelClickListener);
        this.SetWallDialog.setOnClickListener(onSetClickListener);
        this.downloadRaw.setOnClickListener(onDownloadClickListener);
        this.copy.setOnClickListener(onCopyClickListener);
    }


    private void setColor() {
        this.linear.setBackgroundColor(context.getResources().getColor(R.color.LightTopBackgroung));
        this.shapeText.setTextColor(context.getResources().getColor(R.color.LightTextMajorColour));
        this.setText.setTextColor(context.getResources().getColor(R.color.LightTextMajorColour));
        this.downloadText.setTextColor(context.getResources().getColor(R.color.LightTextMajorColour));
        this.cancelText.setTextColor(context.getResources().getColor(R.color.LightTextMajorColour));
        this.copyText.setTextColor(context.getResources().getColor(R.color.LightTextMajorColour));
    }

    public void show() {
        setColor();
        if (Config.PAGEINFOSIGN != 0) SetWallDialog.setVisibility(View.GONE);
        else SetWallDialog.setVisibility(View.VISIBLE);

        if (Config.PAGEINFOSIGN == 2) copy.setVisibility(View.VISIBLE);
        else copy.setVisibility(View.GONE);

        dialog.setCancelable(false);
        dialog.setView(view);
        dialog.show();

    }

    // 点击分享监听
    private View.OnClickListener onShapeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Tool.Toast(context,"此功能暂不可用");
            /*Download download = new Download(context);

            String str = download.pathDownload();
            if (str != null){

                Tool.shapePic(context,str);
            }*/

        }
    };

    // 点击设置壁纸监听
    private View.OnClickListener onSetClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Download download = new Download(context);
            if (Config.PAGEINFOSIGN == 0) {

                download.setWall();


            } else {


//                download.setWall();
                Tool.Toast(context, "该图片暂不支持设置为壁纸");
            }

        }
    };

    // 点击下载监听
    private View.OnClickListener onDownloadClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Download download = new Download(context);
            download.download();
            dialog.dismiss();

        }
    };

    private View.OnClickListener onCopyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Config.PAGEINFOSIGN == 2){
                String str = Config.OnePicPicInfo.get(Config.numPicInfo).getForward();
                Tool.getCopy(context,str);
                dialog.dismiss();
            }
        }
    };

    // 取消监听
    private View.OnClickListener onCancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    };

}
