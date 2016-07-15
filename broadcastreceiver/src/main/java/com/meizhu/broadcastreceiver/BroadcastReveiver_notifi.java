package com.meizhu.broadcastreceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Kun Yu on 2016/6/23.
 */
public class BroadcastReveiver_notifi extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("广播来了");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("好消息");
        builder.setContentText("明天放假");
        manager.notify(100,builder.build());
    }
}
