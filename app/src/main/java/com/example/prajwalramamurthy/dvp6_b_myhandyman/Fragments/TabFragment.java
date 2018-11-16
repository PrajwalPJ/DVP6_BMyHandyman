// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters.PagerAdapter;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.util.Objects;


public class TabFragment extends Fragment {

    public TabFragment() {
        // Required empty public constructor
    }


    public static TabFragment newInstance() {

        return new TabFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // create the two tabs

        TabLayout tabLayout =  view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Service Order"));
        tabLayout.addTab(tabLayout.newTab().setText("Handyman"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // set the adapters and listeners
        final ViewPager viewPager =  view.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (Objects.requireNonNull(getActivity()).getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
