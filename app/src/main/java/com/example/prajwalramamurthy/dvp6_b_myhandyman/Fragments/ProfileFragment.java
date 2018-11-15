// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.Handyman;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.Person;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.ServiceOrder;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfileFragment extends Fragment
{

    private ProfileFragmentLister myProfileListener;

    public  interface ProfileFragmentLister
    {
        void onVerifyButtonClick();
        void launchLogin();
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

        //inflater.inflate(R.menu.menu_edit, menu);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        myProfileListener = (ProfileFragmentLister) context;
    }

    Person person;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final Button verifyButton = view.findViewById(R.id.verifyButton);


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
                myProfileListener.launchLogin();

            }
        });

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener userListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                person = dataSnapshot.getValue(Person.class);


                if(person.profile_img !=null) {
                   ImageView profile_img = ((ImageView) view.findViewById(R.id.profile_pic));
                   Picasso.get().load(person
                   .profile_img).into(profile_img);
                }

                if(person
                        .mEmail != null) {
                    ((TextView) view.findViewById(R.id.user_email_view)).setText(person
                    .mEmail);
                }

                if(person
                        .mFirstName != null) {
                    ((TextView) view.findViewById(R.id.user_name_text)).setText(person
                            .mFirstName);
                }

                if(person.id_img != null) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        verifyButton.setBackgroundColor(getResources().getColor(R.color.colorAccent, null));
                        verifyButton.setText("VERIFIED");
                        verifyButton.setClickable(false);
                    }
                } else {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
if(FirebaseAuth.getInstance().getUid() != null) {
    mDatabase.child("users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(userListner);
}

    }
}
