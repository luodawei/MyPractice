package com.david.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by David on 2016/10/16.
 */

public class Practice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice);
        Button btnGridView = (Button)findViewById(R.id.btn_grid_view);
        Button btnDialog = (Button)findViewById(R.id.btn_dialog);
        btnGridView.setOnClickListener(onClickListener);
        btnDialog.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.btn_grid_view:
                    intent = new Intent(Practice.this,GridViewPractice.class);
                    startActivity(intent);
                    break;
                case R.id.btn_dialog:
                    Toast.makeText(Practice.this,"点击了dialog",Toast.LENGTH_SHORT);
                    intent = new Intent(Practice.this,DialogPractice.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
