package com.google.myapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    List<News> data;
    List<HashMap<String,Object>> list;
    MyAdapter adaper;
    NewsDao newsDao ;
    int pageSize = 13;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        newsDao= new NewsDao(getApplicationContext());
        lv = (ListView) findViewById(R.id.lv);
        data = newsDao.getNewsList(pageSize, page);
        list = new ArrayList<HashMap<String, Object>>();
        for (int i=0;i<data.size();i++)
        {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("title",data.get(i).getTitle());
            Bitmap bitmap = BitmapFactory.decodeFile(data.get(i).getLitpic());
            map.put("pic", bitmap);
            list.add(map);
        }


         adaper= new MyAdapter(getApplicationContext(),list);
        lv.setAdapter(adaper);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean isBottom=false;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                    if(scrollState==0&&isBottom)
                    {
                        page++;
                        update();
                    }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if(firstVisibleItem+visibleItemCount==totalItemCount){
                        isBottom = true;}
                    else {
                        isBottom = false;
                    }
            }
        });



    }


   public void update()
   {

        list.clear();
        List<News> data1 = newsDao.getNewsList(pageSize,page);

       for (int i=0;i<data1.size();i++)
       {
           HashMap<String,Object> map = new HashMap<String,Object>();
           map.put("title",data1.get(i).getTitle());
           Bitmap bitmap = BitmapFactory.decodeFile(data1.get(i).getLitpic());
           map.put("pic", bitmap);
           list.add(map);
       }
       if(data.size()==0&&page!=1){
            page=1;
            update();
       }
       data.addAll(data1);
       adaper.notifyDataSetChanged();
   }


}
