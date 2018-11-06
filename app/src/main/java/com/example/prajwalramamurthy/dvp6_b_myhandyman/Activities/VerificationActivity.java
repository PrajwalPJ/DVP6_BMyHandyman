package com.example.prajwalramamurthy.dvp6_b_myhandyman.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.VerificationFragment;
import com.example.prajwalramamurthy.dvp6_b_myhandyman.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VerificationActivity extends AppCompatActivity
{


//
//    private static final String TAG = "VerificationActivity";
//    private static final int PICTURE_REQUEST = 0x0101;
//    // authority from thr manifest file
//    public static final String AUTHORITY = "com.example.prajwalramamurthy.dvp6_b_myhandyman.fileprovider";
//    // path name derived from my xml layout
//    private static final String IMAGE_FOLDER = "images";
//    // what all my files base name will contain
//    private static final String IMAGE_FILE = "image_";
//
//    // array list to hold all my images
//    private ArrayList<Uri> allMyImages;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.fragment_verification);
//
//        getAllMyFiles();
//
////        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,
////                VerificationFragment.newInstance(allMyImages)).commit();
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        // if the camera button is clicked
//        if(item.getItemId() == R.id.camera_button)
//        {
//            // then create a new intent
//            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            // get the Uri
//            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, returnUri());
//            // grant permission so it can write data
//            intentCamera.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            // start activity
//            startActivityForResult(intentCamera, PICTURE_REQUEST);
//        }
//
//        return true;
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode == RESULT_OK && requestCode == PICTURE_REQUEST)
//        {
//            getAllMyFiles();
//
////            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,
////                    VerificationFragment.newInstance(allMyImages)).commit();
//        }
//    }
//
//    private Uri returnUri()
//    {
//        File newImageFile = getMyImageFile();
//
//
//        return (newImageFile == null ? null : FileProvider.getUriForFile(this, AUTHORITY, newImageFile));
//    }
//
//    // get image from storage
//    private File getMyImageFile()
//    {
//
//        boolean test;
//
//        File protectedStorage = getExternalFilesDir(IMAGE_FOLDER);
//
//        File imageFile = new File(protectedStorage, formatMyFileName());
//
//        try
//        {
//            test = imageFile.createNewFile();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//
//        Log.i(TAG, "getMyImageFile: " + test);
//        return imageFile;
//
//    }
//
//    // create new name so it does not overwrite
//    private String formatMyFileName()
//    {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
//
//        return IMAGE_FILE + dateFormatter.format(new Date()) + ".jpeg";
//
//    }
//
//    // get all files from storage
//    private void getAllMyFiles()
//    {
//        allMyImages = new ArrayList<>();
//
//        File dir = getExternalFilesDir(IMAGE_FOLDER);
//
//        File[] files = dir != null ? dir.listFiles() : new File[0];
//
//        if(files.length == 0)
//        {
//            Log.i("Tag", "Files are empty");
//        }
//        else
//        {
//            for (File aFile : files)
//            {
//                Uri newUri = Uri.parse(aFile.getAbsolutePath());
//                allMyImages.add(newUri);
//            }
//        }
//
//    }
}
