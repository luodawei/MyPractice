package com.david.practice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by David on 2016/10/26.
 */

public class IntentTestTwo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(IntentTestTwo.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        TextView textView = new TextView(IntentTestTwo.this);
        textView.setText("what'wrong");
        linearLayout.addView(textView);
        setContentView(linearLayout);
    }
}
