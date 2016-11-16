package com.david.practice.internet;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.david.practice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/11/15.
 */
public class InternetActivity extends Activity {
    LinearLayout internetLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        internetLayout = (LinearLayout) findViewById(R.id.internet_layout);
        String[] name = {"InetAddress","getData","demoGet","demoPost"};
        for(int i = 0;i<name.length;i++){
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(name[i]);
            button.setId(500+i);
            button.setOnClickListener(getOnClickListener());
            internetLayout.addView(button);
        }

    }
    String params = "\"{\n" +
            "\"params\": [\n" +
            "    {\n" +
            "      \"username\":\"test\",\n" +
            "      \"cmdid\":\"1000\",\n" +
            "      \"logid\": \"12345\",\n" +
            "      \"appid\": \"您的apikey\",\n" +
            "      \"clientip\":\"10.23.34.5\",\n" +
            "      \"type\":\"st_groupverify\",\n" +
            "      \"groupid\": \"0\",\n" +
            "      \"versionnum\": \"1.0.0.1\",\n" +
            "    }\n" +
            "  ],\n" +
            "  \"jsonrpc\": \"2.0\",\n" +
            "  \"method\": \"Delete\",\n" +
            "  \"id\":12\n" +
            "}\"";
    public void demoPost(){
        HttpURLConnection httpURLConnection = null;
        String httpUrl ="http://apis.baidu.com/idl_baidu/faceverifyservice/face_deleteuser";
        try {
            URL url = new URL(httpUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);//不适用缓存
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("apikey","c50939bff71068c16156ec968cf6c0a7");//设置请求参数到header中
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(params.getBytes("utf-8"));//将请求参数（bodyParams）写入到输出流当中
            outputStream.flush();
            outputStream.close();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s = bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("data====>:",stringBuilder.toString());
            }else {
                Log.i("请求失败","状态码为："+httpURLConnection.getResponseCode());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void demoGet(){
        //专门用于HTTP连接的对象的类
        //这个类提供可以不知道数据长度的情况下使用发送流，接受流的方式
        HttpURLConnection httpURLConnection = null;
        String httpURI = "http://apis.baidu.com/apistore/weatherservice/citylist";
        try {
            URL url = new URL(httpURI+"?"+"cityname="+ URLEncoder.encode("重庆","UTF-8"));
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("apikey","c50939bff71068c16156ec968cf6c0a7");//设置请求参数到header中
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("data====>:",stringBuilder.toString());
                String data = stringBuilder.toString();
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(path+"/data.txt");
                if (!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bytes = data.getBytes();
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            }else {
                Log.i("请求失败","状态码为："+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void demoInetAddress(){
        try {
            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            Log.i("InetAddress",inetAddress.getHostAddress());//获取域名的IP地址
            Log.i("是否能连接到IP地址","====="+inetAddress.isReachable(1000));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener getOnClickListener(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case 500:
                        new Thread(){
                            @Override
                            public void run() {
                                demoInetAddress();
                            }
                        }.start();
                        break;
                    case 501:
                        new Thread(){
                            @Override
                            public void run() {
                                getData();
                            }
                        }.start();
                        break;
                    case 502:
                        new Thread(){
                            @Override
                            public void run() {
                                demoGet();
                            }
                        }.start();
                        break;
                    case 503:
                        new Thread(){
                            @Override
                            public void run() {
                                demoPost();
                            }
                        };
                        break;
                }
            }
        };
        return onClickListener;
    }

    public void getData(){
        try {
            URL url = new URL("http://apis.baidu.com/apistore/weatherservice/citylist");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200){
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();//可变的字符序列
                String str;
                while ((str = bufferedReader.readLine())!=null){
                    stringBuilder.append(str);//读取出来的每行字符添加到字符序列
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        long errNum = jsonObject.optLong("errNum",-1);
                        String errMsg = jsonObject.optString("errMsg","errMsg");
                        Log.i("errNum",""+errNum);
                        Log.i("errMsg",""+errMsg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    /*try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONArray jsonArray = jsonObject.optJSONArray("str");
                        for (int i = 0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            long errNum = object.optLong("errNum",-1);//通过key找到value，如果没有找到返回默认值；
                            String errMsg = object.optString("errMsg");
                            Log.i("errNum",""+errNum);
                            Log.i("errMsg",""+errMsg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                }
                Log.i("InputStream",stringBuilder.toString());
            }else {//如果连接失败，则打印状态码
                Log.i("getRespondCode()",""+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
