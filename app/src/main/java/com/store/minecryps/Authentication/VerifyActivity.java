package com.store.minecryps.Authentication;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.store.minecryps.Activities.MainActivity;
import com.store.minecryps.R;

import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {
    private static final String TAG = "VerifyActivity";

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    String phone_number;
    private String VerifivationId;
    private Button VerifyBtn;
    private EditText codeEdt;
    private ProgressDialog progressDialog;
    private Button resendBtn;

    //
    private static final int PHONE_NUMBER_HINT = 100;
    private final int PERMISSION_REQ_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        VerifyBtn = findViewById(R.id.verify_Btn);
        VerifyBtn.setEnabled(false);
        resendBtn=findViewById(R.id.resendBtn);

        codeEdt = findViewById(R.id.code_edt);
        codeEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (codeEdt.getText().length()>=6){
                    VerifyBtn.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (getIntent().hasExtra("phone_number")) {
            phone_number = getIntent().getStringExtra("phone_number");

        }
        SendVerificationCode(phone_number);

        VerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeEdt.getText().toString();
                if (code.isEmpty() || code.length() < 6) {
                    codeEdt.setError("Code is not valid");
                    codeEdt.requestFocus();
                    return;
                }
                progressDialog.show();
                verifyCode(code);
            }
        });

       resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HintRequest hintRequest =
                        new HintRequest.Builder().setPhoneNumberIdentifierSupported(true).build();

                try {
                    final GoogleApiClient googleApiClient =
                            new GoogleApiClient.Builder(VerifyActivity.this).addApi(Auth.CREDENTIALS_API).build();

                    final PendingIntent pendingIntent =
                            Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
                    startIntentSenderForResult(pendingIntent.getIntentSender(), PHONE_NUMBER_HINT, null, 0, 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_NUMBER_HINT && resultCode == RESULT_OK) {
            Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
            final String phoneNumber = credential.getId();

        }
    }

    private void SendVerificationCode(String phone_number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone_number,
                60,
                TimeUnit.SECONDS,
               this,
                mCallbacks
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                codeEdt.setText(code);

                verifyCode(code);
            }
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(VerifyActivity.this, phone_number+"is invalid", Toast.LENGTH_SHORT).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(VerifyActivity.this, "Quota exceeded", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerifivationId = s;
            mResendToken=forceResendingToken;
            Toast.makeText(VerifyActivity.this, "code has been send", Toast.LENGTH_SHORT).show();
        }
    };


    private void verifyCode(String code) {
        progressDialog.show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerifivationId, code);
        signInWithCredentials(credential);
    }

    private void signInWithCredentials(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(VerifyActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
}
