package com.david.practice.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.david.practice.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 2016/10/10.
 */

public class MySimpleAdapter extends BaseAdapter {
    private List<HashMap<String,Object>> list;
    private String[] from;
    private int[] to;
    private int resourceId;
    private Context context;
    public MySimpleAdapter(Context context,List<HashMap<String,Object>> list,int resourceId,String[] from,int[] to){
        this.context = context;
        this.list = list;
        this.resourceId = resourceId;
        this.from = from;
        this.to = to;
    }
    //下面这些方法什么时候调用的呢？
    @Override
    //获取数据源大小的方法
    public int getCount() {
        return list.size();//设置显示的布局的数量，如果值为零，
    }
    //通过ID获得数据源的每一项的对象
    @Override
    public Object getItem(int position) {
        return list.get(position);//返回数据源的每一项的对象
    }
    //获取数据源的ID
    @Override
    public long getItemId(int position) {
        return position;//下标
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        if(convertView == null){
            view = layoutInflater.inflate(resourceId, null);
        }
        else {
            view = convertView;
        }
        final HashMap<String,Object> map = list.get(position);//获得每个列表项的数据源对象
        for(int i = 0;i<to.length;i++){//循环获得每一个ID对应的view
            int subViewId = to[i];//拿到每一个view的id
            View subView = view.findViewById(subViewId);
            if (subView instanceof TextView){
                TextView textView = (TextView)subView;
                String key = from[i];
                if (map.get(key) instanceof Integer){
                    int textId = (Integer)map.get(key);
                    textView.setText(textId);
                }
                if(map.get(key) instanceof String) {
                    String str = map.get(key).toString();
                    textView.setText(str);
                }
            }
            if(subView instanceof ImageView){
                ImageView imageView = (ImageView)subView;
                String key = from[i];
                int imageId = (Integer)map.get(key);
                imageView.setImageResource(imageId);
            }
            if(subView instanceof RatingBar){
                RatingBar ratingBar = (RatingBar)subView;
                String key = from[i];
                float progress = (float) map.get(key);
                ratingBar.setRating(progress);
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            HashMap<String,Object> map1 = list.get(position);
                                map1.put("level",rating);
                                Log.i("map.get(key)",""+map.get("level"));
                        }
                    }
                });
            }
        }
        return view;
    }
}
