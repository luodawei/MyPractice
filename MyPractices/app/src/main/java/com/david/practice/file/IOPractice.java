package com.david.practice.file;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.david.practice.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/8.
 */
public class IOPractice extends Activity {
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.io_practice);
        btnAdd = (Button) findViewById(R.id.add);
        btnAdd.setOnClickListener(onClickListener);
        inPut();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add:
                    outPut();
                    break;
            }
        }
    };
    public void outPut(){
        try {
            FileOutputStream fileOutputStream = this.openFileOutput("david.txt", Context.MODE_APPEND);//追加模式
            String str = "1234";
            fileOutputStream.write(str.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void inPut(){
        try {
            FileInputStream fileInputStream = this.openFileInput("david.txt");
            byte[] bytes = new byte[1024];
            fileInputStream.read(bytes);
            String str = new String(bytes);
            Log.i("bytes",""+str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
