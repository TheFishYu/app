package com.cblue.camera.zxing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cblue.hardware.R;

import java.io.File;

public class CreateQRActivity extends AppCompatActivity {


    EditText et;
    Button btn;
    ImageView iv;
    String qr_content ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
        et = (EditText)findViewById(R.id.create_qr_activity_et);
        btn = (Button)findViewById(R.id.create_qr_activity_btn);
        iv = (ImageView)findViewById(R.id.create_qr_activity_iv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qr_content = et.getText().toString();
                //path=/storage/emulated/0/Android/data/com.cblue.hardware/files/qr_002323234.png
                 final String qr_url =getFileRoot(CreateQRActivity.this)+File.separator+"qr"+System.currentTimeMillis()+".png";
                final Bitmap logo = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean succees =  QRUtils.createQR(qr_content,800,800,qr_url,logo);
                        if(succees){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    iv.setImageBitmap(BitmapFactory.decodeFile(qr_url));
                                }
                            });
                        }
                    }
                }).start();


            }
        });
    }

    //得到项目的Sdcard根路径
    //path=/storage/emulated/0/Android/data/com.cblue.hardware/files
    public static String getFileRoot(Context context){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
           File file =   context.getExternalFilesDir(null);
            if(file!=null){

               return  file.getAbsolutePath();
            }
        }
        return null;
    }



}
