// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.EditProfileFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ProfileFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.VerificationFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

public class ProfileActivity extends AppCompatActivity
{

    private Button mVerifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);


        mVerifyButton = findViewById(R.id.verifyButton);

        mVerifyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        Integer itemId = item.getItemId();

        switch(itemId)
        {
            case R.id.edit_button:
            {
                Intent intent = new Intent(ProfileActivity.this, EditProfileFragment.class);
                startActivity(intent);
            }
        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mVerifyButton = findViewById(R.id.verifyButton);

        mVerifyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });
    }
}
