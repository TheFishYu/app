package com.meizhu.hardware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.meizhu.sensor.SensorActivity01;
import com.meizhu.sensor.SensorActivity02;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.mian_btn1);
        btn2 = (Button) findViewById(R.id.mian_btn2);
        btn3 = (Button) findViewById(R.id.mian_btn3);
        btn4 = (Button) findViewById(R.id.mian_btn4);
        btn5 = (Button) findViewById(R.id.mian_btn5);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mian_btn1:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.mian_btn2:
                startActivity(new Intent(this, Camera01Activity.class));
                break;
            case R.id.mian_btn3:
                startActivity(new Intent(this, SensorActivity01.class));
                break;
            case R.id.mian_btn4:
                startActivity(new Intent(this, SensorActivity02.class));
                break;
        }
    }
}
