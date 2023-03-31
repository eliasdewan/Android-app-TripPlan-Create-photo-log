package com.example.tripplan.objects;

import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Trip {

    private String title;
    private String Description;
    private String ReminderDate;
    private String imageUri;
    private List<TripItem> tripItemList;


    public Trip(String title, String description, String reminderDate) { // List withouth pictures
        this.title = title;
        Description = description;
        ReminderDate = reminderDate;
        imageUri = "INVALID";
        tripItemList = new ArrayList<>();

    }

    public Trip(String title, String description, String reminderDate, String sImageUri) {
        this.title = title;
        Description = description;
        ReminderDate = reminderDate;
        setImageUri(sImageUri);
        tripItemList = new ArrayList<>();
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


    public List<TripItem> getTripItemList() {
        return tripItemList;
    }

    public void setTripItemList(List<TripItem> tripItemList) {
        this.tripItemList = tripItemList;
    }

    public void add(TripItem tripItem){
        this.tripItemList.add(tripItem);
    }

    public void remove(TripItem tripItem){
        this.tripItemList.remove(tripItem);
    }

}
