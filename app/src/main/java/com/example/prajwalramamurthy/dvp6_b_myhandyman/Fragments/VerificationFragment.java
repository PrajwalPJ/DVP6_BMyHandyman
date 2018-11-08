package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

//                    verifyButton = profileFragment.verifyButton.findViewById(R.id.verifyButton);
//                    verifyButton.setBackgroundColor(getResources().getColor(R.color.skyBlue ));
                }
                myVerificationListener.saveVerifiedData();
            }
        });


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

        ProfileFragment profileFragment = new ProfileFragment();
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

        inflater.inflate(R.menu.menu_camera, menu);
    }


}
