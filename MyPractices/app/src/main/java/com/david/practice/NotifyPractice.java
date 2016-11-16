package com.david.practice;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by David on 2016/10/19.
 */

public class NotifyPractice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_practice);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.notify_practice);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        /*LinearLayout linearLayout = new LinearLayout(NotifyPractice.this);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);*/
        String[] btnName = {"Notification"};
        for (int i = 0;i<btnName.length;i++){
            Button button = new Button(NotifyPractice.this);
            button.setLayoutParams(layoutParams);
            button.setText(btnName[i]);
            button.setId(100+i);
            button.setOnClickListener(onClickListener);
            linearLayout.addView(button);
        }
    }
    //创建监听事件的对象
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case 100:
                    creatNotification();
                    break;
                case 101:
                    break;
                case 102:
                    break;
            }
        }
    };
    //创建notification
    public void creatNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifyPractice.this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(getDefaultIntent(Notification.FLAG_AUTO_CANCEL));
        builder.setContentTitle("写完去睡觉");
        builder.setContentText("滚去睡觉");
        Notification notification = builder.build();
        int ID_NOTIFICATION = 1;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(ID_NOTIFICATION,notification);
    }
    public PendingIntent getDefaultIntent(int flag){
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,new Intent(),flag);
        return pendingIntent;
    }
}
