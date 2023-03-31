package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tripplan.adapter.TripAdapter;
import com.example.tripplan.objects.Trip;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TripItemClickListener {

    Button test;
    Button create;
    RecyclerView recyclerView;
   static List<Trip> tripList;
    TripAdapter tripAdapter;
    public static final int REQUEST_CODE = 1;

    // For sstoring data
    String jsonString = new Gson().toJson(tripList);
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tripList = new ArrayList<>();
        getList();

        create = findViewById(R.id.begin);
        test = findViewById(R.id.testButton);
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
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testButton = new Intent(MainActivity.this,mapBox.class);
                startActivity(testButton);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateList();

        // Code to run when the activity is destroyed
        // For example, save data to a database or file
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String image = data.getStringExtra("image"); // Not neede
            Toast.makeText(this, image, Toast.LENGTH_SHORT).show();

            // For when used add button
            if (data.getStringExtra("action").equals("add")) {
                tripList.add(new Trip(data.getStringExtra("title"),
                        data.getStringExtra("description"),
                        data.getStringExtra("date"),
                        data.getStringExtra("image")));
                Log.w("Image uri here", data.getStringExtra("image"));
                tripAdapter.notifyItemInserted(tripList.size() - 1);

                // When pressing edit icon button
            } else if (data.getStringExtra("action").equals("edit")) {
                Toast.makeText(getApplicationContext(), "EDIT", Toast.LENGTH_SHORT).show();
                int tripPosition = data.getIntExtra("position", 0);
                Log.w("Positionoflist", String.valueOf(data.getIntExtra("position", 0)));
                Trip changingTrip = tripList.get(tripPosition);
                changingTrip.setTitle(data.getStringExtra("title"));
                changingTrip.setDescription(data.getStringExtra("description"));
                changingTrip.setReminderDate(data.getStringExtra("date"));
                changingTrip.setImageUri(data.getStringExtra("image"));
                tripAdapter.notifyItemChanged(tripPosition);
            }
        }
        updateList();
    }

    @Override
    public void onMyTripDeleted(int position) {
        tripList.remove(position);
        tripAdapter.notifyItemRemoved(position);
        Log.w("Removetrip", "Trip removed");
        updateList();
    }

    @Override
    public void onMyTripEdited(int position) {
        Log.w("Edittrip", "Trip to edit"+position);
        Intent editTripScreen = new Intent(MainActivity.this, newTrip.class);
        editTripScreen.putExtra("action", "edit");
        editTripScreen.putExtra("position", position);
        editTripScreen.putExtra("tripTitle", tripList.get(position).getTitle());
        editTripScreen.putExtra("tripDescription", tripList.get(position).getDescription());
        editTripScreen.putExtra("tripReminderDate", tripList.get(position).getReminderDate());
        editTripScreen.putExtra("image", tripList.get(position).getImageUri());
        Log.w("endEdittrip", "Trip to edit"+position);

        startActivityForResult(editTripScreen, REQUEST_CODE);
        updateList();
    }

    public void getList(){
        sharedPreferences = getSharedPreferences("TripListSession", Context.MODE_PRIVATE);
        jsonString = sharedPreferences.getString("TripList", "");

       // List<Trip> listToAdd;
        Log.w("ListPrintGson",jsonString);
        if (!jsonString.isEmpty()) {
           // listToAdd = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Trip>>() {}.getType());
          //  tripList.addAll(listToAdd);
            tripList = new Gson().fromJson(jsonString, new TypeToken<ArrayList<Trip>>() {}.getType());
            Log.w("Listfound", " Adding things in the list");
        }
        else {
            // For new app testing ....
            tripList.add(new Trip("TripTitle 1", "Description 1", "12/03/2023"));
            tripList.add(new Trip("TripTitle 2", "Description 2", "13/03/2023"));
        }
        Log.w("ListNotfound", " Adding things in the list");
        //
    }
    public void updateList(){

        // Saving the list or updating the list
        // Creates jsonString
        String jsonString = new Gson().toJson(tripList);
        // Gets the shared preference
        sharedPreferences = getSharedPreferences("TripListSession", Context.MODE_PRIVATE);
        // sets shared preference edit to variable
        editor = sharedPreferences.edit();
        // Edits the shared preference to store
        editor.putString("TripList",jsonString);
        editor.apply();

        Log.w("UpdatedList", " The list was updated and evryting was saved");
        Log.w("ThingsAdding to string", jsonString);
        Log.w("ThingsExisting", sharedPreferences.getString("TripList", ""));


    }


}