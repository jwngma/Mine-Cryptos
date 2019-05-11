package com.store.minecryps.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.store.minecryps.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.net.URI;
import java.util.HashMap;

public class SetupActivityActivity extends AppCompatActivity {
    private static final String TAG = "SetupActivityActivity";
    private DatabaseReference mUserDatabase;
    private StorageReference mProfileStorage;

    private FirebaseAuth mAuth;
    private ImageView setup_image;
    private Button changeBtn, setupSaveBtn;
    private EditText setupName,setupEmail, setupCountry, setupGender, setupDob;

    private  static  final  int GALLERYREQUEST=221;
    private Uri resultUri;

    private ProgressDialog progressDialog;
    private Toolbar mtoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_activity);
        Log.d(TAG, "onCreate: Setup Activity has started");
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mUserDatabase.keepSynced(true);
        mProfileStorage = FirebaseStorage.getInstance().getReference().child("Profile_Images");
        mAuth=FirebaseAuth.getInstance();



        initAll();
        initToolbar();
        setupProfile_image();
        SaveAllData();
        retrieveDataFromFirebase();
    }

    private void retrieveDataFromFirebase() {
        mUserDatabase.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("image")){
                        final String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).placeholder(R.drawable.person).networkPolicy(NetworkPolicy.OFFLINE).into(setup_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get().load(image).placeholder(R.drawable.person).into(setup_image);
                            }
                        });


                    }
                    if (dataSnapshot.hasChild("fullname")){
                        String full_name = dataSnapshot.child("fullname").getValue().toString();
                        setupName.setText(full_name);
                    }
                    if (dataSnapshot.hasChild("country")){
                        String countri = dataSnapshot.child("country").getValue().toString();
                        setupCountry.setText(countri);
                    }
                    if (dataSnapshot.hasChild("gender")){
                        String genderr = dataSnapshot.child("gender").getValue().toString();
                        setupGender.setText(genderr);
                    }
                    if (dataSnapshot.hasChild("dob")){
                        String d_o_b = dataSnapshot.child("dob").getValue().toString();
                        setupDob.setText(d_o_b);
                    }
                    else {
                        Toast.makeText(SetupActivityActivity.this, "Error in loading data", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void initToolbar() {
        mtoolbar =findViewById(R.id.setup_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Setup Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void initAll() {
        setup_image=findViewById(R.id.setup_profile_image);
        changeBtn=findViewById(R.id.change_imageBtn);
        setupSaveBtn=findViewById(R.id.setup_saveBtn);
        setupName=findViewById(R.id.setup_profile_username);
        setupEmail=findViewById(R.id.setup_profile_email);
        setupCountry=findViewById(R.id.setup_profile_country);
        setupGender=findViewById(R.id.setup_profile_gender);
        setupDob=findViewById(R.id.setup_profile_dob);
        progressDialog=new ProgressDialog(this);
    }

    private void initDatabaseSetup() {

        String name, country,gender,dob;
        name=setupName.getText().toString();
        country=setupCountry.getText().toString();
        gender=setupCountry.getText().toString();
        dob=setupDob.getText().toString();

        if (TextUtils.isEmpty(name)&&TextUtils.isEmpty(country)&&TextUtils.isEmpty(gender)&&TextUtils.isEmpty(dob)){


        }else {
            Toast.makeText(this, "Empty in field", Toast.LENGTH_SHORT).show();
        }
    }




    private void setupProfile_image() {
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent galleryintent= new Intent();
                galleryintent.setAction(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(Intent.createChooser(galleryintent,"Select Image"),Gallery_Pick);*/

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(SetupActivityActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       /* if (requestCode==GALLERYREQUEST && resultCode==RESULT_OK && data!=null){
           // Uri image_uri=data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }*/

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                setup_image.setImageURI(resultUri);
            }

        }


    }

    private void SaveAllData() {
        setupSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullname, country,gender,dob,email;
                progressDialog.setTitle("Saving Data");
                progressDialog.setMessage("Please wait! while We save your data");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                fullname = setupName.getText().toString();
                email=setupEmail.getText().toString();
                country = setupCountry.getText().toString();
                dob = setupDob.getText().toString();
                gender=setupCountry.getText().toString();


                final StorageReference filepath = mProfileStorage.child(mAuth.getUid()).child(mAuth.getUid() + ".jpg");
                final UploadTask uploadTask = filepath.putFile(resultUri);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                })
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        if (!task.isSuccessful()) {
                                            throw task.getException();
                                        }
                                        return filepath.getDownloadUrl();

                                    }
                                })
                                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                if (task.isSuccessful()) {
                                                    String downloadurl = task.getResult().toString();

                                                    HashMap userMap = new HashMap<>();
                                                    userMap.put("fullname", fullname);
                                                    userMap.put("image", downloadurl);
                                                    userMap.put("thumb_image", "default");
                                                    userMap.put("email",email );
                                                    userMap.put("gender", gender);
                                                    userMap.put("country", country);
                                                    userMap.put("dob", dob);

                                                    mUserDatabase.child(mAuth.getUid()).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                sendToMain();
                                                                progressDialog.dismiss();
                                                            }
                                                        }
                                                    });

                                                } else {
                                                    String error = task.getException().getMessage();
                                                    Toast.makeText(SetupActivityActivity.this, error, Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();
                                                }
                                            }
                                        });

                            }
                        });


            }
        });
    }

    private void sendToMain() {
        Intent startIntent = new Intent(SetupActivityActivity.this, MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
        finish();
    }

    private void initDatabaseRetrieve() {
    }
}
