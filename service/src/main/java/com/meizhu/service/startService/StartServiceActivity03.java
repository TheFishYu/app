package com.meizhu.service.startService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meizhu.service.R;

public class StartServiceActivity03 extends AppCompatActivity implements View.OnClickListener{

    private Button btn_mstart,btn_mstop;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service03);
        btn_mstart = (Button) findViewById(R.id.btn_mstart);
        btn_mstop = (Button) findViewById(R.id.btn_mstop);
        btn_mstop.setOnClickListener(this);
        btn_mstart.setOnClickListener(this);
        intent = new Intent(StartServiceActivity03.this,StartService03.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mstart:
                startService(intent);
                break;
            case R.id.btn_mstop:
                stopService(intent);
                break;
        }
    }
}
