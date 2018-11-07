// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities.CreateHandymanActivty;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities.ProfileActivity;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.ServiceOrder;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateFragment extends Fragment implements DatePickerDialog.OnDateSetListener
{

    private EditText title;
    private EditText desc;
    private EditText location;
    private EditText time;
    private TextView date;
    private TextView createHandyman;

    private CreateFragmentListener myCreateListener;

    public interface CreateFragmentListener
    {
        void onAreYouAHandymanLink();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        myCreateListener = (CreateFragmentListener) context;
    }

    public static CreateFragment newInstance()
    {
        
        Bundle args = new Bundle();
        
        CreateFragment fragment = new CreateFragment();
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        createHandyman = getView().findViewById(R.id.createHandymanLink);

        createHandyman.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myCreateListener.onAreYouAHandymanLink();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_save, menu);
    }

    private DatabaseReference mDatabase;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Integer itemId = item.getItemId();

        switch(itemId)
        {
            // when my save button is clicked this is what will run
            case R.id.save_button:
            {
                if (title.getText().toString().isEmpty() || desc.getText().toString().isEmpty()
                        || location.getText().toString().isEmpty() || time.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), R.string.empty_fields_toast, Toast.LENGTH_LONG).show();
                } else
                {
                    // catch and store the user input and pass it to our data model
                    String mTitle = title.getText().toString();
                    String mDesc = desc.getText().toString();
                    String mLocation = location.getText().toString();
                    String mTime = time.getText().toString();
                    String dateStore = date.toString();

                    // add the new service order
                    ServiceOrder newSerOrder = new ServiceOrder(mTitle, mDesc, mLocation, mTime, dateStore);

                    // TODO // do something with this

                    mDatabase.child("orders").push().setValue(newSerOrder);
                    // show toast for confirmation
                    Toast.makeText(getContext(), R.string.created_order_toast, Toast.LENGTH_LONG).show();
                    break;
                }

            }


        }
        return true;
    }

    // shows the date picker when user clicks on select date
    private void pickerDialog()
    {
        if(getContext() != null)
        {
            DatePickerDialog datePicker = new DatePickerDialog(getContext(), this, 2018, 11, 8);
            datePicker.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {


        //SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        //StringBuffer sb = new StringBuffer();

        //String hireDate = formatter.format(calendar.getTime(), sb, new FieldPosition(0)).toString();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

        StringBuffer infoType = new StringBuffer();

        String stringPref = sharedPref.getString("dateType", "09/12/18");

        SimpleDateFormat simpDate = new SimpleDateFormat(stringPref);

        String dateToDisplay = simpDate.format(calendar.getTime(), infoType, new FieldPosition(0)).toString();

        date.setText(dateToDisplay);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if(getView() != null)
        {
            title = getView().findViewById(R.id.titleView);
            desc = getView().findViewById(R.id.descView);
            location = getView().findViewById(R.id.locationView);
            time = getView().findViewById(R.id.timeView);
            date = getView().findViewById(R.id.dateView);
            Button selectButton = getView().findViewById(R.id.selectdateButton);


            selectButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    pickerDialog();

                }
            });


        }


    }
}
