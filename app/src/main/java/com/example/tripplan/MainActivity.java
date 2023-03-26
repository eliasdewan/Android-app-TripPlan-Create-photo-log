package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TripItemClickListener {

    Button begin;
    RecyclerView recyclerView;
    List<Trip> tripList;
    TripAdapter tripAdapter;
    private static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tripList = new ArrayList<>();

        tripList.add(new Trip("TripTitle 1", "Description 1", "12/03/2023"));
        tripList.add(new Trip("TripTitle 2", "Description 2", "13/03/2023"));

        begin = findViewById(R.id.begin);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripAdapter = new TripAdapter(tripList, this);
        recyclerView.setAdapter(tripAdapter);


        begin.setOnClickListener(new View.OnClickListener() { // suggested to convert to lambda
            @Override
            public void onClick(View v) {
                Intent tripsScreen = new Intent(MainActivity.this, TaskList.class);
                //startActivity(tripsScreen);
                startActivityForResult(tripsScreen, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            //tripList.add(new Trip("TripTitle 1", "Description 1", "12/03/2023"));
            tripList.add(new Trip(data.getStringExtra("title"), data.getStringExtra("description"), data.getStringExtra("date")));
            Log.w("Listproject", data.getStringExtra("title") + "< none");
            Log.w("Listproject", data.getStringExtra("description") + "< none");
            Log.w("Listproject", data.getStringExtra("date") + "< none");
            tripAdapter.notifyItemInserted(tripList.size() - 1);


        }
    }

    @Override
    public void onMyTripDeleted(int position) {
        tripList.remove(position);
        tripAdapter.notifyItemRemoved(position);
        Log.w("Removetrip", "Trip removed");
    }

    @Override
    public void onMyTripEdited(int position) {
        Log.w("Edit trip", "Trip to edit");
        // handle
    }
}