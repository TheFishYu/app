package com.meizhu.service.boundService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class BoundService01 extends Service {

    private MyBinder myBinder = new MyBinder();
    public BoundService01() {
    }

    class MyBinder extends Binder {

        public void dosomeThing(){
            Log.i("aaa","dosomeThing");
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 100) {
                String name = data.readString();
                Log.i("aaa","得到Activity的数据："+name);
                reply.writeString("zhangsan");

            }
            return super.onTransact(code, data, reply, flags);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("aaa","oncreate");
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i("aaa","onbind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("aaa","onunbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("aaa","ondestory");
    }
}
