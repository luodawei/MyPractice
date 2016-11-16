package com.david.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by David on 2016/10/24.
 */

public class ActivityFirst extends Activity{
    Intent intent;
    String verificationCode;
    EditText inputCode;
    Button getCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        inputCode=(EditText)findViewById(R.id.input_verification_code);

        getCode = (Button)findViewById(R.id.get_verification_code);

        getCode.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            verificationCode = inputCode.getText().toString();
            intent = new Intent(ActivityFirst.this,ActivitySecond.class);
            intent.putExtra("phone_num",verificationCode);
            startActivityForResult(intent,100);
        }
    };
    //需要从别的页面返回数据时，重写该方法,也可以在该方法里面对控件进行操作
    //传入的参数分别代表，请求码，结果码，包含你绑定的数据的Intent对象
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null){
            Log.i("intent",data.toString());
            getCode.setText(data.getStringExtra("userName"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
