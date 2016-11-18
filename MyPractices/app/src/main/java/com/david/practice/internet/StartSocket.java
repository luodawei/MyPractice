package com.david.practice.internet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.david.practice.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Administrator on 2016/11/16.
 */
public class StartSocket extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_socket);
        Button btnSocket = (Button) findViewById(R.id.btn_socket);
        btnSocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        startSocket();;
                    }
                };
            }
        });
    }
    private void startSocket(){
        try {
            Socket socket = new Socket("10.0.0.2",29361);//Android模拟器访问本地PC的IP
            socket.setSoTimeout(10000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
            String data = bufferedReader.readLine();
            Log.i("data",""+data);
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
