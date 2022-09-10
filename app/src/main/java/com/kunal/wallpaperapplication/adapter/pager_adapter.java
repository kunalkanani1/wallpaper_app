package com.kunal.wallpaperapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.kunal.wallpaperapplication.R;

public class pager_adapter extends PagerAdapter {
    Context c;
    int[] list;

    public pager_adapter(Context c, int[] list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(c).inflate(R.layout.myfile2, container, false);

        ImageView image;
        image = view.findViewById(R.id.image);
        image.setImageResource(list[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
