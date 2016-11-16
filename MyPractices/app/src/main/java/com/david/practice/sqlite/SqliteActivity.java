package com.david.practice.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/8.
 */
public class SqliteActivity extends Activity {
    Button btnOpen;
    Button btnUpdata;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity);
        btnOpen = (Button) findViewById(R.id.btn_open);
        btnUpdata = (Button)findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnUpdata.setOnClickListener(onClickListener);
        btnOpen.setOnClickListener(onClickListener);
        btnDelete.setOnClickListener(onClickListener);
        openSQliteDataBase();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_open:
                    insert();
                    break;
                case R.id.btn_update:
                    Log.i("btn_update","btn_update");
                    update();
                    break;
                case R.id.btn_delete:
                    Log.i("btn_delete","btn_delete");
                    delete();
                    break;
            }
        }
    };
    SQLiteDatabase sqLiteDatabase = null;
    public void openSQliteDataBase(){
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this,"android31.db",null,1);
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
    }
    public void insert(){
        //语句 INSERT INTO 表名称 VALUES(值1，值.....)
        String insertSql = "insert into Student values(12,'laowang','56')";
        if (sqLiteDatabase != null){
            //sqLiteDatabase.execSQL(insertSql);
                                        //表名    可为空的列名      //类似于Map的一个类 用于保存数据库列名以及对应的值
            //sqLiteDatabase.insert(String table,String nullCoulm,ContentValues contentvalues);
            ContentValues contentValues = new ContentValues();//创建一个ContentValues对象
            //往对象中添加列名以及对应的值
            contentValues.put("id",15);
            contentValues.put("name","马蓉");
            contentValues.put("chengji",100);
            sqLiteDatabase.insert("Student",null,contentValues);
        }
    }

    public void update(){
        //UPDATE 表名称 SET 列名称 = 新值，列名称=新值，WHERE 列名称 = 某值
        String updateSql = "update Student Set name='马蓉' ,chengji = 13 where id=12";
        if (sqLiteDatabase != null){
            //sqLiteDatabase.execSQL(updateSql);
            ContentValues contentValues = new ContentValues();
            contentValues.put("chengji",65);
            String[] caluseArgs = {"15","马蓉"};
            sqLiteDatabase.update("Student",contentValues,"id=? and name=?",caluseArgs);
        }
    }
    public void delete(){
        //DELETE FROM 表名称 WHERE = 值
        String deleteSql = "delete from Student where id = 15";
        if(sqLiteDatabase!=null){
            //sqLiteDatabase.execSQL(deleteSql);
            sqLiteDatabase.delete("Student","id=?",new String[]{"15"});
        }
    }
    public void select(){
        //SELECT name,SUM(chengji) FROM Student GROUP BY name
        Cursor cursor = sqLiteDatabase.query("Student",new String[]{"name","sum(chengji)"},null,null,"name",null,null);
        if (!cursor.isFirst()){
            cursor.moveToFirst();
            int num = cursor.getColumnCount();
            for(int i=0;i<num;i++){
                String columnName = cursor.getColumnName(i);
                String value = cursor.getString(i);
                Log.i("columnName",columnName);
                Log.i("value",value);
            }
        }
        while (cursor.moveToNext()){
            int num = cursor.getColumnCount();
            for(int i=0;i<num;i++){
                String columnName = cursor.getColumnName(i);
                String value = cursor.getString(i);
                Log.i("columnName",columnName);
                Log.i("value",value);
            }
        }
        cursor.close();
    }
}
