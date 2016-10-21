package com.meizhu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * 该Service接受到Activity的消息后会再返回一条消息
 */

public class MessengerService extends Service {
    private Messenger messenger = new Messenger(new IncomingHandler());
    private Messenger mActivityMessenger;
    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        IBinder binder = messenger.getBinder();
        Log.d("tag","连接建立成功，在这里返回IBinder对象");
        return binder;
    }

    //1.定义一个Handler对象，该Handler处理Activity发送过来的消息
    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    Log.d("tag",msg.toString());
                    if (mActivityMessenger != null) {
                        Message message = new Message();
                        message.what = 2;
                        message.obj = "地瓜地瓜我是土豆";
                        try {
                            mActivityMessenger.send(message);
                        } catch (RemoteException  e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    mActivityMessenger = (Messenger) msg.obj;
                    Log.d("tag","已经获取到Activity发送了的Messenger对象");
                    break;
                default:
                    break;
            }
        }
    }
}
