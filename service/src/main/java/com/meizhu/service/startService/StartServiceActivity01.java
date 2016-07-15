package com.meizhu.service.startService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meizhu.service.R;

public class StartServiceActivity01 extends AppCompatActivity {
    Intent intent;
    private Button btn_start;
    private Button btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        //添加监听器
        btn_start.setOnClickListener(listener);
        btn_stop.setOnClickListener(listener);
        intent = new Intent(StartServiceActivity01.this,StartService01.class);
        intent.putExtra("name","zhangsan");
    }

    //启动监听器
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    startService(intent);
                    break;
                case R.id.btn_stop:
                    stopService(intent);
                    break;
            }
        }
    };
}
