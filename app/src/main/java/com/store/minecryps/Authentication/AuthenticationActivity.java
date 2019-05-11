package com.store.minecryps.Authentication;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.store.minecryps.Authentication.Utils.CountryData;
import com.store.minecryps.R;

import java.util.List;

public class AuthenticationActivity extends AppCompatActivity {
    private static final String TAG = "AuthenticationActivity";
    private Spinner spinnerCountries;
    private EditText edt_mobile;
    private Button getCodeBtn;
    private Button SelectPhoneNumber;
    private TextView detectBtn;

    //
    private static final int PHONE_NUMBER_HINT = 100;
    private final int PERMISSION_REQ_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        edt_mobile = findViewById(R.id.editTextMobile);
        detectBtn=findViewById(R.id.detectBtn);

        spinnerCountries = findViewById(R.id.spinner_countries);
        spinnerCountries.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        getCodeBtn = findViewById(R.id.getCodeBtn);
        getCodeBtn.setEnabled(false);
        initGETphoneNumber();
        edt_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_mobile.getText().length()==13){
                    getCodeBtn.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final HintRequest hintRequest =
                        new HintRequest.Builder().setPhoneNumberIdentifierSupported(true).build();
                try {
                    final GoogleApiClient googleApiClient =
                            new GoogleApiClient.Builder(AuthenticationActivity.this).addApi(Auth.CREDENTIALS_API).build();

                    final PendingIntent pendingIntent =
                            Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
                    startIntentSenderForResult(pendingIntent.getIntentSender(), PHONE_NUMBER_HINT, null, 0, 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.country_codes[spinnerCountries.getSelectedItemPosition()];
                String number = edt_mobile.getText().toString().trim();


                if (TextUtils.isEmpty(number) || number.length() < 10) {
                    edt_mobile.setError(" Valid Number is required");
                    edt_mobile.requestFocus();
                    Toast.makeText(AuthenticationActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // String phone_number="+"+code+number;
                String phone_number = number;

                Intent intent = new Intent(AuthenticationActivity.this, VerifyActivity.class);
                intent.putExtra("phone_number", phone_number);
                startActivity(intent);


            }
        });
    }

    private void initGETphoneNumber() {
        final HintRequest hintRequest =
                new HintRequest.Builder().setPhoneNumberIdentifierSupported(true).build();
        try {
            final GoogleApiClient googleApiClient =
                    new GoogleApiClient.Builder(AuthenticationActivity.this).addApi(Auth.CREDENTIALS_API).build();

            final PendingIntent pendingIntent =
                    Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
            startIntentSenderForResult(pendingIntent.getIntentSender(), PHONE_NUMBER_HINT, null, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_NUMBER_HINT && resultCode == RESULT_OK) {
            Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
            final String phoneNumber = credential.getId();
            Toast.makeText(this, "Phone number" + phoneNumber, Toast.LENGTH_SHORT).show();
            edt_mobile.setText(phoneNumber);
        }
    }
}
