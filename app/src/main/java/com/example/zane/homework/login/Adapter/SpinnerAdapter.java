package com.example.zane.homework.login.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.zane.homework.R;

/**
 * Created by Axton on 2017/5/26.
 */

public class SpinnerAdapter extends BaseAdapter {

    private int[] drawableIds;

    public SpinnerAdapter(int[] drawableIds) {

        this.drawableIds = drawableIds;
    }

    @Override
    public int getCount() {
        return drawableIds.length;
    }

    @Override
    public Object getItem(int position) {
        return drawableIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        convertView = layoutInflater.inflate(R.layout.spinner_item,parent,false);
        if(convertView!=null) {
            ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
            imageView.setImageResource(drawableIds[position]);
        }
        return convertView;
    }
}