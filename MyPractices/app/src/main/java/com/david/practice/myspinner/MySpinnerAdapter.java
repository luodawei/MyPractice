package com.david.practice.myspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.david.practice.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public class MySpinnerAdapter extends BaseAdapter {
    private List<Person> mList;
    private Context context;
    private LayoutInflater inflater;
    public MySpinnerAdapter(List<Person> mList,Context context){
        this.mList = mList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.my_spinner_item,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(mList.get(position).getPersonName());
        return convertView;
    }
}
