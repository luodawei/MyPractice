package com.david.practice.Intentpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/1/4.
 */
public class ActivityOne extends Activity {
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
        button.setText("点击跳转");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);
                startActivity(intent);
            }
        });
        linearLayout.addView(button);
        setContentView(linearLayout);
        Log.i("ActivityOne===>","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityOne===>","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityOne===>","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityOne===>","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityOne===>","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityOne===>","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityOne===>","onRestart");
    }
}
