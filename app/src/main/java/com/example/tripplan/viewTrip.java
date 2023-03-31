package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tripplan.adapter.DateTimePicker;
import com.example.tripplan.adapter.TripItemAdapter;
import com.example.tripplan.objects.TripItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class viewTrip extends AppCompatActivity {

    TripItemAdapter tripItemAdapter;
    RecyclerView itemRecyclerView;
    List<TripItem> tripItemList;
    private static final int REQUEST_IMAGE_CAPTURE = 1;


    FloatingActionButton itemAddButton;

    // Bottom part
    Button confirmAddButton;
    EditText tripItemText;
    Button tripDate;
    ImageView addImage;


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


        tripItemList.add(new TripItem("Tripitemtext", "date", "imageuri", "color", "datetime"));
        tripItemList.add(new TripItem("Tripitemtext2", "date", "imageuri", "color", "datetime"));

        itemAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
                bottomSheetDialog.show();

                confirmAddButton = bottomSheetDialog.findViewById(R.id.confirmAdd);
                tripItemText = bottomSheetDialog.findViewById(R.id.tripItemText);
                tripDate = bottomSheetDialog.findViewById(R.id.tripDate);
                addImage = bottomSheetDialog.findViewById(R.id.addImage);

                tripDate.setOnClickListener(view1 -> {
                    setDatetoView(tripDate);

                });
                addImage.setOnClickListener(view1 -> {
                    dispatchTakePictureIntent();
                });
            }
        });
    }

    public void setDatetoView(TextView view){
        DateTimePicker dateTimePicker = new DateTimePicker(this);
        dateTimePicker.pickDateTime(new DateTimePicker.DateTimePickListener() {
            @Override
            public void onDateTimePicked(Date date) {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE dd MMM yyyy HH:mm");
                view.setText(formatter.format(date));
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
           // Glide.with(this).load(imageBitmap).into(imageView);
            Glide.with(this)
                    .load(imageBitmap)
                    .into(addImage);
        }
    }
}