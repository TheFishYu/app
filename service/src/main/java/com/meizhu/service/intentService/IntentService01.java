package com.meizhu.service.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class IntentService01 extends IntentService {
    public IntentService01() {
        super("IntentService01");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10*1000);
                Log.i("aaa","name="+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
