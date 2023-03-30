package com.example.tripplan.objects;

import android.net.Uri;
import android.util.Log;

public class Trip {

    private String title;
    private String Description;
    private String ReminderDate;
    private String imageUri;


    public Trip(String title, String description, String reminderDate) { // List withouth pictures
        this.title = title;
        Description = description;
        ReminderDate = reminderDate;
        imageUri = "INVALID";
    }

    public Trip(String title, String description, String reminderDate, String sImageUri) {
        this.title = title;
        Description = description;
        ReminderDate = reminderDate;
        setImageUri(sImageUri);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getReminderDate() {
        return ReminderDate;
    }

    public void setReminderDate(String reminderDate) {
        ReminderDate = reminderDate;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {

        try {
            Uri.parse(imageUri);
            this.imageUri = imageUri;
            // The URI string is valid, do something with the URI
        } catch (NullPointerException e) {
            // The URI string is not valid, handle the error
            Log.w("ImageURIError", "String uri not valid:"+imageUri);
            this.imageUri = "INVALID";
        }
    }
}
