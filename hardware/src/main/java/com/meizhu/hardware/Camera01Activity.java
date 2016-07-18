package com.meizhu.hardware;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class Camera01Activity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    ImageView iv;
    SelectPicPopupWindow menuWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera01);
        btn = (Button) findViewById(R.id.camera01_btn);
        iv = (ImageView) findViewById(R.id.camera01_iv);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //实例化SelectPicPopupWindow
        menuWindow = new SelectPicPopupWindow(Camera01Activity.this, itemsOnClick);
        //显示窗口
        menuWindow.showAtLocation(Camera01Activity.this.findViewById(R.id.camera01_main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.popupWindow01:
                    //打开摄像头
                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 200);
                    break;
                case R.id.popupWindow02:
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //剪裁
                    intent.putExtra("crop", "true");//开始剪裁
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("aspectX", 2);
                    intent.putExtra("outputX", 200);
                    intent.putExtra("outputY", 200);
                    intent.putExtra("result-data", true);
                    startActivityForResult(intent, 100);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            iv.setImageBitmap(bitmap);
        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            //保存到本地
            savePicToSdCard(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), "myphoto.png");
            iv.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    private void savePicToSdCard(Bitmap bitmap, String path, String fileName) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String filePath = path + File.separator + fileName;
            File file = new File(filePath);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                //压缩图片
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
