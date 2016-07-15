package com.meizhu.processservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {

    public MessengerService() {
    }
    //创建Messenger对象
    Messenger messenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //通过客户端传递过来的消息，创建服务端的消息对象
            Message message = Message.obtain(msg);
            Log.i("aaa","----");
            Log.i("aaa","从客户端接收信息"+message.arg1+":"+message.arg2);
            //把服务端的消息传递给客户端
            Log.i("aaa","----"+message.arg1);
            message.arg1 = message.arg1+message.arg2;
            try {
                message.replyTo.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }
    });
    @Override
    public IBinder onBind(Intent intent) {
        return  messenger.getBinder();
    }
}
