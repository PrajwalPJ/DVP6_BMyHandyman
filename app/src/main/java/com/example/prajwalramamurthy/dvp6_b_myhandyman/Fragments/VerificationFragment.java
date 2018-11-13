package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.app.NativeActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class VerificationFragment extends Fragment
{
    private static final String POST = "POST";
    private static final String ARG_URI = "ARG_URI";
    private static final int PICTURE_REQUEST = 0x0101;
    Button verifyButton;
    private Uri imageUri;
    Button uploadImgButton;
    private ImageView photoViewImage;

    private DatabaseReference mDatabase;

    public interface VerificationFragmentListener
    {
        void getCameraResult();
        void saveVerifiedData();
        void changeUI();
    }

    VerificationFragmentListener myVerificationListener;

    public static VerificationFragment newInstance()
    {

        return new VerificationFragment();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        myVerificationListener = (VerificationFragmentListener) context;
    }



    public void displayImage( Bitmap imageUri )
    {
        ( (ImageView) getView().findViewById(R.id.photoVIew)).setImageBitmap(imageUri);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        photoViewImage = view.findViewById(R.id.photoVIew);
        uploadImgButton = view.findViewById(R.id.uploadImageButton);



        // if upload ID button is clicked this will take place
        uploadImgButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // then create a new intent
                Intent intentCamera = new Intent(Intent.ACTION_PICK);

                intentCamera.setType("image/jpeg");
                // start activity
                startActivityForResult(intentCamera, PICTURE_REQUEST);


            }
        });

        view.findViewById(R.id.saveVerifiyButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(photoViewImage.getDrawable() != null)
                {
                    Toast.makeText(getContext(), "Photo was saved!", Toast.LENGTH_SHORT).show();

//
//                    Bitmap bmp =  BitmapFactory.decodeResource(getResources(),R.drawable.chicken);//your image
//                    ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
//                    bmp.recycle();
//                    byte[] byteArray = bYtE.toByteArray();
//                    String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

//                    byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
//                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                    verifyButton = profileFragment.verifyButton.findViewById(R.id.verifyButton);
//                    verifyButton.setBackgroundColor(getResources().getColor(R.color.skyBlue ));
                }
                myVerificationListener.saveVerifiedData();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = Objects.requireNonNull(getContext()).checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += Objects.requireNonNull(getContext()).checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        }else{
            Log.d("", "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == PICTURE_REQUEST)
        {
            imageUri = data.getData();
            photoViewImage.setImageURI(imageUri);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_verification, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.camera_button:

                myVerificationListener.getCameraResult();

                break;

            case R.id.saveVerifiyButton:

                myVerificationListener.saveVerifiedData();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        inflater.inflate(R.menu.menu_camera, menu);
    }


}
