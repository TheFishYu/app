package com.google.myapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


public class MyAdapter extends BaseAdapter{
    Context context;
    List<HashMap<String,Object>> data;

    public MyAdapter(Context context, List<HashMap<String,Object>> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView =  View.inflate(context, R.layout.item, null);

            holder = new ViewHolder();

            holder.textView  = (TextView) convertView.findViewById(R.id.item_tv);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_iv);

            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(data.get(position).get("title").toString());
        holder.imageView.setImageBitmap((Bitmap)data.get(position).get("pic"));
        return convertView;
    }


    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }




    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
