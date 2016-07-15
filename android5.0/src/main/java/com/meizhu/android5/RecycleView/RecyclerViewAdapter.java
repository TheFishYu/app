package com.meizhu.android5.RecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meizhu.android5.R;

import java.util.List;

/**
 * 首先创建ViewHolder
 * Created by Kun Yu on 2016/7/14.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements View.OnClickListener{

    Context context;
    List<RecyclerViewItem> list;

    public RecyclerViewAdapter(Context context, List<RecyclerViewItem> list) {
        this.context = context;
        this.list = list;
    }


    //定义一个接口回调
    public interface OnRecyclerViewItemClickListener{
        void onItemClickListener(View v,int position);
    }

    private OnRecyclerViewItemClickListener listener;

    public void setItemClickListener(OnRecyclerViewItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClickListener(v,(int)v.getTag());
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycle_view_item, null);
        view.setOnClickListener(this);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        RecyclerViewItem recyclerViewItem = list.get(position);
        holder.iv.setImageResource(recyclerViewItem.getResId());
        holder.tv.setText(recyclerViewItem.getMsg());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
