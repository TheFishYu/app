package com.meizhu.android5.RecycleView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Kun Yu on 2016/7/14.
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration {
    Paint paint;

    /**
     * 当我们绘制每个Item之前执行
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //得到分割线x轴的起点和终点
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth()-parent.getPaddingRight();
        //确定数据的条数
        final int itemcount = parent.getChildCount();
        //得到画笔
        if (paint == null) {
            paint = new Paint();
            paint.setColor(Color.BLUE);
        }
        if (itemcount > 0) {
            for (int i = 0; i < itemcount; i++) {
                View itemView = parent.getChildAt(i);
                c.drawLine(left,itemView.getBottom(),right,itemView.getBottom(),paint);
            }
        }
    }

    /**
     * 当我们绘制每个Item之后执行
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

   //设置每一个Item的偏移量
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0,0,0,0);
    }
}
