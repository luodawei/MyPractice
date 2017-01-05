package com.david.practice.Intentpractice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/1/4.
 */
public class ActivityTwo extends Activity {
    LinearLayout linearLayout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        button = new Button(this);
        LinearLayout.LayoutParams btnLP =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(btnLP);
        button.setText("点击返回");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout.addView(button);
        setContentView(linearLayout);
        Log.i("ActivityTwo===>","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityTwo===>","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityTwo===>","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityTwo===>","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityTwo===>","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityTwo===>","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityTwo===>","onRestart");
    }
}
