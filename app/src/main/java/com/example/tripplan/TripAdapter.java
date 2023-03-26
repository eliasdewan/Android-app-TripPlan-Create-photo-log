package com.example.tripplan;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private TripItemClickListener aListener;
    private List<Trip> tripList;

    public TripAdapter(List<Trip> tripList ,TripItemClickListener listener) {
        this.tripList = tripList;
        aListener = listener ;
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
        holder.bind(position,aListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
       public TextView descriptionTextView;
        public TextView reminderTextView;
       public ImageButton editButton;
        public ImageButton deleteButton;


        public ViewHolder(View tripView) {
            super(tripView);

            titleTextView = tripView.findViewById(R.id.title_text_view);
            descriptionTextView = tripView.findViewById(R.id.description_text_view);
            reminderTextView = tripView.findViewById(R.id.reminder_text_view);
            editButton = tripView.findViewById(R.id.editButton);
            deleteButton = tripView.findViewById(R.id.deleteButton);

            tripView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("click","click pre position");
                    int position = getAdapterPosition();
                    Log.d("click","position" + position);
                    if (position != RecyclerView.NO_POSITION){
                        Log.d("click","after position");
                    }
                }
            });
        }

        public void bind(int position, TripItemClickListener listener){
            Log.w("Class run", "Bind class running");
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("click","click pre position EDIT");
                    int position = getAdapterPosition();
                    Log.d("click","position" + position);
                    if (position != RecyclerView.NO_POSITION){
                        Log.d("click","after position");

                        // TO CALL EDIT IN MAIN AND IMPLEMENT
                        listener.onMyTripEdited(position);
                    }
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("click","click pre position Delete");
                    int position = getAdapterPosition();
                    Log.d("click","position" + position);
                    if (position != RecyclerView.NO_POSITION){
                        Log.d("click","after position");

                        // TO CALL DELETE IN MAIN AND IMPLEMENT
                        listener.onMyTripDeleted(position);

                    }
                }
            });
        }
    }


}
