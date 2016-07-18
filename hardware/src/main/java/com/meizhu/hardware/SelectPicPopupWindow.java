package com.meizhu.hardware;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by Kun Yu on 2016/7/18.
 */
public class SelectPicPopupWindow extends PopupWindow{
    private Button btn1,btn2,btn3;
    private View mMenuView;

    public SelectPicPopupWindow(Context context, View.OnClickListener itemsOnclick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.camera01_popupwindow_layout,null);
        btn1 = (Button) mMenuView.findViewById(R.id.popupWindow01);
        btn2 = (Button) mMenuView.findViewById(R.id.popupWindow02);
        btn3 = (Button) mMenuView.findViewById(R.id.popupWindow03);
        //取消按钮
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        btn1.setOnClickListener(itemsOnclick);
        btn2.setOnClickListener(itemsOnclick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.PopupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.argb(0, 0, 0, 0));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.camera_linearlayout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
