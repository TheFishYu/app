package com.meizhu.pulltorefresh_lib;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Arrays;
import java.util.LinkedList;

public class PulltorefreshListActivity extends AppCompatActivity {
    private PullToRefreshListView ptrListview;
    private String[] data = {"zhengzhou","shagnhai","beijing","shengzhou","kuangzhou","hangzhou","shenzheng"};
    private LinkedList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh_list);

        list = new LinkedList<String>();

        ptrListview = (PullToRefreshListView)findViewById(R.id.PullToRefreshListView);
        list.addAll(Arrays.asList(data));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ptrListview.setAdapter(adapter);

        ptrListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(PulltorefreshListActivity.this,"下拉刷新",Toast.LENGTH_SHORT).show();
                new myAsycTask().execute("down");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(PulltorefreshListActivity.this,"上拉刷新",Toast.LENGTH_SHORT).show();
                new myAsycTask().execute("up");
            }
        });
    }

    class myAsycTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return params[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("down")){
                list.addFirst("郑州");
            }
            else {
                list.addLast("郑州");
            }

            adapter.notifyDataSetChanged();

            ptrListview.onRefreshComplete();
        }
    }

}
