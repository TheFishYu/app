package com.meizhu.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button first_view,second_view,third_view;
    Intent intent1,intent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first_view = (Button) findViewById(R.id.first_view);
        second_view = (Button) findViewById(R.id.second_view);
        third_view = (Button) findViewById(R.id.third_view);
        first_view.setOnClickListener(this);
        second_view.setOnClickListener(this);
        third_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.first_view:
                intent1 = new Intent(this,Drawable_ViewPagerActivity.class);
                startActivity(intent1);
                break;
            case R.id.second_view:

                break;
            case R.id.third_view:
                intent2 = new Intent(this,Frament_ViewPagerActivity.class);
                startActivity(intent2);
                break;
        }

    }
}
