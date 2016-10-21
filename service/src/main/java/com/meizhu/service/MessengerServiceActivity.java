package com.meizhu.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Activity 和 Service 互发 Message
 */

public class MessengerServiceActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button mBtnMessenger01;
    protected Button mBtnMessenger02;
    private Intent intent;
    private Messenger messenger;
    private Messenger mOutMessenger = new Messenger(new OutgoingHandler());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_messenger_service);
        initView();


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_messenger_01) {
            intent = new Intent(this,MessengerService.class);
            ServiceConnection conn = new MyServiceConnection();
            bindService(intent,conn, Service.BIND_AUTO_CREATE);
        } else if (view.getId() == R.id.btn_messenger_02) {
            if (messenger == null) {
                Toast.makeText(this, "服务不可用！", Toast.LENGTH_SHORT).show();
                return;
            }
            Message message = new Message();
            message.obj = "长江长江我是黄河";
            message.what = 0;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MessengerServiceActivity.this, "连接成功！", Toast.LENGTH_SHORT).show();
            messenger = new Messenger(service);
            Message message = new Message();
            message.what = 1;
            //Activity绑定Service的时候给Service发送一个消息，该消息的obj属性是一个Messenger对象；
            message.obj = mOutMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MessengerServiceActivity.this, "连接已经断开！", Toast.LENGTH_SHORT).show();
        }
    }

    class OutgoingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Log.d("tag", msg.toString());
        }
    }

    private void initView() {
        mBtnMessenger01 = (Button) findViewById(R.id.btn_messenger_01);
        mBtnMessenger01.setOnClickListener(MessengerServiceActivity.this);
        mBtnMessenger02 = (Button) findViewById(R.id.btn_messenger_02);
        mBtnMessenger02.setOnClickListener(MessengerServiceActivity.this);
    }
}
