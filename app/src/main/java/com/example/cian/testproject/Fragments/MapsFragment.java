package com.example.cian.testproject.Fragments;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cian.testproject.Model.Station;
import com.example.cian.testproject.R;
import com.example.cian.testproject.Service.WebService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


public class MapsFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    // Gets the URL from the UI's text field.
    String stringUrl = "";
    SupportMapFragment mapFragment;
    Map<String, Station> mappedStations;

    // newInstance constructor for creating fragment with arguments
    public static MapsFragment newInstance(int page, String title) {
        MapsFragment fragmentFirst = new MapsFragment();
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mapsfragment, null, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);

        mappedStations = new HashMap<>();

        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        WebService webService = new WebService();

        List<Station> stations = webService.requestToEndPoint(this.stringUrl);

        mMap = googleMap;

        mMap.setOnMarkerClickListener(this);

        // Move the camera to dublin with zoom at 13
        LatLng dublin = new LatLng(53.3498, -6.2603);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dublin, 14));
        //creating the GPS

        for (int i = 0; i < stations.size(); i++) {
            //save to hashmap
            mappedStations.put(stations.get(i).getAddress(), stations.get(i));

            LatLng gpsPosition = new LatLng(stations.get(i).getPosition().getLat(), stations.get(i).getPosition().getLng());
            // create marker and cutomise
            mMap.addMarker(new MarkerOptions().position(gpsPosition).title(stations.get(i).getAddress()).icon(BitmapDescriptorFactory.defaultMarker(186)));
        }

    }


    //https://stackoverflow.com/questions/17622622/how-to-pass-data-from-a-fragment-to-a-dialogfragment
    @Override
    public boolean onMarkerClick(Marker marker) {

        Station station = mappedStations.get(marker.getTitle());

        BottomSheetDialogFragment newFragment = new CustomBottomSheetDialogFragment();
        Bundle args = new Bundle();

        int id = Integer.parseInt(station.getNumber());
        args.putInt("id",id);

        String name = station.getName();
        args.putString("name",name);

        String address = station.getAddress();
        args.putString("address",address);

        String availableBikeStands = station.getAvailable_bike_stands();
        args.putString("availableBikeStands",availableBikeStands);

        String availableBikes = station.getAvailable_bikes();
        args.putString("availableBikes",availableBikes);

        newFragment.setArguments(args);
        newFragment.show(getFragmentManager(), "dialog");

        return true;
    }
}

