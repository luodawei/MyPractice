package com.david.practice.uri;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/10.
 */
public class UriPractice extends Activity {
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnGetContact;
    private Button btnAddContact;
    private Button btnUpdateContact;
    private Button btnDeleteContact;

    CantactUtil cantactUtil = new CantactUtil();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uri_practice);

        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_two);
        btnThree = (Button) findViewById(R.id.btn_three);
        btnGetContact = (Button) findViewById(R.id.btn_get_contact);
        btnAddContact = (Button) findViewById(R.id.btn_add_contact);
        btnUpdateContact = (Button) findViewById(R.id.btn_update_contact);
        btnDeleteContact = (Button) findViewById(R.id.btn_delete_contact);

        btnOne.setOnClickListener(getOnClickListener());
        btnTwo.setOnClickListener(getOnClickListener());
        btnThree.setOnClickListener(getOnClickListener());
        btnGetContact.setOnClickListener(getOnClickListener());
        btnAddContact.setOnClickListener(getOnClickListener());
        btnUpdateContact.setOnClickListener(getOnClickListener());
        btnDeleteContact.setOnClickListener(getOnClickListener());

        requestMission();
    }

    public View.OnClickListener getOnClickListener(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_one:
                        Uri uri = Uri.parse("http://www.baidu.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.btn_two:
                        Uri uri1 = Uri.parse("tel:15340528845");
                        Intent intent1 = new Intent(Intent.ACTION_CALL, uri1);
                        if (ActivityCompat.checkSelfPermission(UriPractice.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(intent1);
                        break;
                    case R.id.btn_three:
                        Uri uri2 = Uri.parse("smsto:15340528845");
                        Intent intent2 = new Intent(Intent.ACTION_SENDTO,uri2);
                        intent2.putExtra("向新春","sendmessage");
                        startActivity(intent2);
                        break;
                    case R.id.btn_get_contact:
                        cantactUtil.getContactsList(UriPractice.this);
                        break;
                    case R.id.btn_add_contact:
                        cantactUtil.addContact(UriPractice.this,"David","13458498646");
                        break;
                    case R.id.btn_update_contact:
                        break;
                    case R.id.btn_delete_contact:
                        break;
                }
            }
        };
        return onClickListener;
    }

    public void requestMission(){
        if (Build.VERSION.SDK_INT >= 23){
            this.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS},9527);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 9527){
            cantactUtil.getContactsList(this);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    
}
