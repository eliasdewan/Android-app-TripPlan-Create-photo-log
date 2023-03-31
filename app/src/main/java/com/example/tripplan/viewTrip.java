package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tripplan.adapter.DateTimePicker;
import com.example.tripplan.adapter.TripItemAdapter;
import com.example.tripplan.objects.Trip;
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
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int SELECT_PICTURE = 1;

    FloatingActionButton itemAddButton;

    // Bottom part
    Button confirmAddButton;
    EditText tripItemText;
    Button tripDate;
    ImageView addImage;

    public String LoadedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        setTitle(getIntent().getStringExtra("title"));

        //tripItemList = new ArrayList<>();
        tripItemList = (ArrayList<TripItem>) getIntent().getSerializableExtra("TripItemList");


         //       (ArrayList<TripItem>) getIntent().getSerializableExtra("Trip");
        itemAddButton = findViewById(R.id.addItemButton);


        itemRecyclerView = findViewById(R.id.tripViewRecyclerView);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripItemAdapter = new TripItemAdapter(tripItemList); // ADD parameter for custom listener
        itemRecyclerView.setAdapter(tripItemAdapter);


      //  tripItemList.add(new TripItem("Tripitemtext", "date", "imageuri", "color", "datetime"));
      //  tripItemList.add(new TripItem("Tripitemtext2", "date", "imageuri", "color", "datetime"));

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
                    chooseAPicture();
                });
                confirmAddButton.setOnClickListener(view1 -> {
                    if (tripItemText.getText().toString().trim().length() == 0 && LoadedImage!=null) {
                    tripItemList.add ( new TripItem(tripItemText.getText().toString(),tripDate.getText().toString(),LoadedImage+"","None","DATE"));
                    tripItemAdapter.notifyItemInserted(tripItemList.size()-1);}
                    else {
                        Toast.makeText(getApplicationContext(), "Nothing to add", Toast.LENGTH_SHORT).show();}
                    bottomSheetDialog.dismiss();
                });
            }
        });
    }

    public void setDatetoView(TextView view) {
        DateTimePicker dateTimePicker = new DateTimePicker(this);
        dateTimePicker.pickDateTime(new DateTimePicker.DateTimePickListener() {
            @Override
            public void onDateTimePicked(Date date) {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE dd MMM yyyy HH:mm");
                view.setText(formatter.format(date));
            }
        });
    }

    public void chooseAPicture() {
        Intent pickIntent = new Intent();
        pickIntent.setType("image/*");
        pickIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
        Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
        chooserIntent.putExtra
                (
                        Intent.EXTRA_INITIAL_INTENTS,
                        new Intent[]{takePhotoIntent}
                );
        startActivityForResult(chooserIntent, SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // For when choosing a picture
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null && data.getExtras() != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //For saving the image
            String title = "MyImage";
            String description = "Image captured from camera";
            String savedImageURL = MediaStore.Images.Media.insertImage(getContentResolver(), imageBitmap, title, description);
            Glide.with(this).load(savedImageURL).into(addImage);
            LoadedImage = savedImageURL;
            Log.w("PICKDATACAM", imageBitmap.toString());
        } // For when taking a picture
        else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Glide.with(this).load(data.getData()).into(addImage);
            LoadedImage = data.getDataString();
            Log.w("PICKDATAGAL", data.getData().toString());
            Log.w("PICKDATAGAL", data.getDataString());
        }

    }
}