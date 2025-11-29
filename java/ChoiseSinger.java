package com.example.mediaplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChoiseSinger extends AppCompatActivity {

    List<SingerModel> singerList;
    SingerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_signer);

        ListView listView = findViewById(R.id.listViewSingers);
        Button btnSubmit = findViewById(R.id.btnSubmit);




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

        btnSubmit.setOnClickListener(v -> {
            ArrayList<String> selected = new ArrayList<>();
            for (SingerModel s : singerList) {
                if (s.isSelected()) selected.add(s.getName());
            }

            if (selected.size() >= 5) {
               // Toast.makeText(this, "Selected: " + selected, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("selected", selected);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "Please select exactly 5 singers!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}