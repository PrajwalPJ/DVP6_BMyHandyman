package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.Handyman;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateHandymanFragment extends Fragment
{
    private EditText  mTitle;
    private EditText  mBio;
    private EditText  mLocation;
    private EditText  mAvailability;
    private EditText  mYearsExp;
    private EditText  mHourRate;

    private CreateHandymanListener myCreateHandymanListener;

    public interface CreateHandymanListener
    {
        void onPostServiceOrderLink();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        myCreateHandymanListener = (CreateHandymanFragment.CreateHandymanListener) context;
    }

    public static CreateHandymanFragment newInstance()
    {

      return new CreateHandymanFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_handyman, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        inflater.inflate(R.menu.menu_save, menu);
    }

    private DatabaseReference mDatabase;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer itemId = item.getItemId();

        switch(itemId)
        {
            // when my save button is clicked this is what will run
            case R.id.save_button:
            {
                if (mTitle.getText().toString().isEmpty() || mBio.getText().toString().isEmpty()
                        || mLocation.getText().toString().isEmpty() || mAvailability.getText().toString().isEmpty()
                        || mYearsExp.getText().toString().isEmpty() || mHourRate.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), R.string.empty_fields_toast, Toast.LENGTH_LONG).show();
                } else
                {
                    // catch and store the user input and pass it to our data model
                    String title = mTitle.getText().toString();
                    String bio = mBio.getText().toString();
                    String location = mLocation.getText().toString();
                    String avil = mAvailability.getText().toString();
                    Integer years = Integer.parseInt(mYearsExp.getText().toString());
                    Integer rate = Integer.parseInt(mHourRate.getText().toString());


                    // add the new service order
                    Handyman handyman = new Handyman(title, bio, location, avil, years, rate);


                    mDatabase.child("handyman").push().setValue(handyman);
                    // show toast for confirmation
                    Toast.makeText(getContext(), R.string.created_order_toast, Toast.LENGTH_LONG).show();
                    break;
                }

            }


        }
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        TextView createServiceOrder = getView().findViewById(R.id.createServiceOrderLink);

        createServiceOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myCreateHandymanListener.onPostServiceOrderLink();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getView() != null)
        {
            mTitle = getView().findViewById(R.id.titleViewHand);
            mBio = getView().findViewById(R.id.bioViewHand);
            mLocation = getView().findViewById(R.id.locationViewHand);
            mAvailability = getView().findViewById(R.id.availViewHand);
            mYearsExp = getView().findViewById(R.id.yearsViewHand);
            mHourRate = getView().findViewById(R.id.rateViewHand);

        }
    }
}
