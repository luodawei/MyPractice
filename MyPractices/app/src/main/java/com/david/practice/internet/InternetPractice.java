package com.david.practice.internet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.david.practice.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/16.
 */
public class InternetPractice extends Activity {
    LinearLayout internetPractice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_practice);
        String[] btnName = {"get","post"};
        internetPractice = (LinearLayout) findViewById(R.id.internet_practice);
        for (int i = 0;i<btnName.length;i++){
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(btnName[i]);
            button.setId(1111+i);
            button.setOnClickListener(getOnClicklistener());
            internetPractice.addView(button);
        }
    }
    private View.OnClickListener getOnClicklistener(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case 1111:
                        practiceGet();
                        break;
                    case 1112:
                        break;
                    case 1113:
                        break;
                }
            }
        };
        return onClickListener;
    }

    private void practiceGet(){
        HttpURLConnection httpURLConnection = null;
        String httpUrl = "http://211.149.190.90/api/register?tel=TEL&password=PASSWORD&verify=VERIFY";
        try {
            URL url = new URL(httpUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("Get");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("tel","18883285936");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s = bufferedReader.readLine()) != null){

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
