package com.kunal.wallpaperapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kunal.wallpaperapplication.R;

public class WallpaperAdapter extends BaseAdapter {

    Context context;
    int[] temp;

    public WallpaperAdapter(Context context, int[] temp) {
        this.temp = temp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return temp.length;
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

        view = LayoutInflater.from(context).inflate(R.layout.wallpaperlist, viewGroup, false);

        ImageView image2;

        image2 = view.findViewById(R.id.image2);
        image2.setImageResource(temp[i]);

        return view;
    }
}