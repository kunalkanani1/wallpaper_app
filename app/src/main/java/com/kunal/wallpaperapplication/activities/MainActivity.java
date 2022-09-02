package com.kunal.wallpaperapplication.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.kunal.wallpaperapplication.R;
import com.kunal.wallpaperapplication.adapter.MyAdapter;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    int[] wallpaper_list = {R.drawable.flowers, R.drawable.cars, R.drawable.buildings, R.drawable.seas};

    GridView gridView;
    String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    static File folder;
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
        ActivityCompat.requestPermissions(MainActivity.this, permissions,1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    folder=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/myfolder");
                    if(folder.exists())
                    {
                        System.out.println("folder exist");
                    }
                    else
                    {
                        System.out.println("folder not exist");
                        if(folder.mkdir())
                        {
                            System.out.println("folder created");
                        }
                        else
                        {
                            System.out.println("folder not created");
                        }
                    }
//                    File f=new File(Environment.getExternalStorageDirectory()+"/myfolder");

                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }


                return;
            }

        }
    }
}