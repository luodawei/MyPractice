package com.david.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 2016/10/24.
 */

public class ActivitySecond extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView phoneNum = (TextView)findViewById(R.id.phone_num);
        Button btnBack = (Button)findViewById(R.id.btn_back);
        Intent intent = getIntent();
        String numRes = intent.getStringExtra("phone_num");
        if (numRes!=null){
            phoneNum.setText(numRes);
        }
        btnBack.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        //这里放入数据是在点击按钮之后才执行的，如果不点按钮而点返回键，会直接执行finish()
        @Override
        public void onClick(View v) {
            Intent intentOne = getIntent();
            intentOne.putExtra("userName","David");
            setResult(110,intentOne);
            finish();
        }
    };
}
