package com.example.tripplan;

import android.net.Uri;

public class Trip {

    private String title;
    private String Description;
    private String ReminderDate;
    private Uri imageUri;

    public Trip(String title, String description, String reminderDate) {
        this.title = title;
        Description = description;
        ReminderDate = reminderDate;
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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
