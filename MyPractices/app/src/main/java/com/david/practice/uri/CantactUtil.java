package com.david.practice.uri;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by Administrator on 2016/11/10.
 */
public class CantactUtil{
    //读取联系人
    //1、先读取contacts表，获取ContactsID
    //2、再在raw_contacts表中根据ContactsID获取RawContactsID;
    //3、然后就可以在data表中根据RawContactsID获取该联系人的各数据了。
    public static void getContactsList(Context context){
        //获取用来操作数据的类的对象，对联系人的基本操作都是使用这个对象
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String rawContactsId = "";
                int columnNumb = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String id = cursor.getString(columnNumb);
                Cursor rawContactsIdCur = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI,
                        null,
                        ContactsContract.RawContacts.CONTACT_ID +"= ?",
                        new String[]{id},
                        null);
                if (rawContactsIdCur.moveToFirst()){
                    rawContactsId = rawContactsIdCur.getString(rawContactsIdCur.getColumnIndex(ContactsContract.RawContacts._ID));
                }
                rawContactsIdCur.close();
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))>0){
                    Cursor phoneCur = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{rawContactsId},null);
                    while (phoneCur.moveToNext()){
                        String number = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String name = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    }
                    phoneCur.close();
                }
            }
        }
    }
    public static long addContact(Context context,String name,String phoneNum){
        ContentValues values = new ContentValues();
        Uri rawContactUri = context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        if (name!=""){
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID,rawContactId);
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
        }
        if (phoneNum != ""){
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID,rawContactId);
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,phoneNum);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
        }
        return rawContactId;
    }
    public static void updateContact(Context context,long rawContactId){
        ContentValues values = new ContentValues();
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"15023244541");
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        String where = ContactsContract.Data.RAW_CONTACT_ID+"=? AND"+ ContactsContract.Data.MIMETYPE+"=?";
        String[] selectionArgs = new String[]{String.valueOf(rawContactId), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE};
        context.getContentResolver().update(ContactsContract.Data.CONTENT_URI,values,where,selectionArgs);
    }
    //删除联系人
    //只需要将raw_contacts表中指定RawContactID的行删除，其他表中与之关联的数据都会自动删除
    public static void deleteContact(Context context,long rawContactId){
        context.getContentResolver().delete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI,rawContactId),null,null);//删除指定rawContactId的那条数据
    }
}
