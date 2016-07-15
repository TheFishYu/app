package com.cblue.screenadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * 屏幕适配：图片适配，不同的屏幕的分辨率加载不同的图片
 */
public class ScreenAdapterActivity01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_adapter01);
    }
}
