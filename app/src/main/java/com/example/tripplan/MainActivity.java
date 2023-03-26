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

    Button create;
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

        create = findViewById(R.id.begin);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripAdapter = new TripAdapter(tripList, this);
        recyclerView.setAdapter(tripAdapter);


        create.setOnClickListener(new View.OnClickListener() { // CREATE NEW
            @Override
            public void onClick(View v) {
                Intent newTripScreen = new Intent(MainActivity.this, newTrip.class);
                newTripScreen.putExtra("action", "add");
                //startActivity(tripsScreen);
                startActivityForResult(newTripScreen, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String image = data.getStringExtra("image"); // Not neede
            Toast.makeText(this, image, Toast.LENGTH_SHORT).show();


            if (data.getStringExtra("action").equals("add")) {
                tripList.add(new Trip(data.getStringExtra("title"), data.getStringExtra("description"), data.getStringExtra("date")));
                tripAdapter.notifyItemInserted(tripList.size() - 1);
            } else if (data.getStringExtra("action").equals("edit")) {
                Toast.makeText(getApplicationContext(), "EDIT", Toast.LENGTH_SHORT).show();
                int tripPosition = data.getIntExtra("position", 0);
                Trip changingTrip = tripList.get(tripPosition);
                changingTrip.setTitle(data.getStringExtra("title"));
                changingTrip.setDescription(data.getStringExtra("description"));
                changingTrip.setReminderDate(data.getStringExtra("date"));
                tripAdapter.notifyItemChanged(tripPosition);
            }
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
        Intent editTripScreen = new Intent(MainActivity.this, newTrip.class);
        editTripScreen.putExtra("action", "edit");
        editTripScreen.putExtra("position", position);
        editTripScreen.putExtra("tripTitle", tripList.get(position).getTitle());
        editTripScreen.putExtra("tripDescription", tripList.get(position).getDescription());
        editTripScreen.putExtra("tripReminderDate", tripList.get(position).getReminderDate());
        // editTripScreen.putExtra("tripDescription",tripList.get(position).getDescription());
        startActivityForResult(editTripScreen, REQUEST_CODE);
    }
}