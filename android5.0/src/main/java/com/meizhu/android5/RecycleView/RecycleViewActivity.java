package com.meizhu.android5.RecycleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.meizhu.android5.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RecyclerViewItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        //设置控件类型
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //绘制分割线
        recyclerView.addItemDecoration(new RecyclerViewDivider());

        //添加数据
        initData();

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,data);
        recyclerView.setAdapter(recyclerViewAdapter);

        //添加item监听
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Log.i("aaa","position="+position);

            }
        });
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            data.add(new RecyclerViewItem(R.mipmap.ic_launcher,"msg"+i));
        }
    }
}
