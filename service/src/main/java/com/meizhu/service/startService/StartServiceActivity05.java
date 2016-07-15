package com.meizhu.service.startService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meizhu.service.R;

/**
 * !!!!!!!!!!!!!空指针异常
 */

public class StartServiceActivity05 extends AppCompatActivity {

    private Button btn_downloadImg;
    private Intent intent;
    private String urlStr = "http://img3.utuku.china.com/600x0/game/20160615/070e8f3e-69df-4d37-8465-4f81002442ce.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service05);
        btn_downloadImg = (Button) findViewById(R.id.btn_downloadImg);
        btn_downloadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StartServiceActivity05.this,StartService05.class);
                intent.putExtra("urlStr",urlStr);
                startService(intent);
            }
        });
    }
}
