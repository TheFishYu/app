package com.meizhu.android5.RecycleView;

/**
 * Created by Kun Yu on 2016/7/14.
 */
public class RecyclerViewItem {
    private int resId;
    private String msg;

    public RecyclerViewItem(int resId, String msg) {
        this.resId = resId;
        this.msg = msg;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
