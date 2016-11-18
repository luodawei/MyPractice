package com.david.practice.thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.david.practice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public class ThreadPractice extends Activity {
    List<News> list;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_paractice);
        listView = (ListView) findViewById(R.id.thread_practice_list);
        Button btnThread = (Button) findViewById(R.id.btn_thread);
        Button btnSyn = (Button) findViewById(R.id.btn_syn);
        btnThread.setOnClickListener(onClickListener);
        btnSyn.setOnClickListener(onClickListener);
        list = new ArrayList<News>();

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_thread:
                    new Thread(){
                        @Override
                        public void run() {
                            getData();
                        }
                    }.start();
                    break;
                case R.id.btn_syn:
                    startAsyncTask();
                    Toast.makeText(ThreadPractice.this,"点击就送",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private void getData(){
        StringBuilder stringBuilder = new StringBuilder();//创建一个字符序列
        String httpUrl = "http://apis.baidu.com/txapi/weixin/wxhot?"+"num=10";
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("apikey","c50939bff71068c16156ec968cf6c0a7");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
                String s;
                while ((s = bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("data ====>",stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                for (int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    News news = new News();
                    news.setAuthor(jsonObject1.getString("ctime"));
                    news.setTitle(jsonObject1.getString("title"));
                    news.setContent(jsonObject1.getString("description"));
                    news.setImgId(jsonObject1.getString("picUrl"));
                    Log.i("picUrl",jsonObject1.getString("picUrl"));
                    list.add(news);
                }
                handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            listView.setAdapter(new NewsAdapter(ThreadPractice.this,list));
        }
    };

    public class MyAsyncTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... params) {//执行耗时操作，不进行UI操作
            StringBuilder stringBuilder = new StringBuilder();//可变字符序列
            try {
                URL url = new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("apikey","c50939bff71068c16156ec968cf6c0a7");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode()==httpURLConnection.HTTP_OK){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
                    String s;
                    while ((s =bufferedReader.readLine())!= null ){
                        this.publishProgress(s.length());
                        stringBuilder.append(s);
                    }
                    Log.i("data==>",stringBuilder.toString());
                    return stringBuilder.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    News news = new News();
                    news.setContent(object.getString("description"));
                    news.setTitle(object.getString("title"));
                    news.setAuthor(object.getString("ctime"));
                    news.setImgId(object.getString("picUrl"));
                    list.add(news);
                }
                NewsAdapter newsAdapter = new NewsAdapter(ThreadPractice.this,list);
                listView.setAdapter(newsAdapter);
                Log.i("listView","ListView");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
    public void startAsyncTask(){
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        String httpUrl = "http://apis.baidu.com/txapi/weixin/wxhot?"+"num=10";
        myAsyncTask.execute(httpUrl);
    }
}
