package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.tripplan.adapter.TripItemAdapter;
import com.example.tripplan.objects.TripItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class viewTrip extends AppCompatActivity {

    TripItemAdapter tripItemAdapter;
    RecyclerView itemRecyclerView;
    List<TripItem> tripItemList;

    FloatingActionButton itemAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        setTitle(getIntent().getStringExtra("title"));

        tripItemList = new ArrayList<>();
        itemAddButton = findViewById(R.id.addItemButton);



        itemRecyclerView = findViewById(R.id.tripViewRecyclerView);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripItemAdapter = new TripItemAdapter(tripItemList); // ADD parameter for custom listener
        itemRecyclerView.setAdapter(tripItemAdapter);

        tripItemList.add(new TripItem("Tripitemtext","date","imageuri","color","datetime"));
        tripItemList.add(new TripItem("Tripitemtext2","date","imageuri","color","datetime"));

    itemAddButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext());
            bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
            bottomSheetDialog.show();
        }
    });


    }
}