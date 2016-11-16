package com.david.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/3.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    public MyAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 5;
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
        View view;
        if (convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_practice_item,null);
        }else {
            view = convertView;
        }
        return view;
    }
}
