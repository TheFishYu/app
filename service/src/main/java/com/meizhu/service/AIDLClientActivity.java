package com.meizhu.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.meizhu.processservice.IMyAidlInterface;

public class AIDLClientActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_dial01,btn_dial02;
    private Intent intent;
    private IMyAidlInterface myAidlInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlclient);
        btn_dial01 = (Button)findViewById(R.id.btn_dial01);
        btn_dial02 = (Button)findViewById(R.id.btn_dial02);
        btn_dial02.setOnClickListener(this);
        btn_dial01.setOnClickListener(this);
        intent = new Intent();
        intent.setAction("com.meizhu.processservice.processservice");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dial01:
                //绑定服务
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_dial02:
                //调用aidl方法
                try {
                    int result = myAidlInterface.add(1,2);
                    Log.i("aaa","result=:"+result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
