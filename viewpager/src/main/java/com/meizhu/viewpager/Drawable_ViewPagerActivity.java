package com.meizhu.viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
/**
 * 利用drawable资源文件实现viewPager
 */
import java.util.ArrayList;

public class Drawable_ViewPagerActivity extends AppCompatActivity {

    PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<View>();

    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        pager = (ViewPager) findViewById(R.id.viewpager);

        pager.setAdapter(new ViewpagerAdapter());

    }

    static class ViewpagerAdapter extends PagerAdapter{

        private  static  int[] sDrawables = {R.drawable.a149102,R.drawable.a149103,R.drawable.a149111};

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(sDrawables[position]);
            container.addView(imageView, ViewPager.LayoutParams.MATCH_PARENT,ViewPager.LayoutParams.MATCH_PARENT);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return sDrawables.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
