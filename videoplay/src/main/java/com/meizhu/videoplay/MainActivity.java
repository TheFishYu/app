package com.meizhu.videoplay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    ListView listView;
    String viewUrl = "http://vf1.mtime.cn/Video/2015/03/20/mp4/150320094140850937_480.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.jiecao_activity_listview);
        listView.setAdapter(new VideoAdapter());

        videoView.setMediaController(new MediaController(this));
        videoView.setFocusable(true);
        videoView.setVideoURI(Uri.parse(viewUrl));
    }

    class VideoAdapter extends BaseAdapter{

        private Context context;

        String[] video = {"","","","",""};

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
