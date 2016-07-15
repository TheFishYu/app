package com.meizhu.service.boundService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.meizhu.service.R;

public class BoundService02 extends Service {
    private MyBinder myBinder = new MyBinder();
    private MediaPlayer mediaplayer;
    public BoundService02() {
    }

     class MyBinder extends Binder{

        public void start(){
            mediaplayer.start();
        }
        public  void stop(){
            mediaplayer.pause();
//            mediaplayer.stop();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.people);
        mediaplayer.setLooping(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
         return  myBinder;
    }
}
