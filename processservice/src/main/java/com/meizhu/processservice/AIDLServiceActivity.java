package com.meizhu.processservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AIDLServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlservice);

        Intent intent = new Intent();
        intent.setAction("com.meizhu.processservice.processservice");
        startService(intent);
        Log.i("aaa","AIDL服务启动");

    }


}
