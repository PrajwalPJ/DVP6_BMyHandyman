// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ExploreFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

public class ExploreActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_explore);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, ExploreFragment.newInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        return true;
    }
}
