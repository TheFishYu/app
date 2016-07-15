package com.cblue.versionadapter;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cblue.screenadapter.R;

public class VersionAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_adapter);
        //当系统运行时的当前版本
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            ActionBar actionBar = getActionBar();
        }




    }
}
