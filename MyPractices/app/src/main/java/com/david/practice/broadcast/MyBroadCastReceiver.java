package com.david.practice.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/10.
 */
public class MyBroadCastReceiver extends BroadcastReceiver {
    //接收的回调，可以接收所有广播
    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播后执行逻辑
        if (intent.getAction().equals("www.baidu.com.play")){
            Log.i("onReceive","播放音乐");
            Toast.makeText(context,"播放",Toast.LENGTH_SHORT).show();
        }else {
            Log.i("onReceive","其他广播乱入");
        }
    }
}
