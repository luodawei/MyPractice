package com.david.practice.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/10.
 */
public class MyBroadCastReceiverActivity extends Activity {
    Button btnOne;
    Button btnTwo;
    Button btnThree;

    MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_broadcast_receiver_activity);
        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_two);
        btnThree = (Button)findViewById(R.id.btn_three);

        btnOne.setOnClickListener(getOnClickListener());
        btnTwo.setOnClickListener(getOnClickListener());
        btnThree.setOnClickListener(getOnClickListener());
    }
    //静态注册

    public View.OnClickListener getOnClickListener(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_one:
                        registerBroadCast();
                        break;
                    case R.id.btn_two:
                        send();
                        break;
                    case R.id.btn_three:
                        unRegister();
                        break;
                }
            }
        };
        return onClickListener;
    }

    public void registerBroadCast(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("www.baidu.com.play");
        intentFilter.addAction("www.baidu.com.hello");
        this.registerReceiver(myBroadCastReceiver,intentFilter);
    }

    public void unRegister(){
        this.unregisterReceiver(myBroadCastReceiver);
    }

    public void send(){
        Intent intent = new Intent();
        intent.putExtra("id",110);
        intent.setAction("www.baidu.com.play");//设置动作
        sendBroadcast(intent);//发送
    }
}
