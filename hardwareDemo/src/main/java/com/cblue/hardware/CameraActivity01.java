package com.cblue.hardware;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

/***
 * 拍摄照片，并得到照片
 */
public class CameraActivity01 extends AppCompatActivity {


    Button btn;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera01);

        iv = (ImageView)findViewById(R.id.camera_activity01_iv);
        btn = (Button)findViewById(R.id.camera_activity01_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开摄像头
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100&&resultCode==RESULT_OK){
           Bundle bundle =   data.getExtras();
           Bitmap bitmap = (Bitmap) bundle.get("data");
            //保存到本地
            savePicToSdCard(bitmap,Environment.getExternalStorageDirectory().getAbsolutePath(),"myphoto.png");
            iv.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void savePicToSdCard(Bitmap bitmap,String path,String fileName) {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){

           File file = new File(path,fileName);
            System.out.println("myfile="+file.getAbsolutePath());
            FileOutputStream fileOutput = null;
            try {
                fileOutput = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutput);
                fileOutput.flush();
                fileOutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }
}
