package com.kunal.wallpaperapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.kunal.wallpaperapplication.R;
import com.kunal.wallpaperapplication.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    int[] wallpaper_list = {R.drawable.flowers, R.drawable.cars, R.drawable.buildings, R.drawable.seas};

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview1);
        MyAdapter myAdapter = new MyAdapter(this, wallpaper_list);
        gridView.setAdapter(myAdapter);

        gridView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("pos", i);
            startActivity(intent);
        });

    }
}