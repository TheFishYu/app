package com.meizhu.service.boundService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.meizhu.service.R;

public class BoundServiceActivity01 extends AppCompatActivity implements View.OnClickListener{

    private Button btn_boundStart_01,btn_boundStop_01,btn_boundIntent_01;
    private Intent intent;
    BoundService01.MyBinder myBinder;
    boolean isConnect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service01);
        btn_boundIntent_01 = (Button) findViewById(R.id.btn_boundIntent_01);
        btn_boundStart_01 = (Button) findViewById(R.id.btn_boundStart_01);
        btn_boundStop_01 = (Button) findViewById(R.id.btn_boundStop_01);
        btn_boundIntent_01.setOnClickListener(this);
        btn_boundStart_01.setOnClickListener(this);
        btn_boundStop_01.setOnClickListener(this);
        intent = new Intent(BoundServiceActivity01.this,BoundService01.class);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_boundStart_01:
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
                isConnect = true;
                break;
            case R.id.btn_boundStop_01:
                if(isConnect){
                    unbindService(connection);
                    isConnect = false;
                }
                break;
            case R.id.btn_boundIntent_01:
                //传值
                Parcel data = Parcel.obtain();
                data.writeString("name");
                Parcel reply = Parcel.obtain();
                try {
                    myBinder.transact(100,data,reply,1);
                    String value = reply.readString();
                    Log.i("aaa","得到Service的数据："+value);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        //当我们绑定服务的时候，Activity和Service之间产生一个连接
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("aaa","onServiceConnected");
            myBinder = (BoundService01.MyBinder) service;
            myBinder.dosomeThing();
        }
        //绑定Service异常销毁时（内存不足时），才执行这个方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
        Log.i("aaa","onservicedisconnected");
        }
    };
}
