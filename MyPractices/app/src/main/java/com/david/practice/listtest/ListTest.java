package com.david.practice.listtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ListTest extends Activity {
    ListView testListView;
    boolean status = true;
    TextView testText;
    Button btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_test);
        testText = (TextView) findViewById(R.id.test_text);
        btnTest = (Button) findViewById(R.id.btn_test);
        testListView = (ListView) findViewById(R.id.test_list_view);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status){
                    status = false;
                }else {
                    status = true;
                }
            }
        });
        testListView.setAdapter(new ListTestAdapter(ListTest.this));
    }

}
