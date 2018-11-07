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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.CreateFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ExploreFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ProfileFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.VerificationFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.MainActivity;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity implements ProfileFragment.ProfileFragmentLister , VerificationFragment.VerificationFragmentListener
{

    // member/stored variables
    private BottomNavigationView myBottomNavBar;
    private FrameLayout myMainFrame;
    private CreateFragment createFragment;
    private ExploreFragment exploreFragment;
    private ProfileFragment profileFragment;
    private VerificationFragment verificationFragment;

    private static final int PICTURE_REQUEST = 0x0101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);

        // initialize my bottom navigation bar and my frame layout
        myMainFrame = findViewById(R.id.main_frame_layout);
        myBottomNavBar = findViewById(R.id.main_navigation_bar);


        // initialize my fragments
        createFragment = new CreateFragment();
        exploreFragment = new ExploreFragment();
        profileFragment = new ProfileFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, ExploreFragment.newInstance()).commit();

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

                        // change the color when selected

                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, ExploreFragment.newInstance()).commit();


                        return true;

                    case R.id.nav_create:

                        // change the color when selected
                        myBottomNavBar.setItemBackgroundResource(R.color.colorPrimary);

                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, CreateFragment.newInstance()).commit();

                        return true;


                    case R.id.nav_profile:

                        // change the color when selected

                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, ProfileFragment.newInstance()).commit();

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

        if(requestCode == PICTURE_REQUEST)
        {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            verificationFragment.displayImage(photo);
        }
    }

    @Override
    public void onVerifyButtonClick()
    {
        verificationFragment =  VerificationFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,verificationFragment).commit();
    }

    @Override
    public void getCameraResult()
    {
        // then create a new intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Start intent with Action_Image_Capture
        startActivityForResult(intent, PICTURE_REQUEST);

    }

    @Override
    public void saveVerifiedData()
    {


        profileFragment = ProfileFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,profileFragment).commit();

    }

    @Override
    public void changeUI() {

    }


}




