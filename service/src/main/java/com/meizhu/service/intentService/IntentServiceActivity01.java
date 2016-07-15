package com.meizhu.service.intentService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meizhu.service.R;

public class IntentServiceActivity01 extends AppCompatActivity {

    Button btn_intent;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service01);
        btn_intent = (Button) findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(IntentServiceActivity01.this,IntentService01.class);
                startService(intent);
            }
        });
    }
}
