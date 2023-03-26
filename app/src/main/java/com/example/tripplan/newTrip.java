package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class newTrip extends AppCompatActivity {

    private EditText tripTitle;
    private EditText tripDescription;
    private EditText tripReminder;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_add_edit);
        tripTitle = findViewById(R.id.editTextNewTripTitle);
        tripDescription = findViewById(R.id.editTextNewTripDescription);
        tripReminder = findViewById(R.id.editTextNewDate);
        addButton = findViewById(R.id.actionButton);
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });


        String request = getIntent().getStringExtra("action");

        if (request.equals("edit")) {
            Toast.makeText(getApplicationContext(), "EDIT SCREEN", Toast.LENGTH_SHORT).show();
            TextView screen = findViewById(R.id.screenTitle);
            Button actionButton = findViewById(R.id.actionButton);
            screen.setText("Edit Trip");
            actionButton.setText("Edit");

            tripTitle.setText(getIntent().getStringExtra("tripTitle"));
            tripDescription.setText(getIntent().getStringExtra("tripDescription"));
            tripReminder.setText(getIntent().getStringExtra("tripReminderDate"));
            Log.w("Image uri from edit", getIntent().getStringExtra("image"));

            if (!getIntent().getStringExtra("image").equals("INVALID")) {
                Glide.with(this).load(Uri.parse(getIntent().getStringExtra("image"))).into(imageView);
            }
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tripTitle.getText().toString().trim().length() > 0) {
                    Intent intent = new Intent();

                    intent.putExtra("title", tripTitle.getText().toString());
                    intent.putExtra("description", tripDescription.getText().toString());
                    intent.putExtra("date", tripReminder.getText().toString());

                    if (imageUri == null) {
                        intent.putExtra("image", "INVALID");
                    } else {
                        intent.putExtra("image", imageUri.toString());
                    }


                    intent.putExtra("action", getIntent().getStringExtra("action"));
                    intent.putExtra("position", getIntent().getIntExtra("position",0));

                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "You did not enter a Title", Toast.LENGTH_SHORT).show();
                    Log.w("ACTION", getIntent().getStringExtra("action"));
                    Log.w("Position", String.valueOf(getIntent().getIntExtra("position", 0)));

                }
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(imageView);
        }
    }
}