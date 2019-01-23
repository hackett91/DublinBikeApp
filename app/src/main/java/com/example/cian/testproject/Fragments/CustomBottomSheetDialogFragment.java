package com.example.cian.testproject.Fragments;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cian.testproject.Service.FavouriteStationsService;
import com.example.cian.testproject.R;


public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment{

    TextView nameView;
    TextView availableSlotsView;
    TextView availableBikesView;
    FavouriteStationsService favouriteStationsService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottomsheetdialog, container, false);

        final ImageButton addToFavourite = (ImageButton)v.findViewById(R.id.addtoFavString);

        nameView =(TextView) v.findViewById(R.id.name);

        availableSlotsView = (TextView) v.findViewById(R.id.availableslots);

        availableBikesView = (TextView) v.findViewById(R.id.availablebikes);

        Bundle mArgs = getArguments();

        final int id = mArgs.getInt("id");

        final String name = mArgs.getString("name");
        nameView.setText(name);

        final String address = mArgs.getString("address");

        final String availableBikeStands = mArgs.getString("availableBikeStands");
        availableSlotsView.setText(availableBikeStands);

        final String availableBikes = mArgs.getString("availableBikes");
        availableBikesView.setText(availableBikes);


        addToFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteStationsService = new FavouriteStationsService(getContext());

                if(favouriteStationsService.addFavouriteStation(id, name, address, availableBikeStands,  availableBikes)){
                    Toast.makeText(getActivity(), address+" added to favourites",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), address+" already a favourite",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}