package com.david.practice.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SharedPreferencesPractice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences_practice);
        demoSharedPerferences();
    }
    //sharedPreferences轻量级的存储 缓存
    //在我们的app的安装目录结构下创建一个文件夹，并存储到xxx.xml文件中。
    //结构为key-value的形式
    public void demoSharedPerferences(){
        //SharedPreferences sharedPreferences = getSharedPreferences(String name, int mode );
        //int mode Context
        //Context.MODE_PRIVATE 私有模式 除本应用外其他应用无法更改缓存文件
        //Context.MODE_WORLD_READABLE 读取权限 其他应用只能读取的模式
        //Context.MODE_WORLD_WRITEABLE 读写权限
        //1.getSharedPreferences 的对象
        SharedPreferences sharedPreferences = getSharedPreferences("david", Context.MODE_PRIVATE);
        //2.使用SharedPreferes存储数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取对数据进行操作的类的对象
        editor.putString("user_name","luodawei");
        editor.commit();
        //3.使用sp对象进行缓存的获取
        //sharedPreferences.getString(String key,String defvalue) defvalue防止key对应的value为空或找不到，返回的值。
        String user_name = sharedPreferences.getString("user_name","default_name");
        Log.i("user_name",""+user_name);
    }
}
