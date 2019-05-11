package com.store.minecryps.WelccomeScreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.store.minecryps.Activities.MainActivity;
import com.store.minecryps.R;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(WelcomeScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        },2000);


    }
}
