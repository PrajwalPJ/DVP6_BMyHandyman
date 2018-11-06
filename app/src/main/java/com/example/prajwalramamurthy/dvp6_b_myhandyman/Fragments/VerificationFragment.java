package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.util.ArrayList;

public class VerificationFragment extends Fragment
{

    private static final String POST = "POST";

   public interface VerificationFragmentListner {
        void getCameraResult();
    }

    VerificationFragmentListner listner;

    public static VerificationFragment newInstance()
    {

        Bundle args = new Bundle();

       // args.putSerializable(POST, _myList);
        VerificationFragment fragment= new VerificationFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listner = (VerificationFragmentListner) context;
    }

    ImageView imageView;

    public void displayImage( Bitmap imageUri ){
        ( (ImageView) getView().findViewById(R.id.photoVIew)).setImageBitmap(imageUri);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView = (ImageView) view.findViewById(R.id.imageView);
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
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.camera_button) {
            listner.getCameraResult();
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
