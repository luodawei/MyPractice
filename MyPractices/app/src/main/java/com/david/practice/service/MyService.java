package com.david.practice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/11/14.
 */
public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//startId启动的次数
        Log.i("Service","onStartCommand"+this.toString()+startId);
        return START_STICKY;
//        return START_NOT_STICKY;
//        return START_REDELIVER_INTENT;
//        return START_STICKY_COMPATIBILITY;
//        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service","onDestroy"+this.toString());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Service","onBind");
        return null;
    }
}
