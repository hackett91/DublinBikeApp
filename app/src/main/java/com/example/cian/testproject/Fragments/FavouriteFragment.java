package com.example.cian.testproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cian.testproject.Service.FavouriteStationsService;
import com.example.cian.testproject.Model.FavouriteStation;
import com.example.cian.testproject.R;
import com.example.cian.testproject.Patterns.RecyclerAdapter;

import java.util.List;

public class FavouriteFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    View rootView;
    List<FavouriteStation> favouriteStations;
    FavouriteStationsService favService;
    private SwipeRefreshLayout swipeRefreshLayout;

    // newInstance constructor for creating fragment with arguments
    public static FavouriteFragment newInstance(int page, String title) {
        FavouriteFragment fragmentFirst = new FavouriteFragment();
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.favourite_stations, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.favouriterecycler);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), favouriteStations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setAdapter(recyclerAdapter);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        favService = new FavouriteStationsService(getContext());
        favouriteStations = favService.getAllFavouriteStations();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        refreshList();

    }

   public void refreshList(){
        //do processing to get new data and set your listview's adapter, maybe  reinitialise the loaders you may be using or so
        //when your data has finished loading, cset the refresh state of the view to false
       favService = new FavouriteStationsService(getContext());
       favouriteStations = favService.getAllFavouriteStations();
       recyclerView = (RecyclerView) rootView.findViewById(R.id.favouriterecycler);
       RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), favouriteStations);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       recyclerView.setAdapter(recyclerAdapter);
       swipeRefreshLayout.setRefreshing(false);

    }
}
