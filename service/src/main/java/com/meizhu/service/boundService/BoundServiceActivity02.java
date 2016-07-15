package com.meizhu.service.boundService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meizhu.service.R;

/**
 * 播放、停止音乐
 * 
 */

public class BoundServiceActivity02 extends AppCompatActivity implements View.OnClickListener{

    private Button btn_boundStart,btn_boundStop;
    private Intent intent;
    BoundService02.MyBinder myBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound02);
        btn_boundStart = (Button) findViewById(R.id.btn_boundStart);
        btn_boundStop = (Button) findViewById(R.id.btn_boundStop);
        btn_boundStart.setOnClickListener(this);
        btn_boundStop.setOnClickListener(this);
        //
        intent = new Intent(BoundServiceActivity02.this,BoundService02.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (BoundService02.MyBinder) service;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_boundStart:
                myBinder.start();
                break;
            case R.id.btn_boundStop:
                myBinder.stop();
                break;
        }
    }
}
