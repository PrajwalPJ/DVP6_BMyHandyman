package com.example.prajwalramamurthy.dvp6_b_myhandyman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

// Key Hash
// ga0RGNYHvNM5d0SLGQfpQWAPGJ8=

public class MainActivity extends AppCompatActivity {

    //FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


    }
}
