package com.example.tripplan;

public class TripItem {
    private String tripItemText;
    private String tripDate;
    private String imageUri;
    private String color;
    private String scheduleTimeDate;
    // Map location
    // map locations* multiple


    public TripItem(String tripItemText, String tripDate, String imageUri, String color, String scheduleTimeDate) {
        this.tripItemText = tripItemText;
        this.tripDate = tripDate;
        this.imageUri = imageUri;
        this.color = color;
        this.scheduleTimeDate = scheduleTimeDate;
    }

    public String getTripItemText() {
        return tripItemText;
    }

    public void setTripItemText(String tripItemText) {
        this.tripItemText = tripItemText;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getScheduleTimeDate() {
        return scheduleTimeDate;
    }

    public void setScheduleTimeDate(String scheduleTimeDate) {
        this.scheduleTimeDate = scheduleTimeDate;
    }
}
