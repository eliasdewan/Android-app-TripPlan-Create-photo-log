package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tripplan.adapter.TripItemAdapter;
import com.example.tripplan.objects.TripItem;

import java.util.ArrayList;
import java.util.List;


public class viewTrip extends AppCompatActivity {

    TripItemAdapter tripItemAdapter;
    RecyclerView itemRecyclerView;
    List<TripItem> tripItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        setTitle(getIntent().getStringExtra("title"));

        tripItemList = new ArrayList<>();
        itemRecyclerView = findViewById(R.id.tripViewRecyclerView);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripItemAdapter = new TripItemAdapter(tripItemList); // ADD parameter for custom listener
        itemRecyclerView.setAdapter(tripItemAdapter);

        tripItemList.add(new TripItem("Tripitemtext","date","imageuri","color","datetime"));
        tripItemList.add(new TripItem("Tripitemtext2","date","imageuri","color","datetime"));




    }
}