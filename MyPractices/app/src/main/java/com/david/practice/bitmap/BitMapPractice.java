package com.david.practice.bitmap;

import android.Manifest;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;

import com.david.practice.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/7.
 */
public class BitMapPractice extends Activity {
    ImageView bitmapImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmapImg = (ImageView)findViewById(R.id.bitmap_img);
        checkUserPermission();
        //createBitmapTwo();
    }
    public  void createBitMap(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);//将res下的图片转换成bitmap
        Bitmap bitmap1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"qyc.jpg");
        AssetManager assetManager = getAssets();
        try {
            //通过管理器打开asset目录下对应的某个文件的InputStream
            InputStream inputStream = assetManager.open("img,jpg");
            Bitmap bitmap2 = BitmapFactory.decodeStream(inputStream);//将输入流的对象转换成一个bitmap
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //动态权限的请求
    public void checkUserPermission(){//需要用户点击确认
        if (Build.VERSION.SDK_INT >=23){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1111);//请求码
        }

    }
    //动态函数的回调的函数
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i("requestCode",""+requestCode);
        createBitmapTwo();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public void createBitmapTwo(){
        //创建一个Options的对象，用于设置相关参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;//设置为true则Bitmap对象为空，获取高度需要拿options对象取
        options.inSampleSize = 16;//图片宽高的缩放倍数，如果设置为4，则宽高都为原来的1/4，图是原来的1/16
        options.inDensity = 20;//像素压缩比
        options.inTargetDensity = 1;//目标位图的像素压缩比
        options.inScaled = true;//压缩图片从inDensity到inTargetDensity的压缩比例
        options.inPreferredConfig = Bitmap.Config.RGB_565;//设置位图参数
        Bitmap bitmap1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/qyc.jpg",options);
        bitmapImg.setImageBitmap(bitmap1);
    }
}
