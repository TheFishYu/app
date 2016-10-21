package com.meizhu.android5;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.meizhu.android5.RecycleView.RecyclerViewAdapter;
import com.meizhu.android5.RecycleView.RecyclerViewDivider;
import com.meizhu.android5.RecycleView.RecyclerViewItem;

import java.util.ArrayList;
import java.util.List;


public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton floatingActionButton;
    List<RecyclerViewItem> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coodinatorlayout);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"snackbar显示",Snackbar.LENGTH_SHORT);
                View view = snackbar.getView();
                view.setBackgroundColor(Color.BLUE);
                snackbar.setAction("action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplication(), "hah", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.setActionTextColor(Color.parseColor("#ff0000"));
                snackbar.show();
            }
        });

        initSwipeRefreshLayout();
        initRecyclerView();

    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //设置进度条背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        //设置进度条的滚动颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,android.R.color.holo_green_dark,android.R.color.holo_blue_light);
        //设置进度条的偏移量
        swipeRefreshLayout.setProgressViewOffset(false,0,20);
        //设置刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //请求数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<RecyclerViewItem> list = new ArrayList<RecyclerViewItem>();
                        for (int i = 0; i < 5; i++) {
                            list.add(new RecyclerViewItem(R.drawable.spriderman4,"new data"+i));
                        }
                        //将list数据加在data之前
                        list.addAll(data);
                        data.removeAll(data);

                        data.addAll(list);
                        recyclerViewAdapter.notifyDataSetChanged();
                        //停止刷新
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },5000);
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.swipe_recyclerview);
        //设置控件类型
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);*/

        //绘制分割线
        recyclerView.addItemDecoration(new RecyclerViewDivider());

        //添加数据
        initData();

        recyclerViewAdapter = new RecyclerViewAdapter(this,data);
        recyclerView.setAdapter(recyclerViewAdapter);

        //添加item监听
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Log.i("aaa","position="+position);
                Toast.makeText(SwipeRefreshLayoutActivity.this, "点击的位置是："+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            data.add(new RecyclerViewItem(R.drawable.spriderman,"msg"+i));
        }
    }
}
