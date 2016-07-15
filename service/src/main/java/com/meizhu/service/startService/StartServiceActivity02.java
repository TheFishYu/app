package com.meizhu.service.startService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meizhu.service.R;

/**
 * service启动周期
 */
public class StartServiceActivity02 extends AppCompatActivity {

    private Button btn_zhouqi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service02);
        btn_zhouqi = (Button) findViewById(R.id.btn_zhouqi);
        btn_zhouqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartServiceActivity02.this,StartService02.class);
                startService(intent);
            }
        });
    }
}
