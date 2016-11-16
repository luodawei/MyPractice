package com.david.practice.sql.pracitce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/9.
 */
public class MyAccount extends Activity {
    Button income;
    Button expend;
    Button accountList;
    Button statistics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);
        income = (Button) findViewById(R.id.income);
        expend = (Button) findViewById(R.id.expend);
        accountList = (Button) findViewById(R.id.account_list);
        statistics = (Button) findViewById(R.id.statistics);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.income:
                    break;
                case R.id.expend:
                    break;
                case R.id.account_list:
                    break;
                case R.id.statistics:
                    break;
            }
        }
    };
}
