package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button begin;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Trip> tripList = new ArrayList<>();
        tripList.add(new Trip("TripTitle 1", "Description 1", "12/03/2023"));
        tripList.add(new Trip("TripTitle 2", "Description 2", "13/03/2023"));
        tripList.add(new Trip("TripTitle 3", "Description 3", "14/03/2023"));
        tripList.add(new Trip("TripTitle 1", "Description 1", "12/03/2023"));
        tripList.add(new Trip("TripTitle 2", "Description 2", "13/03/2023"));
        tripList.add(new Trip("TripTitle 3", "Description 3", "14/03/2023"));

        begin = findViewById(R.id.begin);
        recyclerView =  findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TripAdapter tripAdapter  = new TripAdapter (tripList);
        recyclerView.setAdapter(tripAdapter);

        begin.setOnClickListener(new View.OnClickListener() { // suggested to convert to lambda
            @Override
            public void onClick(View v) {
                Intent tripsScreen = new Intent(MainActivity.this,TaskList.class);
                startActivity(tripsScreen);
            }
        });
    }
}