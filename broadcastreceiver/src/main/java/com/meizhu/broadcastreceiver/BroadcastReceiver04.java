package com.meizhu.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiver04 extends BroadcastReceiver {
    public BroadcastReceiver04() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("aaa","BroadcastReceiver04广播");
    }
}
