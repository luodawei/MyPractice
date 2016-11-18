package com.david.practice.thread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.practice.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public class NewsAdapter extends BaseAdapter {
    Context context;
    List<News> list;
    public NewsAdapter(Context context,List<News> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView==null){
            convertView = layoutInflater.inflate(R.layout.thread_practice_item,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.item_content);
            viewHolder.author = (TextView) convertView.findViewById(R.id.item_author);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.author.setText(list.get(position).getAuthor());
        viewHolder.content.setText(list.get(position).getContent());
        MyHandler myHandler = new MyHandler();
        myHandler.setViewHolder(viewHolder);
        MyThread myThread = new MyThread();
        myThread.setMyHandler(myHandler);
        myThread.setImageURL(list.get(position).getImgId());
        myThread.start();
        return convertView;
    }
    class MyThread extends Thread{
        String imageURL;
        MyHandler myHandler;
        public void setMyHandler(MyHandler myHandler){
            this.myHandler = myHandler;
        }
        public void setImageURL(String imageURL){
            this.imageURL = imageURL;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageURL);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                if (bitmap!=null){
                    myHandler.setBitmap(bitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myHandler.sendEmptyMessage(0);
        }
    }
    class MyHandler extends Handler{
        ViewHolder viewHolder;
        Bitmap bitmap;
        public void setBitmap(Bitmap bitmap){
            this.bitmap = bitmap;
        }
        public void setViewHolder(ViewHolder viewHolder){
            this.viewHolder = viewHolder;
        }

        @Override
        public void handleMessage(Message msg) {
            if (bitmap!=null){
                viewHolder.img.setImageBitmap(bitmap);
            }
        }
    }
}
