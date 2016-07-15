package com.cblue.actionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 继承AppCompatActivity就会出现ActionBar
 * 使用按钮的方式，让ActionBar出现或消失
 *
 */
public class ActionBarActivity01 extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar01);
        btn1 = (Button)findViewById(R.id.actionbar01_btn01);
        btn2 = (Button)findViewById(R.id.actionbar01_btn02);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        //获得V7下的ActionBar对象
        actionBar = getSupportActionBar();
        //获得Android3.0以上的ActionBar对象
       // actionBar = getActionBar();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actionbar01_btn01:
            //显示ActionBar
                actionBar.show();
                break;

            case R.id.actionbar01_btn02:
               //隐藏ActionBar
                actionBar.hide();
                break;
        }
    }
}
