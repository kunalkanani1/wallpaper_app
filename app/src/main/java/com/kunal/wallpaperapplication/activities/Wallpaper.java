package com.kunal.wallpaperapplication.activities;

import android.app.WallpaperManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kunal.wallpaperapplication.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Wallpaper extends AppCompatActivity {

    //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
    ImageView image3, share, next, previous, download, set;
    int pos = 0;
    int[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        image3 = findViewById(R.id.image3);
        share = findViewById(R.id.share);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        download = findViewById(R.id.download);
        set = findViewById(R.id.set);

        list = getIntent().getIntArrayExtra("list");
        pos = getIntent().getIntExtra("pos", 0);

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        image3.setImageResource(list[pos]);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    wallpaperManager.setResource(list[pos]);
                    Toast.makeText(Wallpaper.this, "Set Wallpaper", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri screenshotUri = Uri.parse("android.resource://com.android.test/*");
                try {
                    InputStream stream = getContentResolver().openInputStream(screenshotUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                sharingIntent.setType("image/jpeg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pos >= 1) {
                    pos--;
                    image3.setImageResource(list[pos]);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pos < list.length - 1) {
                    pos++;
                    image3.setImageResource(list[pos]);
                }
            }
        });
    }
}