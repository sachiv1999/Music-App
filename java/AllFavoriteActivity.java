package com.example.mediaplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AllFavoriteActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<SingerModel> singerList;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_favorite);
        ListView listView = findViewById(R.id.listViewAll);




        singerList = new ArrayList<>();
        singerList.add(new SingerModel("Arijit Singh", R.drawable.arijit));
        singerList.add(new SingerModel("Kumar Sanu", R.drawable.sonu));
        singerList.add(new SingerModel("Aditya Gadhvi", R.drawable.aditya));
        singerList.add(new SingerModel("Gita Rabari", R.drawable.gita));
        singerList.add(new SingerModel("Kirtidan Gadhvi", R.drawable.kirtidan));
        singerList.add(new SingerModel("Neha Thakar", R.drawable.neha));
        singerList.add(new SingerModel("Kinjal Dave", R.drawable.kinjal));
        singerList.add(new SingerModel("Falguni Pathak", R.drawable.falguni));
        singerList.add(new SingerModel("Tulsi Kumar", R.drawable.tulsi));
        singerList.add(new SingerModel("Jignesh Kaviraj", R.drawable.jignesh));
        singerList.add(new SingerModel("Sairam Dave", R.drawable.sairam));



        adapter = new SingerAdapter(this, singerList);
        listView.setAdapter(adapter);

        // Handle item clicks
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            SingerModel singer = singerList.get(position);
            singer.setSelected(!singer.isSelected()); // toggle selection
            adapter.notifyDataSetChanged();
        });


    }




}