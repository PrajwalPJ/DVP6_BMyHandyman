// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.CreateFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.CreateHandymanFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ProfileFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.TabFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.VerificationFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.MainActivity;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;


import java.io.IOException;
import java.util.Objects;

public class NavigationActivity extends AppCompatActivity implements ProfileFragment.ProfileFragmentLister ,
        VerificationFragment.VerificationFragmentListener, CreateFragment.CreateFragmentListener,
        CreateHandymanFragment.CreateHandymanListener
{

    // member/stored variables
    private BottomNavigationView myBottomNavBar;
    private CreateFragment createFragment;
    private TabFragment exploreFragment;
    private ProfileFragment profileFragment;
    private VerificationFragment verificationFragment;
    private static final int PICTURE_REQUEST = 0x0101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);

        // initialize my bottom navigation bar and my frame layout
        myBottomNavBar = findViewById(R.id.main_navigation_bar);


        // initialize my fragments
        createFragment = CreateFragment.newInstance();
        exploreFragment = TabFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();
        verificationFragment =  VerificationFragment.newInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, exploreFragment).commit();

        final Menu menu = myBottomNavBar.getMenu();
        final MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        // when an option in the nav bar is clicked this is what handles the click
        myBottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch(item.getItemId())
                {
                    case R.id.nav_explore:

                        // load my tab fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, TabFragment.newInstance()).commit();
                        exploreFragment.onResume();

                        return true;

                    case R.id.nav_create:

                        // load create fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,createFragment).commit();

                        return true;


                    case R.id.nav_profile:

                        // load profile fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, profileFragment).commit();

                        return true;

                    // TODO MAP

                    default:

                        return false;

                }
            }

        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // get image Uri
        // convert imageto bitmap
        // display it in image view
        if(requestCode == PICTURE_REQUEST)
        {
            Uri imageUri = Objects.requireNonNull(data).getData();

            Bitmap photo = null;

            if (imageUri != null) {
                try {
                    photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

            photo = (Bitmap) Objects.requireNonNull(Objects.requireNonNull(data).getExtras()).get("data");}
            if(photo != null) {
                verificationFragment.displayImage(photo);
            }
        }
    }


    // All these methods work as interface methods and each handle different functionalities based on the corresponding fragments
    // When verifi button is clicked
    @Override
    public void onVerifyButtonClick()
    {
        // it will launch the verification fragment
        verificationFragment =  VerificationFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,verificationFragment,"VerificationFragment").commit();
    }

    @Override
    public void launchLogin() {
        // then create a new intent
        Intent intent = new Intent(this, MainActivity.class);
        //Start intent with Action_Image_Capture
        startActivity(intent);
    }

    // when camera icon is clicked open the camera app
    @Override
    public void getCameraResult()
    {
        // then create a new intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Start intent with Action_Image_Capture
        startActivityForResult(intent, PICTURE_REQUEST);

    }

    // once the save button is clicked launch profile fragment after
    @Override
    public void saveVerifiedData()
    {

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,profileFragment).commit();

    }

    // when upload id button is clicked
    // get access to gallery from the device
    // and then fill the image view with selected image
    @Override
    public void uploadID()
    {
        // then create a new intent
        Intent intentCamera = new Intent(Intent.ACTION_PICK);

        intentCamera.setType("image/jpeg");
        // start activity
        startActivityForResult(intentCamera, PICTURE_REQUEST);

    }


    // when link clicked change fragments
    @Override
    public void onAreYouAHandymanLink()
    {
        CreateHandymanFragment createHandymanFragment = CreateHandymanFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, createHandymanFragment).commit();
    }

    // switch fragments when clicked
    @Override
    public void onPostServiceOrderLink()
    {
        createFragment = CreateFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, createFragment).commit();
    }
}




