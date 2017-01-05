package com.david.practice.selectportrait;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.david.practice.R;

import java.io.File;

/**
 * Created by Administrator on 2017/1/5.
 */
public class SelectPortrait extends Activity {
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header.jpg";
    private ImageView myPortrait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_portrait);
        setupViews();
    }
    private void setupViews(){
        myPortrait = (ImageView) findViewById(R.id.img_protrait);
        Button btnSelectImg = (Button) findViewById(R.id.btn_select_img);
        Button btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
        btnSelectImg.setOnClickListener(getOnClickListener());
        btnTakePhoto.setOnClickListener(getOnClickListener());
    }
    private boolean isSDCardExisting(){
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }
    }
    private View.OnClickListener getOnClickListener(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_select_img:
                        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent,IMAGE_REQUEST_CODE);
                        break;
                    case R.id.btn_take_photo:
                        if (isSDCardExisting()){
                            Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");//拍照
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,getImageUri());
                            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
                            startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE);
                        }else {
                            Toast.makeText(v.getContext(),"请插入SD卡",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };
        return onClickListener;
    }
    public void resizeImage(Uri uri){//重塑图片大小
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");//可以裁剪
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,RESIZE_REQUEST_CODE);
    }
    private void showResizeImage(Intent data){//显示图片
        Bundle extras = data.getExtras();
        if (extras != null){
            Bitmap photp = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photp);
            myPortrait.setImageDrawable(drawable);
        }
    }
    private Uri getImageUri(){
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!=RESULT_OK){
            return;
        }else {
            switch (requestCode){
                case IMAGE_REQUEST_CODE:
                    resizeImage(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if (isSDCardExisting()){
                        resizeImage(getImageUri());
                    }else {
                        Toast.makeText(SelectPortrait.this,"未找到存储卡，无法存储照片！",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RESIZE_REQUEST_CODE:
                    if (data!=null){
                        showResizeImage(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
