package com.meizhu.service.startService;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StartService05 extends Service {

    public StartService05() {
    }

    private String urlStr = null;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //执行耗时操作
         urlStr = intent.getStringExtra("urlStr");
        if (urlStr!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //下载图片
                    byte[] data = downloadFile(urlStr);
                    Log.i("aaa","data.len="+data.length);
                    //保存到SD卡
                    boolean flag = saveFile(data,"spriderman.jpg");
                    Log.i("aaa","flag="+flag);
                    if (flag) {
                        Log.i("aaa","下载成功");
                    }

                }
            }).start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private byte[] downloadFile(String urlStr){
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //请求方式
            con.setRequestMethod("GET");
            //设置输入流
            con.setDoInput(true);

            con.connect();
            if (con.getResponseCode() == 200) {
                InputStream inputStream = con.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] data = new byte[100];
                int len = 0;
                while ((len = inputStream.read(data))!=-1){
                    baos.write(data,0,len);
                }
                Log.i("aaa","baos的长度"+baos.toByteArray().length);
                if (inputStream != null) {
                    inputStream.close();
                }
                byte[] result = baos.toByteArray();
                if (baos!=null){
                    baos.close();
                }
                return result;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //保存下载文件
    private boolean saveFile(byte[] data ,String fileName){
        boolean flag = false;
        //判断SD卡是否挂载成功
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            //得到SD卡的路径 /mnt/sdcard/download
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            Log.i("aaa","root文件"+root.getAbsolutePath().toString());
            //创建一个写入文件 /mnt/sdcard/download/filename
            File file = new File(root,fileName);
            //往文件中写数据
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(data,0,data.length);
                flag = true;
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return flag;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
