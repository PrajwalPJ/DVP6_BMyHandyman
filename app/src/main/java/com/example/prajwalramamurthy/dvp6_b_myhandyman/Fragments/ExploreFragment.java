// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters.HandymanAdapter;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters.ServiceOrderAdapter;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.Handyman;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.ServiceOrder;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ExploreFragment extends Fragment implements SearchView.OnQueryTextListener {

    private final ArrayList<ServiceOrder> orders = new ArrayList<>();
    private final ArrayList<Handyman> handymen = new ArrayList<>();
    private ServiceOrderAdapter serviceOrderAdapter;
    private String selector;
    private HandymanAdapter handyManAdapter;

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(serviceOrderAdapter != null && !newText.isEmpty()) {
            serviceOrderAdapter.getFilter().filter(newText);
        } else {
            serviceOrderAdapter.myServiceOrders = orders;
            serviceOrderAdapter.filteredData = orders;
            serviceOrderAdapter.notifyDataSetChanged();
        }

        if(handyManAdapter != null && !newText.isEmpty()) {
            handyManAdapter.getFilter().filter(newText);
        }else {
            handyManAdapter.myHandymen = handymen;
            handyManAdapter.filteredData = handymen;
            handyManAdapter.notifyDataSetChanged();
        }
        return false;
    }

    public interface ExploreFragmentListener
    {
        void searchButtonClick();
    }

    public static ExploreFragment newInstance(String selector)
    {

        ExploreFragment fragment = new ExploreFragment();
        fragment.selector = selector;
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    if(Objects.equals(selector, "orders")) {
                        ServiceOrder order = postSnapshot.getValue(ServiceOrder.class);
                        orders.add(order);
                    } else {
                        Handyman hand = postSnapshot.getValue(Handyman.class);
                        handymen.add(hand);
                    }

                    // notify that its changed and refresh the database

                    serviceOrderAdapter.notifyDataSetChanged();
                    handyManAdapter.notifyDataSetChanged();
                    //myListView.deferNotifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };

        mDatabase.child(Objects.equals(selector, "orders") ? "orders" : "handyman").addValueEventListener(postListener);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        serviceOrderAdapter = new ServiceOrderAdapter(getContext(), orders);
        handyManAdapter = new HandymanAdapter(getContext(), handymen);

        ListView myListView = view.findViewById(R.id.myListView);


        if (selector  == "orders") {
            myListView.setAdapter(serviceOrderAdapter);
        } else if(selector  == "handyman") {
            myListView.setAdapter(handyManAdapter);
        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(true);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
     setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {

        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager)
                getContext().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search_button);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

//        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
//        MenuItem searchMenuItem = menu.findItem(R.id.search_button);
//        SearchView searchView = (SearchView) searchMenuItem.getActionView();
//
//
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//
//                String some = query;
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });


//        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
//        MenuItem searchMenuItem = menu.findItem(R.id.search_button);
//        SearchView searchView = null;
//
//        if (searchMenuItem != null) {
//            searchView = (SearchView) searchMenuItem.getActionView();
//        }
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
//        }
//        return super.onCreateOptionsMenu(menu);

    }



}
