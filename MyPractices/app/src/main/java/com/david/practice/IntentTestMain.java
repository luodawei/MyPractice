package com.david.practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by David on 2016/10/26.
 */

public class IntentTestMain extends Activity {
    LinearLayout linearLayout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = new LinearLayout(IntentTestMain.this);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        button = new Button(IntentTestMain.this);
        button.setText("点击跳转");
        linearLayout.addView(button);
        setContentView(linearLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentWithAction(IntentTestMain.this);
            }
        });
    }
    public void intentWithAction(Context context){
        Intent intent = new Intent();
        intent.setAction("MyAction");
        context.startActivity(intent);
    }
}
