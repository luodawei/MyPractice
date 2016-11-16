package com.david.practice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.david.practice.adapter.MySimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 2016/10/8.
 */

public class ListPractice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_practice);
        ListView listView = (ListView)findViewById(R.id.practice_list);
        int[] to = {R.id.list_item_title,
                R.id.item_img_one,
                R.id.item_img_two,
                R.id.item_img_three,
                R.id.list_item_source,
                R.id.list_item_date,
                R.id.list_item_recommend,
                R.id.practice_level
        };
        List<HashMap<String,Object>> list = getDate();
        String[] from = {"title","imgOne","imgTwo","imgThree","source","date","recommend","level"};
        /*SimpleAdapter simpleAdapter =  new SimpleAdapter(ListPractice.this,
                list,
                R.layout.list_practice_item,
                from,
                to);*/
        MySimpleAdapter mySimpleAdapter = new MySimpleAdapter(ListPractice.this,
                list,
                R.layout.list_practice_item,
                from,
                to);
        listView.setAdapter(mySimpleAdapter);
    }
    private List<HashMap<String,Object>> getDate(){
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0;i<12;i++){
            //HashMap 和 Map 有什么区别？
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("title",R.string.list_item_title);
            map.put("imgOne",R.mipmap.item_img_one);
            map.put("imgTwo",R.mipmap.item_img_two);
            map.put("imgThree",R.mipmap.item_img_three);
            map.put("source","攀登科技");
            map.put("date",R.string.list_item_date);
            map.put("recommend","推荐"+(i+1));
            map.put("level",(float)Math.random()*5);
            list.add(map);
        }
        return list;
    }
}
