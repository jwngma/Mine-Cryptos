package com.store.minecryps.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.store.minecryps.R;

public class SignOutActivity extends AppCompatActivity {
    private static final String TAG = "SignOutActivity";

    private FirebaseAuth mAuth;
    private Button signOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);
        mAuth = FirebaseAuth.getInstance();
        initSignOut();
    }

    private void initSignOut() {
        signOutBtn = findViewById(R.id.sign_out);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SignOutActivity.this);
                builder.setTitle("Sign Out");
                builder.setMessage("Are You Sure ?");
                builder.setPositiveButton(" Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        Intent signOutIntent = new Intent(SignOutActivity.this, StartActivity.class);
                        startActivity(signOutIntent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });


    }
}
