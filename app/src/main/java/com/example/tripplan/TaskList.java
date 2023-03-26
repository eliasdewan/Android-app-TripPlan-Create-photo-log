package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TaskList extends AppCompatActivity {


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
        setContentView(R.layout.activity_task_list);
        tripTitle = findViewById(R.id.editTextNewTripTitle);
        tripDescription = findViewById(R.id.editTextNewTripDescription);
        tripReminder = findViewById(R.id.editTextNewDate);
        addButton = findViewById(R.id.addTask);
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openImagePicker();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tripTitle.getText().toString().trim().length() > 0) {
                    String result = "Image uri:" + imageUri;
                    Intent intent = new Intent();
                    intent.putExtra("result", result);
                    intent.putExtra("title", tripTitle.getText().toString());
                    intent.putExtra("description", tripDescription.getText().toString());
                    intent.putExtra("date", tripReminder.getText().toString());

                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "You did not enter a Title", Toast.LENGTH_SHORT).show();

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