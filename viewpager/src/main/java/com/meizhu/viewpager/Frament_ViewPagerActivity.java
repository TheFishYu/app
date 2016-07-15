package com.meizhu.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.meizhu.viewpager.fragments.Fragment01;
import com.meizhu.viewpager.fragments.Fragment02;
import com.meizhu.viewpager.fragments.Fragment03;

import java.util.ArrayList;
import java.util.List;

public class Frament_ViewPagerActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{


    private ViewPager vp;
    private TextView red_tv, green_tv,blue_tv;
    private Fragment fragment1,fragment2,fragment3;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frament__view_pager);

        vp = (ViewPager) findViewById(R.id.fragment_vp);

        red_tv = (TextView) findViewById(R.id.red_tv);
        green_tv = (TextView) findViewById(R.id.green_tv);
        blue_tv = (TextView) findViewById(R.id.blue_tv);
        red_tv.setOnClickListener(this);
        green_tv.setOnClickListener(this);
        blue_tv.setOnClickListener(this);

        fragment1 = new Fragment01();
        fragment2 = new Fragment02();
        fragment3 = new Fragment03();
        fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

        fragmentManager = getSupportFragmentManager();
        vp.setAdapter(new FragmentPagerAdapter(fragmentManager) {


            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        vp.addOnPageChangeListener(this);
        setTabColor(0);

    }
//实现重置textView字体颜色
    private  void resetTabColor(){
        red_tv.setTextColor(Color.parseColor("#000000"));
        green_tv.setTextColor(Color.parseColor("#000000"));
        blue_tv.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void onClick(View v) {
        resetTabColor();
        switch (v.getId()) {
            case R.id.red_tv:
                vp.setCurrentItem(0);
//                red_tv.setTextColor(Color.parseColor("#ff0000"));
                break;
            case R.id.green_tv:
                vp.setCurrentItem(1);
//                green_tv.setTextColor(Color.parseColor("#00ff00"));
                break;
            case R.id.blue_tv:
                vp.setCurrentItem(2);
//                blue_tv.setTextColor(Color.parseColor("#0000ff"));
                break;
        }
    }

    //设置标题的颜色
    private void setTabColor(int position){
        resetTabColor();
        switch (position){
            case 0:
                red_tv.setTextColor(Color.parseColor("#ff0000"));
                break;
            case 1:
                green_tv.setTextColor(Color.parseColor("#00ff00"));
                break;
            case 2:
                blue_tv.setTextColor(Color.parseColor("#0000ff"));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTabColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
