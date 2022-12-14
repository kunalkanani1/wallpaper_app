package com.kunal.wallpaperapplication.activities;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import com.kunal.wallpaperapplication.R;
import com.kunal.wallpaperapplication.adapter.pager_adapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Wallpaper extends AppCompatActivity {

    //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

    ImageView share, next, previous, download, set;
    ViewPager pager;
    int pos = 0;
    int[] list;
    String[] arr = {"Home", "Lock"};
    LinearLayout linearLayout;
    ActionBar actionBar;
    Palette.Swatch vibrantSwatch;
    int random = 0;
    int currentimg;
    ArrayList<Palette.Swatch> alist = new ArrayList();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        actionBar = getSupportActionBar();
        share = findViewById(R.id.share);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        download = findViewById(R.id.download);
        set = findViewById(R.id.set);
        linearLayout = findViewById(R.id.linear);

        list = getIntent().getIntArrayExtra("list");
        pos = getIntent().getIntExtra("pos", 0);
        currentimg = list[pos];

        pager = findViewById(R.id.pager);
        pager_adapter pa = new pager_adapter(this, list);
        pager.setAdapter(pa);

        pager.setCurrentItem(pos);

        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alert = builder.create();
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = alert.getWindow().getAttributes();

        wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        alert.setView(view);

        TextView home = view.findViewById(R.id.yes);
        TextView lock = view.findViewById(R.id.no);
        TextView both = view.findViewById(R.id.both);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), list[pos]);
        Palette p = createPaletteSync(icon);
        getcolor(p);

        random = new Random().nextInt(alist.size() - 0) + 0;
        vibrantSwatch = alist.get(random);

        if (vibrantSwatch != null) {
            linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
            random = new Random().nextInt(alist.size() - 0) + 0;
            vibrantSwatch = alist.get(random);
//            actionBar.setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));
        }

        home.setOnClickListener(view1 -> {
            try {
                myWallpaperManager.setResource(list[pos], WallpaperManager.FLAG_SYSTEM);
            } catch (IOException e) {
                e.printStackTrace();
            }
            alert.dismiss();
        });

        lock.setOnClickListener(view1 -> {
            try {
                myWallpaperManager.setResource(list[pos], WallpaperManager.FLAG_LOCK);
            } catch (IOException e) {
                e.printStackTrace();
            }
            alert.dismiss();
        });

        both.setOnClickListener(view1 -> {
            try {
                myWallpaperManager.setResource(list[pos]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            alert.dismiss();
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = BitmapFactory.decodeResource(getResources(), list[pos]);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                File f = new File(MainActivity.folder.getAbsolutePath() + "/" + currentDateandTime + ".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                alert.show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = BitmapFactory.decodeResource(getResources(), list[pos]);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                File f = new File(MainActivity.folder.getAbsolutePath() + "/" + currentDateandTime + ".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(f.getAbsolutePath()));
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pos >= 1) {
                    pos--;
                    pager.setCurrentItem(pos);
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), list[pos]);
                    Palette p = createPaletteSync(icon);
                    getcolor(p);
                    random = new Random().nextInt(alist.size() - 0) + 0;
                    vibrantSwatch = alist.get(random);

                    if (vibrantSwatch != null) {
                        linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
                        random = new Random().nextInt(alist.size() - 0) + 0;
                        vibrantSwatch = alist.get(random);
//                        actionBar.setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));
                    }
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pos < list.length - 1) {
                    pos++;
                    pager.setCurrentItem(pos);
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), list[pos]);
                    Palette p = createPaletteSync(icon);
                    getcolor(p);
                    random = new Random().nextInt(alist.size() - 0) + 0;
                    vibrantSwatch = alist.get(random);

                    if (vibrantSwatch != null) {
                        linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
                        random = new Random().nextInt(alist.size() - 0) + 0;
                        vibrantSwatch = alist.get(random);
//                        actionBar.setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));
                    }
                }
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentimg = list[position];
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save) {
            Bitmap icon = BitmapFactory.decodeResource(getResources(), currentimg);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());
            File f = new File(MainActivity.folder.getAbsolutePath() + "/" + currentDateandTime + ".jpg");
            try {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (item.getItemId() == R.id.share) {

        }

        if (item.getItemId() == R.id.wallpaper) {

        }

        return super.onOptionsItemSelected(item);
    }

    void getcolor(Palette p) {
        alist.clear();
        alist.add(p.getVibrantSwatch());
        alist.add(p.getLightVibrantSwatch());
        alist.add(p.getDarkVibrantSwatch());
        alist.add(p.getDarkMutedSwatch());
        alist.add(p.getMutedSwatch());
        alist.add(p.getLightMutedSwatch());
        alist.add(p.getDominantSwatch());
    }

    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }
}

/*
dialog 2
 builder.setTitle("Choice");
        builder.setItems(arr,(dialogInterface, i) -> {
            if(i==0)
            {
                try {
                    myWallpaperManager.setResource(list[pos],WallpaperManager.FLAG_SYSTEM);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(i==1)
            {
                try {
                    myWallpaperManager.setResource(list[pos],WallpaperManager.FLAG_LOCK);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
 */

/*
dialog 1
 builder.setMessage("Do u want to exit");
        builder.setTitle("Exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNeutralButton("Hello", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
 */