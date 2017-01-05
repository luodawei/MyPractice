package com.david.practice.listtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ListTestAdapter extends BaseAdapter {
    ImageView imageView;
    ListTest context;
    LayoutInflater inflater;
    public ListTestAdapter(ListTest context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_practice_item,null);
        }
        ImageView itemImgOne = BaseViewHolder.get(convertView,R.id.item_img_one);
        itemImgOne.setImageResource(R.mipmap.ic_launcher);
        /*View view = inflater.inflate(R.layout.list_practice_item,null);
        imageView = (ImageView) view.findViewById(R.id.item_img_one);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.testText.setText("子项点击"+position);
            }
        });
        if (context.status){
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else {
            imageView.setImageResource(R.mipmap.item_img_one);
        }*/
        return convertView;
    }
}
