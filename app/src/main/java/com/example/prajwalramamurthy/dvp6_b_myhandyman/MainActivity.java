package com.example.prajwalramamurthy.dvp6_b_myhandyman;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    public static final String TAG = "FACEBOOK_LOGIN";

    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL, "public_profile"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                // gets the access token and create auth token
                // then signs in with credentials
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel()
            {
                // App code
            }

            @Override
            public void onError(FacebookException exception)
            {
                // App code
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Log.i(TAG, "onStart: TEST DIS" + currentUser);
        //updateUI();
        if(currentUser != null)
        {
            updateUI();
        }
    }

    private void updateUI()
    {
        Toast.makeText(MainActivity.this, "You're logged in ", Toast.LENGTH_SHORT).show();

        Intent accountIntent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(accountIntent);
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token)
    {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else
                            {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }


}
