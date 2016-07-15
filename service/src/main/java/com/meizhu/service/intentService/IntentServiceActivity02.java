package com.meizhu.service.intentService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meizhu.service.R;

public class IntentServiceActivity02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service02);

        Intent intent = new Intent(IntentServiceActivity02.this,IntentService02.class);
        intent.putExtra("urlStr","http://img3.utuku.china.com/600x0/game/20160615/070e8f3e-69df-4d37-8465-4f81002442ce.jpg");
        startService(intent);
    }
}
