package com.example.cian.testproject.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {

        //columns
        private static final String TABLE_FAVSTATION = "FavouriteStations";
        private static final String KEY_STATION_ID 	 = "_id";
        private static final String KEY_NAME = "name";
        private static final String KEY_ADDRESS = "address";
        private static final String KEY_AVAILABLE_BIKE_STANDS = "available_bike_stands";
        private static final String KEY_AVAILABLE_BIKES = "available_bikes";

        //Database info
        private static final String DATABASE_NAME 	= "DublinBikes";
        private static final int DATABASE_VERSION 	= 1;

        //Station info banking 1 true 0 false (boolean replacement)
        private static final String SQL_CREATE_TABLE_STATION =
                "create table FavouriteStations( _id integer primary key," +
                        "name text not null,"  +
                        "address text not null," +
                        "available_bike_stands text not null,"  +
                        "available_bikes text not null"+
                        ");";


        private final Context context;
        private DatabaseHelper DBHelper;
        private SQLiteDatabase db;

        public DatabaseAccess(Context cxt){
            this.context = cxt;
            DBHelper = new DatabaseHelper(context);
        }

        public DatabaseAccess open() throws SQLException
        {
            db  = DBHelper.getWritableDatabase();
            return this;
        }

        // nested dB helper class
        private static class DatabaseHelper extends SQLiteOpenHelper {

            DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

                db.execSQL(SQL_CREATE_TABLE_STATION);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion,
                                  int newVersion) {
                // dB structure change..

            }
        }//end of nested class

        // remainder of the Database Example methods to "use" the database
        public void close()
        {
            DBHelper.close();
        }


        public long addFavouriteStation(int _id, String name, String address, String available_bike_stands, String available_bikes){
            ContentValues insertFavouriteValues = new ContentValues();

            insertFavouriteValues.put(KEY_STATION_ID, _id);
            insertFavouriteValues.put(KEY_NAME, name);
            insertFavouriteValues.put(KEY_ADDRESS, address);
            insertFavouriteValues.put(KEY_AVAILABLE_BIKE_STANDS, available_bike_stands);
            insertFavouriteValues.put(KEY_AVAILABLE_BIKES,available_bikes);

            return db.insert(TABLE_FAVSTATION,null,insertFavouriteValues);
        }

        public Cursor getAllFavouriteStations()
        {
            Cursor mCursor =
                    db.query(true, TABLE_FAVSTATION, new String[]
                                    {
                                            KEY_STATION_ID,
                                            KEY_NAME,
                                            KEY_ADDRESS,
                                            KEY_AVAILABLE_BIKE_STANDS,
                                            KEY_AVAILABLE_BIKES
                                    },
                            null,  null, null, null, null, null);

            if (mCursor != null)
            {
                mCursor.moveToFirst();
            }
            return mCursor;
        }


    public Cursor getFavouriteStation(int rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, TABLE_FAVSTATION, new String[]
                        {
                                KEY_STATION_ID,
                                KEY_NAME,
                                KEY_ADDRESS,
                                KEY_AVAILABLE_BIKE_STANDS,
                                KEY_AVAILABLE_BIKES
                        },
                        KEY_STATION_ID + "=" + rowId,  null, null, null, null, null);

        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean removeFavouriteStation(int rowId)
    {
        return db.delete(TABLE_FAVSTATION, KEY_STATION_ID +
                "=" + rowId, null) > 0;
    }
}
