package com.cblue.hardware;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity02 extends AppCompatActivity {

    Button btn;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera01);

        iv = (ImageView)findViewById(R.id.camera_activity01_iv);
        btn =(Button)findViewById(R.id.camera_activity01_btn);
        btn.setText("打开图库");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //剪裁
                intent.putExtra("crop","true");//开始剪裁
                intent.putExtra("aspectX",2); //设置剪裁的比例
                intent.putExtra("aspectY",1);
                intent.putExtra("outputX",200); //设置剪裁后的图片大小
                intent.putExtra("outputY",100);
                intent.putExtra("result-data",true);//返回数据
                startActivityForResult(intent,200);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200&&resultCode==RESULT_OK){
            Bundle bundle =  data.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            iv.setImageBitmap(bitmap);
        }

    }
}
