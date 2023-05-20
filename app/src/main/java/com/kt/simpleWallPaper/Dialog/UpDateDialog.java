package com.kt.simpleWallPaper.Dialog;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;
import com.kt.simpleWallPaper.api.My.base.UpDateBase;

public class UpDateDialog extends AlertDialog {
    UpDateBase upDateBase;

    public UpDateDialog(Context context, UpDateBase upDateBase) {
        super(context);
        this.upDateBase = upDateBase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dialog);
        initView();
        initData();
    }

    private void initData() {

        setCancelable(false);

        title.setText(upDateBase.getTitle());
        text.setText(upDateBase.getText());
        confirmText.setText("更新("+upDateBase.getSize()+"MB)");
        cancelText.setText("暂不更新");
        if (upDateBase.getMust().equals("Y")){
            cancel.setVisibility(View.GONE);
        }else {
            cancel.setVisibility(View.VISIBLE);
        }

    }


    TextView title, text, confirmText, cancelText;
    LinearLayout cancel, confirm;

    private void initView() {

        title = findViewById(R.id.title);
        text = findViewById(R.id.text);
        confirmText = findViewById(R.id.confirmText);
        cancelText = findViewById(R.id.cancelText);
        cancel = findViewById(R.id.cancel);
        confirm = findViewById(R.id.confirm);


        cancel.setBackground(Tool.clickResult());
        confirm.setBackground(Tool.clickResult());
        cancel.setOnClickListener(cancelOnClickListener);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);



    }

    public void setConfirmOnClickListener(View.OnClickListener onClickListener) {
        confirm.setOnClickListener(onClickListener);
    }

    private View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };


}
