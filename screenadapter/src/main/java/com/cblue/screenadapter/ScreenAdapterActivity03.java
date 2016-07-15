package com.cblue.screenadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 布局适配：使用不同的布局文件进行适配
 */
public class ScreenAdapterActivity03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_adapter03);
    }
}
