package com.david.practice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by David on 2016/10/12.
 */

public class DialogPractice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_practice);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.dialog_practice);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        String[] buttonName = {"alert dialog","progress dialog","date dialog","time dialog"};
        for (int i = 0;i<buttonName.length;i++){
            Button button = new Button(DialogPractice.this);
            button.setLayoutParams(layoutParams);
            button.setText(buttonName[i]);
            button.setId(500+i);
            button.setOnClickListener(onClickListener);
            linearLayout.addView(button);
        }
    }
    //创建TimeDIalog
    public void creatTimeDialog(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(DialogPractice.this,onTimeSetListener,19,29,true);
        timePickerDialog.setTitle("时间选择器");
        timePickerDialog.setIcon(R.mipmap.item_img_one);
        timePickerDialog.show();
    }
    //创建OnTimeSetListener
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Toast.makeText(DialogPractice.this,hourOfDay+"点"+minute+"分",Toast.LENGTH_SHORT).show();
        }
    };
    //创建Dialog
    public void creatDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(DialogPractice.this);
        builder.setIcon(R.mipmap.item_img_one);
        builder.setTitle("AlertDialog");
//        builder.setMessage("确定要下载吗");
       /* builder.setItems(R.array.test_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogPractice.this,"点击了"+which,Toast.LENGTH_SHORT).show();
            }
        });*/
       /* builder.setSingleChoiceItems(R.array.test_item, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogPractice.this,"你选择了"+which,Toast.LENGTH_SHORT).show();
            }
        });*/
        /*builder.setMultiChoiceItems(R.array.test_item, new boolean[]{true, false, true, true}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(DialogPractice.this,"你点击了"+which+"选择状态为"+isChecked,Toast.LENGTH_SHORT).show();
            }
        });*/
        LayoutInflater layoutInflater = LayoutInflater.from(DialogPractice.this);
        View view = layoutInflater.inflate(R.layout.dialog_test_item,null);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogPractice.this,"你点击了"+which,Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }
    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case 500:
                    creatDialog();
                    break;
                case 501:
                    break;
                case 502:
                    break;
                case 503:
                    creatTimeDialog();
                    break;
            }
        }
    };
}
