// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities.NavigationActivity;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.DataModel.Person;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener
{

    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private static final String TAG = "FACEBOOK_LOGIN";
    private TextView toastMe;
    private LoginButton loginButton;
    private NetworkStateReceiver networkStateReceiver;

    public void onDestroy() {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.fragment_login);


        loginButton = findViewById(R.id.login_button);

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        toastMe = findViewById(R.id.error_toast);

        if(isNetworkAvailable()) {

            toastMe.setText(R.string.terms_cond);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                toastMe.setTextColor(getResources().getColor(R.color.white, null));
            }

            myMainActivityFunc();

        }
        else {
            final LoginButton loginButton = findViewById(R.id.login_button);

            loginButton.setClickable(false);

            toastMe.setText(R.string.toast_network);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                toastMe.setTextColor(getResources().getColor(R.color.red, null));
            }

            Toast.makeText(MainActivity.this, R.string.toast_network, Toast.LENGTH_LONG).show();

        }

}


private void myMainActivityFunc()
    {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Log.i(TAG, "onStart: TEST DIS" + currentUser);
        //updateUI();
        if(currentUser != null)
        {
            updateUI();
        }

        callbackManager = CallbackManager.Factory.create();


        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // gets the access token and create auth token
                // then signs in with credentials
                handleFacebookAccessToken(loginResult.getAccessToken());

                updateUI();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(MainActivity.this, R.string.toast_id_not_saved, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    private void updateUI()
    {
        Toast.makeText(MainActivity.this, R.string.login_toast, Toast.LENGTH_SHORT).show();

        Intent navigationIntent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(navigationIntent);
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


                            Person person = new Person(Objects.requireNonNull(user).getDisplayName(),user.getEmail(),Objects.requireNonNull(user.getPhotoUrl()).toString());

                            DatabaseReference mDatabase;

                            mDatabase = FirebaseDatabase.getInstance().getReference();

                            mDatabase.child("users").child(user.getUid()).setValue(person);

                            updateUI();
                        } else
                            {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, R.string.fb_auth_fail_toast,
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public void networkAvailable()
    {
        toastMe.setText(R.string.terms_cond);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toastMe.setTextColor(getResources().getColor(R.color.white, null));
        }
        loginButton.setClickable(true);

       Toast.makeText(this, R.string.network_connected_toast, Toast.LENGTH_SHORT).show();
        myMainActivityFunc();
    }

    @Override
    public void networkUnavailable() {

    }
}

class NetworkStateReceiver extends BroadcastReceiver {

    protected Set<NetworkStateReceiverListener> listeners;
    protected Boolean connected;

    public NetworkStateReceiver() {
        listeners = new HashSet<NetworkStateReceiverListener>();
        connected = null;
    }

    public void onReceive(Context context, Intent intent) {
        if(intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();

        if(ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
            connected = false;
        }

        notifyStateToAll();
    }

    private void notifyStateToAll() {
        for(NetworkStateReceiverListener listener : listeners)
            notifyState(listener);
    }

    private void notifyState(NetworkStateReceiverListener listener) {
        if(connected == null || listener == null)
            return;

        if(connected == true)
            listener.networkAvailable();
        else
            listener.networkUnavailable();
    }

    public void addListener(NetworkStateReceiverListener l) {
        listeners.add(l);
        notifyState(l);
    }

    public void removeListener(NetworkStateReceiverListener l) {
        listeners.remove(l);
    }

    public interface NetworkStateReceiverListener {
        public void networkAvailable();
        public void networkUnavailable();
    }
}