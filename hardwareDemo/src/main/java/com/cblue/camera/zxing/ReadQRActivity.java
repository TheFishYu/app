package com.cblue.camera.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cblue.hardware.R;
import com.google.zxing.client.android.CaptureActivity;

public class ReadQRActivity extends AppCompatActivity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_qr);
        btn = (Button)findViewById(R.id.read_qr_activity_btn);
        tv =(TextView)findViewById(R.id.read_qr_activity_tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadQRActivity.this, CaptureActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==RESULT_OK){
           Bundle bundle =  data.getExtras();
           String resultStr =  bundle.getString("SCAN_RESULT");
            if(resultStr!=null){
                tv.setText(resultStr);
            }
        }

    }
}
