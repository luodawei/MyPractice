package com.david.practice.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    //参数分别是上下文，数据库名字，游标对象，版本号，异常handler
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    String CREATE_TABLE_SQL= "create table Student (id integer primary key AUTOINCREMENT,name varchar(200),chengji varchar(200))";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }
    //SQLiteDatabase 数据库操作类
    //execSQL 直接执行sql语句
    //insert 插入方法【封装好的插入数据的方法】
    //update 更新方法【封装好的】
    //query 查询方法
    //rawQuery 未加工的查询方法
    //delete 删除方法
    @Override
    //参数分别是数据库对象，旧版本，新版本
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
