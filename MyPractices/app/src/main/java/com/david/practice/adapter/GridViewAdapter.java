package com.david.practice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.david.practice.R;

/**
 * Created by David on 2016/10/16.
 */

public class GridViewAdapter extends BaseAdapter {
    Context context;
    public GridViewAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return getImageId.length;
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
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(context);
            imageView.setBackgroundResource(R.color.colorAccent);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
        }else {
            imageView = (ImageView)convertView;
        }
        imageView.setImageResource(getImageId[position]);
        return imageView;
    }
    private int[] getImageId = {R.mipmap.item_img_two,R.mipmap.item_img_two,R.mipmap.item_img_two,
            R.mipmap.item_img_two,R.mipmap.item_img_two,R.mipmap.item_img_two,
            R.mipmap.item_img_two,R.mipmap.item_img_two,R.mipmap.item_img_two,
            R.mipmap.item_img_two,R.mipmap.item_img_two,R.mipmap.item_img_two,
    };
}
