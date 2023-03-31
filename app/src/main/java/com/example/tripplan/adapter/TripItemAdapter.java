package com.example.tripplan.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tripplan.R;
import com.example.tripplan.TripItemClickListener;
import com.example.tripplan.TripItemOptionListener;
import com.example.tripplan.objects.TripItem;

import java.util.List;

public class TripItemAdapter extends RecyclerView.Adapter<TripItemAdapter.ViewHolder> {


    private List<TripItem> tripItemList;
    private TripItemOptionListener itemListener;

    // HAVE CUSTOM LISTENER HERE WHEN NEEDED FOR RECYCLER VIEW ITEM BUTTONS

    public TripItemAdapter(List<TripItem> tripItemList, TripItemOptionListener listener) {
        this.tripItemList = tripItemList;
        this.itemListener = listener;

        // SET LISTENER HERE
    }

    @Override
    public TripItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return tripItemList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TripItem tripItem = tripItemList.get(position);
        holder.tripItemTextView.setText(tripItem.getTripItemText());
        holder.tripItemDate.setText(tripItem.getTripDate());
        Glide.with(holder.tripItemImage.getContext())
                .load(tripItem.getImageUri())
                .override(1000)
                .centerCrop()
                .into(holder.tripItemImage);

        // SET THE HOLDERS FOR RECYCLER VIEW

        //USE CONDITIONALS FOR CUSTOMISED VIEW
        // Use holder.bind(position,lister) for binding the listener
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tripItemTextView;
        public TextView tripItemDate;
        public ImageView tripItemImage;

        // HAVE THE ATTRIBUTES FOR VIEW HERE
        public ViewHolder(View tripItemView) {
            super(tripItemView);

            tripItemTextView = tripItemView.findViewById(R.id.tripItemTextView);
            tripItemDate = tripItemView.findViewById(R.id.tripItemDate);
            tripItemImage = tripItemView.findViewById(R.id.tripItemImage);

            tripItemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    new AlertDialog.Builder(itemView.getContext())
                            .setMessage("Delete item?").setPositiveButton("Yes", (dialog, which) -> itemListener.openOption(getAdapterPosition()))
                            // User clicked "Yes" button, handle the confirmation                        }
                            .setNegativeButton("No", null).show();
                    return false;
                }
            });

        }
    }

}
