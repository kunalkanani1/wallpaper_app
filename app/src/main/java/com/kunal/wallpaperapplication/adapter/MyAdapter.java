package com.kunal.wallpaperapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kunal.wallpaperapplication.R;

public class MyAdapter extends BaseAdapter {

    //hello

    int[] wallpaper_list;
    Context context;

    public MyAdapter(Context context, int[] wallpaper_list) {
        this.wallpaper_list = wallpaper_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return wallpaper_list.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.myfile, viewGroup, false);

        ImageView image;

        image = view.findViewById(R.id.image);
        image.setImageResource(wallpaper_list[i]);

        return view;
    }
}
