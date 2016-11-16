package com.david.practice.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ServiceTwo extends Service {
    MyBinder myBinder = new MyBinder();
    public class MyBinder extends Binder{
        public ServiceTwo getService(){//通过内部类返回当前Service对象
            return ServiceTwo.this;
        }
        public void playMusic(){//创建一个内部类的方法用于调用当前类的方法
            Log.i("MyBinder","playMusic");
            ServiceTwo.this.playMusic();

        }
    }
    public void playMusic(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.two);
        mediaPlayer.start();
        Log.i("MyBinder","playMusic");
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {//执行绑定
        Log.i("ServiceTwo","onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {//解除绑定
        Log.i("ServiceTwo","onUnBind");
        return super.onUnbind(intent);
    }
}
