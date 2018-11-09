// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

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
import android.widget.Button;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment
{

    private ProfileFragmentLister myProfileListener;
    private Button verifyButton;

    public  interface ProfileFragmentLister
    {
        void onVerifyButtonClick();
    }

    public static ProfileFragment newInstance()
    {
        
        return new ProfileFragment();

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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        inflater.inflate(R.menu.menu_edit, menu);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        myProfileListener = (ProfileFragmentLister) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        verifyButton = view.findViewById(R.id.verifyButton);


        verifyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                myProfileListener.onVerifyButtonClick();
            }
        });

        view.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

            }
        });

    }
}
