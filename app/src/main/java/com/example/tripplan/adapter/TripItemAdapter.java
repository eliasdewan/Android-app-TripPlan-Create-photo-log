package com.example.tripplan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplan.R;
import com.example.tripplan.objects.TripItem;

import java.util.List;

public class TripItemAdapter extends RecyclerView.Adapter<TripItemAdapter.ViewHolder>{


    private List<TripItem> tripItemList;

    // HAVE CUSTOM LISTENER HERE WHEN NEEDED FOR RECYCLER VIEW ITEM BUTTONS

    public TripItemAdapter(List<TripItem> tripItemList) {
        this.tripItemList = tripItemList;
        // SET LISTENER HERE
    }

    @Override
    public TripItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {return tripItemList.size();}


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TripItem tripItem = tripItemList.get(position);
        holder.tripItemText.setText(tripItem.getTripItemText());

        // SET THE HOLDERS FOR RECYCLER VIEW

        //USE CONDITIONALS FOR CUSTOMISED VIEW
        // Use holder.bind(position,lister) for binding the listener
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tripItemText;
        // HAVE THE ATTRIBUTES FOR VIEW HERE
        public ViewHolder(View tripItemView) {
            super(tripItemView);


            tripItemText = tripItemView.findViewById(R.id.itemAddButton);
            // Set all the layout items herer

            // Need a bind class for onclick actions with custom interface

        }

    }
}
