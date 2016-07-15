package com.google.myapp;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.NotificationCompat;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }




    private String urlStr= null;
    private Intent intent;
    private List<News> newsList;
    private NewsDao newsDao;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    private MyHandler handler;
    private int page=1;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getApplicationContext());
        handler = new MyHandler();
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("保存数据库");
        builder.setContentText("正在保存...");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        while(page<10) {
            urlStr = intent.getStringExtra("url") + page;
            if (urlStr != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newsDao = new NewsDao(getApplicationContext());
                        byte[] data = downFile(urlStr);
                        String str = new String(data);
                        newsList = parseJson(str);
                        for (News n : newsList) {
                            String[] fName = n.getLitpic().split("/");
                            String s = saveFile(n, fName[fName.length - 1]);
                            n.setLitpic(s);
                            newsDao.insert(n);
                        }

                    }
                }).start();
            }
            page++;
        }
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
        return super.onStartCommand(intent, flags, startId);
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1)
            {
                builder.setContentText("保存完成");
                manager.notify(100,builder.build());
            }
        }
    }

    private byte[] downFile(String str)
    {
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if(conn.getResponseCode()==200)
            {
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024*4];
                int len=0;
                while((len=is.read(b))!=-1)
                {
                    baos.write(b,0,len);
                }
                is.close();
                return baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<News> parseJson(String jsonString){

        List<News> list = new ArrayList<News>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONObject object1 = object.getJSONObject("data");
            News n;
            for(int i=0;i<10;i++){
                n = new News();
                JSONObject object2  = object1.getJSONObject(""+i);
                n.setId(object2.getString("id"));
                n.setTypeid(object2.getString("typeid"));
                n.setTypeid2(object2.getString("typeid2"));
                n.setSortrank(object2.getString("sortrank"));
                n.setFlag(object2.getString("flag"));
                n.setIsmake(object2.getString("ismake"));
                n.setChannel(object2.getString("channel"));
                n.setArcrank(object2.getString("arcrank"));
                n.setClick(object2.getString("click"));
                n.setMoney(object2.getString("money"));
                n.setTitle(object2.getString("title"));
                n.setShorttitle(object2.getString("shorttitle"));
                n.setColor(object2.getString("color"));
                n.setWriter(object2.getString("writer"));
                n.setSource(object2.getString("source"));
                n.setLitpic(object2.getString("litpic"));
                n.setPubdate(object2.getString("pubdate"));
                n.setSenddate(object2.getString("senddate"));
                n.setMid(object2.getString("mid"));
                n.setKeywords(object2.getString("keywords"));
                n.setLastpost(object2.getString("lastpost"));
                n.setScores(object2.getString("scores"));
                n.setGoodpost(object2.getString("goodpost"));
                n.setBadpost(object2.getString("badpost"));
                n.setVoteid(object2.getString("voteid"));
                n.setNotpost(object2.getString("notpost"));
                n.setDescription(object2.getString("description"));
                n.setFilename(object2.getString("filename"));
                n.setDutyadmin(object2.getString("dutyadmin"));
                n.setTackid(object2.getString("tackid"));
                n.setMtype(object2.getString("mtype"));
                n.setWeight(object2.getString("weight"));
                n.setFby_id(object2.getString("fby_id"));
                n.setGame_id(object2.getString("game_id"));
                n.setFeedback(object2.getString("feedback"));
                n.setTypedir(object2.getString("typedir"));
                n.setTypename(object2.getString("typename"));
                n.setCorank(object2.getString("corank"));
                n.setIsdefault(object2.getString("isdefault"));
                n.setDefaultname(object2.getString("defaultname"));
                n.setNamerule(object2.getString("namerule"));
                n.setNamerule2(object2.getString("namerule2"));
                n.setIspart(object2.getString("ispart"));
                n.setMoresite(object2.getString("moresite"));
                n.setSiteurl(object2.getString("siteurl"));
                n.setSitepath(object2.getString("sitepath"));
                n.setArcurl(object2.getString("arcurl"));
                n.setTypeurl(object2.getString("typeurl"));

                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private String saveFile(News news,String name)
    {
        String absoultePath = null;
        String path = news.getLitpic();
        String path2 = "http://www.3dmgame.com"+path;
        byte[] b = downFile(path2);
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
        {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(root,name);
            absoultePath = "/mnt/sdcard/download"+"/"+name;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(b,0,b.length);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return absoultePath;
    }



}
