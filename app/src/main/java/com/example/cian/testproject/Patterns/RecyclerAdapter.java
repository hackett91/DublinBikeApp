package com.example.cian.testproject.Patterns;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cian.testproject.Model.FavouriteStation;
import com.example.cian.testproject.R;
import com.example.cian.testproject.Service.FavouriteStationsService;

import java.util.List;
// https://www.youtube.com/watch?v=T_QfRU-A3s4
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    List<FavouriteStation> favouriteStations;
    FavouriteStationsService favouriteStationsService;

    public RecyclerAdapter(Context context, List<FavouriteStation> favouriteStations) {
        this.context = context;
        this.favouriteStations = favouriteStations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view;
        view = LayoutInflater.from(this.context).inflate(R.layout.fav_station_item, viewGroup, false );
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.item_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteStationsService = new FavouriteStationsService(context);
                int deleteFav = favouriteStations.get(viewHolder.getAdapterPosition()).getId();
                Toast.makeText(context, favouriteStations.get(viewHolder.getAdapterPosition()).getAddress()+" removed", Toast.LENGTH_SHORT).show();
                favouriteStations.remove(viewHolder.getAdapterPosition());
                favouriteStationsService.removeFavouriteStation(deleteFav);
                notifyDataSetChanged();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int position) {

        myViewHolder.favNameView.setText(favouriteStations.get(position).getName());
        myViewHolder.favAddressView.setText(favouriteStations.get(position).getAddress());
        myViewHolder.favAvailableSlotsView.setText(favouriteStations.get(position).getAvailable_bike_stands());
        myViewHolder.favAvailableBikesView.setText(favouriteStations.get(position).getAvailable_bikes());
    }

    @Override
    public int getItemCount() {
        return favouriteStations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageButton item_fav;
        private TextView favNameView;
        private TextView favAddressView;
        private TextView favAvailableSlotsView;
        private TextView favAvailableBikesView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_fav = (ImageButton)  itemView.findViewById(R.id.favicon);
            favNameView = (TextView) itemView.findViewById(R.id.fav_name);
            favAddressView = (TextView) itemView.findViewById(R.id.fav_address);
            favAvailableSlotsView = (TextView) itemView.findViewById(R.id.fav_bikes_available_stands);
            favAvailableBikesView = (TextView) itemView.findViewById(R.id.fav_bikes_available);
        }
    }

}
