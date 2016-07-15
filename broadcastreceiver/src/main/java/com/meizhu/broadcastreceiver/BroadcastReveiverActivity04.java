package com.meizhu.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BroadcastReveiverActivity04 extends AppCompatActivity implements View.OnClickListener{

    private Button btn01,btn02,btn03;
    BroadcastReceiver04 broadcastReveiver04;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_reveiver04);
        btn01 = (Button) findViewById(R.id.btn_broad04_01);
        btn02 = (Button) findViewById(R.id.btn_broad04_02);
        btn03 = (Button) findViewById(R.id.btn_broad04_03);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        broadcastReveiver04 = new BroadcastReceiver04();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_broad04_01:
                //注册
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("code");
                registerReceiver(broadcastReveiver04,intentFilter);
                 break;
            case R.id.btn_broad04_02:
                //注销
                unregisterReceiver(broadcastReveiver04);
                break;
            case R.id.btn_broad04_03:
                //发送
                Intent intent = new Intent();
                intent.setAction("code");
                sendBroadcast(intent);
                break;
        }
    }
}
