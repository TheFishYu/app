package com.meizhu.broadcastreceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Kun Yu on 2016/6/23.
 */
public class BroadcastReceiver01 extends android.content.BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("aaa","广播接受到了");
    }
}
