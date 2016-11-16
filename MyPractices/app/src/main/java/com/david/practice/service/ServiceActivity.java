package com.david.practice.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ServiceActivity extends Activity {
    LinearLayout serviceLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        serviceLayout = (LinearLayout) findViewById(R.id.service_layout);
        String[] datas = {"start service", "stop service","on bind","on unbind","play music","stop music"};
        for (int i = 0; i < datas.length; i++) {
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(layoutParams);
            button.setText(datas[i]);
            button.setId(1000 + i);
            button.setOnClickListener(getOnClickListener());
            serviceLayout.addView(button);
        }
    }

    Intent intent;

    public View.OnClickListener getOnClickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case 1000:
                        start();
                        break;
                    case 1001:
                        stop();
                        break;
                    case 1002:
                        bind();
                        break;
                    case 1003:
                        unbind();
                        break;
                    case 1004:
                        playMusic();
                        break;
                    case 1005:
                        stopMusic();
                        break;
                }
            }
        };
        return onClickListener;
    }
    MediaPlayer mediaPlayer;
    public void playMusic(){
        mediaPlayer = MediaPlayer.create(this,R.raw.one);
        mediaPlayer.start();
    }
    public void stopMusic(){
        mediaPlayer.stop();
    }
    public void start(){
        intent = new Intent(ServiceActivity.this,MyService.class);
        startService(intent);
    }

    public void stop(){
        stopService(intent);
    }
    public void bind(){
        Intent intent2 = new Intent(this,ServiceTwo.class);
        //intent, 服务连接对象, 创建service模式
        bindService(intent2,serviceConnection, Context.BIND_AUTO_CREATE);
    }
    public void unbind(){
        unbindService(serviceConnection);//与服务解除绑定
    }
    ServiceTwo serviceTwo;
    //创建一个服务连接的对象，可用来监听服务的连接与断开
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override//服务连接的回调方法
        public void onServiceConnected(ComponentName name, IBinder service) {//此处的Binder对象是OnBind方法返回过来的
            ServiceTwo.MyBinder myBinder = (ServiceTwo.MyBinder) service;
            serviceTwo = myBinder.getService();
            myBinder.playMusic();
//            serviceTwo.playMusic();
            Log.i("serviceConnection","serviceConnected");
        }

        @Override//服务断开连接的回调方法  当服务器崩溃或被kill掉，执行调用
        public void onServiceDisconnected(ComponentName name) {
            Log.i("ServiceDisconnect","ServiceDisconnected");
        }
    };
}
