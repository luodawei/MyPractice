package com.david.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.david.practice.internet.InternetActivity;

/**
 * Created by Administrator on 2016/12/10.
 */
public class GuideActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Button complete = (Button) findViewById(R.id.btn_complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, InternetActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
