package com.kt.simpleWallPaper.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kt.simpleWallPaper.R;

public class LongAlertDialogs extends AlertDialog {
    public LongAlertDialogs(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_dialog_long);
        initView();

    }

    private TextView cancelText, setText, downloadText, shapeText, copyText;
    private LinearLayout cancel, SetWallDialog, downloadRaw, shape, linear, copy;

    private void initView() {
        cancel = findViewById(R.id.cancel);
        SetWallDialog = findViewById(R.id.SetWallDialog);
        downloadRaw = findViewById(R.id.downloadRaw);
        shape = findViewById(R.id.shape);
        copy = findViewById(R.id.copy);
        cancel.setOnClickListener(cancelOnClickListener);


        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

    }

    public void setDialogOnClickListener(View.OnClickListener onClickListener){
        SetWallDialog.setOnClickListener(onClickListener);
        downloadRaw.setOnClickListener(onClickListener);
        shape.setOnClickListener(onClickListener);
        copy.setOnClickListener(onClickListener);

    }

    private View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };



}
