package com.meizhu.service.startService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartService01 extends Service {
    public StartService01() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        Log.i("aaa","name="+name);
        //执行后台的耗时操作
        Log.i("aaa","耗时操作");
        return super.onStartCommand(intent, flags, startId);
    }
}
