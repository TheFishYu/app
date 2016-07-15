package com.cblue.screenadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
  代码适配
* */
public class ScreenAdapterActivity04 extends AppCompatActivity {


    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_adapter04);

        tv = (TextView)findViewById(R.id.screen_adapter_activity04_tv);

        //获得屏幕的宽度和高度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;



        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int)(screenWidth*0.5+0.5),(int)(screenHeight*0.5+0.5));
        tv.setLayoutParams(layoutParams);
    }
}
