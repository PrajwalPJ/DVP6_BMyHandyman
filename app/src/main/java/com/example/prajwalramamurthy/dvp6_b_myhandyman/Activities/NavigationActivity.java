package com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.CreateFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ExploreFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ProfileFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

public class NavigationActivity extends AppCompatActivity
{

    // member/stored variables
    private BottomNavigationView myBottomNavBar;
    private FrameLayout myMainFrame;
    private CreateFragment createFragment;
    private ExploreFragment exploreFragment;
    private ProfileFragment profileFragment;


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

//        setFragment(exploreFragment);

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
                        myBottomNavBar.setItemBackgroundResource(R.color.colorAccent);
                        // method call
                        //setFragment(exploreFragment);


                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, ExploreFragment.newInstance()).commit();
                        // ExploreFragment.newInstance();

//                        Intent exploreIntent = new Intent(NavigationActivity.this, ExploreActivity.class);
//                        startActivity(exploreIntent);
//                        finish();

                        return true;


                    case R.id.nav_create:

                        // change the color when selected
                        myBottomNavBar.setItemBackgroundResource(R.color.colorPrimary);
                        // method call
                        setFragment(createFragment);

                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, CreateFragment.newInstance()).commit();


//                        Intent createIntent = new Intent(NavigationActivity.this, CreateActivity.class);
//                        startActivity(createIntent);
//                        finish();

                        return true;


                    case R.id.nav_profile:

                        // change the color when selected
                        myBottomNavBar.setItemBackgroundResource(R.color.colorPrimaryDark);
                        // method call
                        setFragment(profileFragment);
//
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, ProfileFragment.newInstance()).commit();

//                        Intent profileIntent = new Intent(NavigationActivity.this, ProfileActivity.class);
//                        startActivity(profileIntent);
//                        finish();

                        return true;

                        // TODO MAP

                        default:

                            return false;

                }
            }

        });


    }


    // custom method to load our different fragments and layouts
    private void setFragment(Fragment fragment)
    {

        // loads the fragment and layout
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout, fragment);
        fragTransaction.commit();

//        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment);
//        ExploreFragment.newInstance();
    }
}
