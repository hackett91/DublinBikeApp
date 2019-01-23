package com.example.cian.testproject.Service;

import android.content.Context;
import android.database.Cursor;

import com.example.cian.testproject.Model.DatabaseAccess;
import com.example.cian.testproject.Model.FavouriteStation;

import java.util.ArrayList;
import java.util.List;

public class FavouriteStationsService {

    List<FavouriteStation> favouriteStations;
    DatabaseAccess db;
    private Cursor cursor;


    public FavouriteStationsService(Context context){
        favouriteStations = new ArrayList<FavouriteStation>();
        db = new DatabaseAccess(context);
    }

    public List<FavouriteStation> getAllFavouriteStations() {

        db.open();
        cursor = db.getAllFavouriteStations();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String available_bike_stands = cursor.getString(cursor.getColumnIndex("available_bike_stands"));
                String available_bikes = cursor.getString(cursor.getColumnIndex("available_bikes"));

                favouriteStations.add( new FavouriteStation(id, name, address, available_bike_stands, available_bikes));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favouriteStations;
    }

    public boolean addFavouriteStation(int id, String name, String address, String availableBikeStands, String availableBikes){

        db.open();
        if(db.getFavouriteStation(id) == null){
            db.close();
            return false;
        }

        db.addFavouriteStation( id, name,address,availableBikeStands,availableBikes);

        db.close();
        return true;
    }

    public boolean removeFavouriteStation(int id){
        db.open();
        boolean removed = db.removeFavouriteStation(id);
        db.close();
        return removed;
    }
}
