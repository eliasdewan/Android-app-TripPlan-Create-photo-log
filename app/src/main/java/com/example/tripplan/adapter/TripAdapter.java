package com.example.tripplan.adapter;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

import static com.example.tripplan.MainActivity.REQUEST_CODE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tripplan.MainActivity;
import com.example.tripplan.R;
import com.example.tripplan.newTrip;
import com.example.tripplan.objects.Trip;
import com.example.tripplan.TripItemClickListener;
import com.example.tripplan.viewTrip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private TripItemClickListener aListener;
    private List<Trip> tripList;

    public TripAdapter(List<Trip> tripList, TripItemClickListener listener) {
        this.tripList = tripList;
        aListener = listener;
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

        Uri imageUri;
        if (trip.getImageUri().equals("INVALID")) {
            imageUri = Uri.parse("android.resource://com.example.tripplan/" + R.mipmap.ic_launcher);
        } else {
            imageUri = Uri.parse(trip.getImageUri());
        }

        Glide.with(holder.imageView.getContext())
                .load(imageUri)
                .error(R.drawable.baseline_broken_image_24)
                .override(300)
                .centerCrop()
                .into(holder.imageView);
        holder.bind(position, aListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView reminderTextView;
        public ImageView imageView;
        public ImageButton editButton;
        public ImageButton deleteButton;

        private Context context;


        public ViewHolder(View tripView) {
            super(tripView);

            context = itemView.getContext();

            titleTextView = tripView.findViewById(R.id.title_text_view);
            descriptionTextView = tripView.findViewById(R.id.description_text_view);
            reminderTextView = tripView.findViewById(R.id.reminder_text_view);
            imageView = tripView.findViewById(R.id.tripImage);
            editButton = tripView.findViewById(R.id.editButton);
            deleteButton = tripView.findViewById(R.id.deleteButton);



            tripView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // To go to view trip
                    int position = getAdapterPosition();
                    Log.d("click", "position" + position);
                    if (position != RecyclerView.NO_POSITION) {
                        Intent newView = new Intent(context, viewTrip.class);
                        newView.putExtra("position",position);
                        newView.putExtra("title",titleTextView.getText().toString());
                        context.startActivity(newView);


                    }
                }
            });
        }

        public void bind(int position, TripItemClickListener listener) {
            Log.w("Class run", "Bind class running");
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("click", "click pre position EDIT");
                    int position = getAdapterPosition();
                    Log.d("click", "position" + position);
                    if (position != RecyclerView.NO_POSITION) {
                        Log.d("click", "after position");

                        // TO CALL EDIT IN MAIN AND IMPLEMENT
                        listener.onMyTripEdited(position);
                    }
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("click", "click pre position Delete");
                    int position = getAdapterPosition();
                    Log.d("click", "position" + position);
                    if (position != RecyclerView.NO_POSITION) {
                        Log.d("click", "after position");

                        // TO CALL DELETE IN MAIN AND IMPLEMENT

                        new AlertDialog.Builder(deleteButton.getContext())
                                .setMessage("Are you sure you want to delete this item?").setPositiveButton("Yes", (dialog, which) -> listener.onMyTripDeleted(position))
                                // User clicked "Yes" button, handle the confirmation                        }
                                .setNegativeButton("No", null).show();
                    }
                }
            });
        }
    }
}
