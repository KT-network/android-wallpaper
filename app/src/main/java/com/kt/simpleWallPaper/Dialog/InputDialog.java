package com.kt.simpleWallPaper.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kt.simpleWallPaper.R;
import com.kt.simpleWallPaper.Tool;

public class InputDialog extends AlertDialog {
    public InputDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input_text);
        initView();

    }

    TextView title;
    EditText inputTextEdit;
    LinearLayout cancel, confirm;
    private void initView() {
        title = findViewById(R.id.title);
        inputTextEdit = findViewById(R.id.inputTextEdit);
        cancel = findViewById(R.id.cancel);
        confirm = findViewById(R.id.confirm);
        cancel.setOnClickListener(cancelOnClickListener);


        cancel.setBackground(Tool.clickResult());
        confirm.setBackground(Tool.clickResult());
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    public void setConfirmOnClickListener(View.OnClickListener onClickListener){
        confirm.setOnClickListener(onClickListener);
    }

    public String getEdit(){
        return inputTextEdit.getText().toString();
    }

    public void setTitle(String msg){
        title.setText(msg+"");
    }

    private View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };

}
