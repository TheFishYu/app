package com.cblue.hardware;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

/**
 * 首界面  点击button，弹出的是PopWindow控件
 * 拍照
 * 从图库选取
 * 取消
 */


public class CameraActivity03 extends AppCompatActivity {

    Button btn;
    ImageView iv;
    View.OnClickListener listener;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera03);
        btn =(Button)findViewById(R.id.camera_activity03_btn);
        iv =(ImageView)findViewById(R.id.camare_activity03_iv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View Menmu = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_popupwindow,null);
                //打开Popwindow
                MenuPopupWindow menuPopupWindow = new MenuPopupWindow(CameraActivity03.this,Menmu,listener);
                //PopWindow显示的位置
                menuPopupWindow.showAtLocation(CameraActivity03.this.findViewById(R.id.camera_activity03_rl), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

            }
        });

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             switch (v.getId()){
                 case R.id.menu_popupwindow_camera:
                     Log.i("aaa","打开摄像头");
                     intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                     //剪裁
                     intent.putExtra("crop","true");//开始剪裁
                     intent.putExtra("aspectX",2); //设置剪裁的比例
                     intent.putExtra("aspectY",1);
                     intent.putExtra("outputX",200); //设置剪裁后的图片大小
                     intent.putExtra("outputY",100);
                     intent.putExtra("result-data",true);//返回数据
                     startActivityForResult(intent,100);
                     break;

                 case R.id.menu_popupwindow_picture:
                    Log.i("aaa","打开图库");
                      intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                     //剪裁
                     intent.putExtra("crop","true");//开始剪裁
                    // intent.putExtra("circlecrop","true");//结果是不行的
                     intent.putExtra("aspectX",2); //设置剪裁的比例
                     intent.putExtra("aspectY",1);
                     intent.putExtra("outputX",200); //设置剪裁后的图片大小
                     intent.putExtra("outputY",100);
                     intent.putExtra("result-data",true);//返回数据
                     startActivityForResult(intent,200);
                     break;

             }
            }
        };
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = null;
        if(requestCode==100&&resultCode==RESULT_OK){
            Bundle bundle =   data.getExtras();
            bitmap = (Bitmap) bundle.get("data");

        }else if(requestCode==200&&resultCode==RESULT_OK){
            Bundle bundle =  data.getExtras();
            bitmap = bundle.getParcelable("data");
        }
        if(bitmap!=null) {
            //保存到本地
            iv.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

class MenuPopupWindow extends PopupWindow{

    Button btn_open_camera,btn_open_pictrue,btn_cancle;
    /**
     *
     * @param context
     * @param menuView 就是弹出的布局的View对象
     * @param onClickListener 按钮点击的监听
     */
    public MenuPopupWindow(Context context, View menuView, View.OnClickListener onClickListener) {
        super(context);
        btn_open_camera = (Button)menuView.findViewById(R.id.menu_popupwindow_camera);
        btn_open_pictrue = (Button)menuView.findViewById(R.id.menu_popupwindow_picture);
        btn_cancle = (Button)menuView.findViewById(R.id.menu_popupwindow_cancle);

        btn_open_camera.setOnClickListener(onClickListener);
        btn_open_pictrue.setOnClickListener(onClickListener);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //让PopUpWindow消失
                dismiss();
            }
        });
        //设置显示内容的属性
        this.setContentView(menuView);
        //设置弹出的宽和高
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置可以获得焦点
        this.setFocusable(true);
        //添加背景颜色
        this.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffcc")));
        //设置弹出框外围不能被点击
        this.setOutsideTouchable(true);
    }
}
