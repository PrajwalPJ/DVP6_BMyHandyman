// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

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

public class ExploreFragment extends Fragment
{

    private final ArrayList<ServiceOrder> orders = new ArrayList<>();
    private final ArrayList<Handyman> handymen = new ArrayList<>();
    private ServiceOrderAdapter serviceOrderAdapter;
    private ListView myListView;
    private String selector;
    private HandymanAdapter handyManAdapter;

    public static ExploreFragment newInstance(String selector)
    {



        ExploreFragment fragment = new ExploreFragment();
        fragment.selector = selector;
        return fragment;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    if(selector == "orders") {
                        ServiceOrder order = postSnapshot.getValue(ServiceOrder.class);
                        orders.add(order);
                    } else {
                        Handyman order = postSnapshot.getValue(Handyman.class);
                        handymen.add(order);
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

        mDatabase.child(selector  == "orders"? "orders" : "handyman").addValueEventListener(postListener);

       serviceOrderAdapter = new ServiceOrderAdapter(getContext(), orders);
        handyManAdapter = new HandymanAdapter(getContext(), handymen);

        myListView = view.findViewById(R.id.myListView);


        if (selector  == "orders") {
            myListView.setAdapter(serviceOrderAdapter);
        } else {
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

        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Integer itemId = item.getItemId();

        switch (itemId)
        {

        }

        return true;
    }

}
