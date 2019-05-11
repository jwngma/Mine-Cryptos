package com.store.minecryps.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.store.minecryps.Authentication.AuthenticationActivity;
import com.store.minecryps.R;

public class StartActivity extends AppCompatActivity {
    private static final String TAG = "StartActivity";
    private Button log_gmailBtn, log_phoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initBtns();
    }

    private void initBtns() {
        log_gmailBtn=findViewById(R.id.login_with_gmail);
        log_gmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailInetnet=new Intent(StartActivity.this, AuthenticationActivity.class);
                startActivity(emailInetnet);
            }
        });
        log_phoneBtn=findViewById(R.id.login_with_phone);
        log_phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneInetnet=new Intent(StartActivity.this, AuthenticationActivity.class);
                startActivity(phoneInetnet);

            }
        });
    }
}
