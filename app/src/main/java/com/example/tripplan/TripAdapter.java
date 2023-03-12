package com.example.tripplan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    private List<Trip> tripList;

    public TripAdapter(List<Trip> tripList) {
        this.tripList = tripList;
    }


    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trip trip = tripList.get(position);
        holder.titleTextView.setText(trip.getTitle());
        holder.descriptionTextView.setText(trip.getDescription());
        holder.reminderTextView.setText(trip.getReminderDate());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView descriptionTextView;
        TextView reminderTextView;

        public ViewHolder(View tripView) {
            super(tripView);

            titleTextView = tripView.findViewById(R.id.title_text_view);
            descriptionTextView = tripView.findViewById(R.id.description_text_view);
            reminderTextView = tripView.findViewById(R.id.reminder_text_view);
        }
    }
}
