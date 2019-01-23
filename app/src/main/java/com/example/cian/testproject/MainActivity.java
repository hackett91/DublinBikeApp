package com.example.cian.testproject;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.cian.testproject.Fragments.FavouriteFragment;
import com.example.cian.testproject.Fragments.MapsFragment;
import com.example.cian.testproject.Patterns.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.container);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.colorHeading,getTheme()));

        //add Fragements here
        adapter.addFragment(new MapsFragment(),"Maps");
        adapter.addFragment(new FavouriteFragment(), "Favourites");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.map);
        tabLayout.getTabAt(1).setIcon(R.drawable.favourite);
    }


}
