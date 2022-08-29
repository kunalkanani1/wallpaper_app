package com.kunal.wallpaperapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.kunal.wallpaperapplication.R;
import com.kunal.wallpaperapplication.adapter.WallpaperAdapter;

public class MainActivity2 extends AppCompatActivity {

    int[] flower_arr = {R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4, R.drawable.f5};
    int[] car_arr = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5};
    int[] building_arr = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5};
    int[] sea_arr = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5};

    GridView gridView2;
    int pos;
    int[] temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pos = getIntent().getIntExtra("pos", 0);
        if (pos == 0) {
            temp = flower_arr;
        }
        if (pos == 1) {
            temp = car_arr;
        }
        if (pos == 2) {
            temp = building_arr;
        }
        if (pos == 3) {
            temp = sea_arr;
        }

        gridView2 = findViewById(R.id.gridview2);
        WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(this, temp);
        gridView2.setAdapter(wallpaperAdapter);

        gridView2.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity2.this, Wallpaper.class);
            intent.putExtra("list", temp);
            intent.putExtra("pos", i);
            startActivity(intent);
        });

    }
}