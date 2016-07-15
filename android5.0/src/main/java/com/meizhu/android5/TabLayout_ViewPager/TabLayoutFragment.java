package com.meizhu.android5.TabLayout_ViewPager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meizhu.android5.R;

/**
 * Created by Kun Yu on 2016/7/15.
 */
public class TabLayoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tabl_layout_fragment,container,false);
        Bundle bundle = getArguments();
        String value = bundle.getString("CONTENT");
        TextView textView = (TextView) view.findViewById(R.id.tab_layout_fragment_tv);
        textView.setText(value);
        return view;
    }
}
