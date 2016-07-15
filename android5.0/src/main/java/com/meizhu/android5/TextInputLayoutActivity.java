package com.meizhu.android5;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextInputLayoutActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputLayout textInputLayout;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        textInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        btn = (Button) findViewById(R.id.text_input_btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String inputText = textInputLayout.getEditText().getText().toString();
        //验证只能是数字字母的正则表达式
        String pattenText = "^[A-Za-z0-9]+$";
        //把正则表达式转化为Java对象
        Pattern pattern = Pattern.compile(pattenText);
        //让正则表达式与我们输入的内容进行比较
        Matcher matcher = pattern.matcher(inputText);
        //得到比较的结果
        boolean flag = matcher.matches();
        if (flag){
            Log.i("aaa","验证成功");
            //关闭错误提示功能
            textInputLayout.setErrorEnabled(false);
        }else {
            Log.i("aaa","验证失败");
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("输入错误");
        }
    }
}
