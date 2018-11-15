package com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class VerificationFragment extends Fragment
{
    private static final String POST = "POST";
    //private static final String ARG_URI = "ARG_URI";
    private static final int PICTURE_REQUEST = 0x0101;
    private ImageView photoViewImage;
    private DatabaseReference mDatabase;

    //private ArrayList<Bitmap> myImageArrayList;

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



    public void displayImage( Bitmap bitmap )
    {
        if(getView() != null) {
            ((ImageView) (getView()).findViewById(R.id.photoVIew)).setImageBitmap(bitmap);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        photoViewImage = view.findViewById(R.id.photoVIew);

        Button uploadImgButton = view.findViewById(R.id.uploadImageButton);



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

        //checkFilePermissions();

        view.findViewById(R.id.saveVerifiyButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(photoViewImage.getDrawable() != null)
                {
                    Toast.makeText(getContext(), R.string.toast_id_saved, Toast.LENGTH_SHORT).show();

                    final StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("IDImages");

                    StorageReference imagesRef = storageRef.child(String.valueOf((new Date()).getTime()) + ".jpg");

                    Bitmap bitmap = ((BitmapDrawable) photoViewImage.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    UploadTask uploadTask = imagesRef.putBytes(data);


                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            // ...

                            Uri url = taskSnapshot.getDownloadUrl();

                            String uid = FirebaseAuth.getInstance().getUid();

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                               DatabaseReference user = databaseReference.child("users").child(uid);

                               user.child("id_img").setValue(url.toString());
//
                        }
                    });

                    myVerificationListener.saveVerifiedData();

                }
                else
                {
                    Toast.makeText(getContext(), R.string.toast_id_not_saved, Toast.LENGTH_LONG).show();
                    myVerificationListener.saveVerifiedData();
                }

            }
        });


    }


//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void checkFilePermissions() {
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
//            int permissionCheck = Objects.requireNonNull(getContext()).checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
//            permissionCheck += Objects.requireNonNull(getContext()).checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
//            if (permissionCheck != 0) {
//                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
//            }
//        }else{
//            Log.d("", "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        mDatabase.child("imageUrl").push();
//
//        if(resultCode == RESULT_OK && requestCode == PICTURE_REQUEST)
//        {
//            Uri imageUri = data.getData();
//            photoViewImage.setImageURI(imageUri);
//
//            try
//            {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getContext()).getContentResolver(), imageUri);
//                photoViewImage.setImageBitmap(bitmap);
//
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }

//        if (requestCode == RESULT_OK && resultCode == getActivity().RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            photoViewImage.setImageBitmap(imageBitmap);
//            encodeBitmapAndSaveToFirebase(imageBitmap);
//        }
    //}

//    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//        DatabaseReference ref = FirebaseDatabase.getInstance()
//                .getReference(Constants.IAP_PRODUCT_ID)
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                //.child(// TODO .getPushId())
//                .child("imageUrl");
//        ref.setValue(imageEncoded);
//    }

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
