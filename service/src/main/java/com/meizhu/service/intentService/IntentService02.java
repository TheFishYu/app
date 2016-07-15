package com.meizhu.service.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.meizhu.service.FileUtils;
import com.meizhu.service.HttpUtils;

public class IntentService02 extends IntentService {
    MyHandler myHandler = null;

    public IntentService02() {
        super("IntentService02");
        myHandler = new MyHandler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
       byte[] b =  HttpUtils.request(intent.getStringExtra("urlStr"));
        boolean flag = FileUtils.saveFile(b, "spriderman.png");
        Log.i("aaa","flag= "+flag);
        if (flag) {
            myHandler.sendEmptyMessage(1);
        }
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                Toast.makeText(getApplicationContext(),"下载成功",Toast.LENGTH_LONG).show();
            }
        }
    }
}
