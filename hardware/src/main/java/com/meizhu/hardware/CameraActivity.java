package com.meizhu.hardware;

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

public class CameraActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        btn = (Button) findViewById(R.id.camera_btn);
        iv = (ImageView) findViewById(R.id.camera_iv);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //打开摄像头
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            //保存到本地
            savePicToSdCard(bitmap,Environment.getExternalStorageDirectory().getAbsolutePath(),"myphoto.png");
            iv.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void savePicToSdCard(Bitmap bitmap, String path, String fileName){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String filePath = path+File.separator+fileName;
            File file = new File(filePath);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                //压缩图片
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
