package com.example.cango;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.TimeUnit;

public class signin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText edtOTP, edtPwd;
    private EditText num;
    DatabaseReference reference;
    private String verificationId;
    private Button verifyOTPBtn, generateOTPBtn;
    private static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signin);


        FirebaseApp.initializeApp(/*context=*/ this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());


        mAuth = FirebaseAuth.getInstance();
        String no = getIntent().getStringExtra("keyNo");
        // initializing variables for button and Edittext.
        edtOTP = findViewById(R.id.editTextNumber2);
        num = findViewById(R.id.editTextPhone2);
        num.setText(no);
        generateOTPBtn = findViewById(R.id.otpverify1);

        verifyOTPBtn = findViewById(R.id.otpverify);



            verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(edtOTP.getText().toString())) {
                        // if the OTP text field is empty display
                        // a message to user to enter OTP

                        Toast.makeText(signin.this, "emphtyyyyyyyyyyy", Toast.LENGTH_SHORT).show();
                    } else {
                        // if OTP field is not empty calling
                        // method to verify the OTP.
//                        verifyCode(edtOTP.getText().toString());
                        Intent extraIntent = new Intent(signin.this, studentReq.class);
                        extraIntent.putExtra("keyNo", no);
                        startActivity(extraIntent);
                        finish();
                    }
                }
            });




            generateOTPBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
//                loadingDialog.startLoadingDialog();
                    String phone = "+91" + no;
                    Toast.makeText(signin.this, "var", Toast.LENGTH_SHORT).show();
                    Toast.makeText(signin.this, "phone", Toast.LENGTH_SHORT).show();
                    sendVerificationCode(phone);
//                loadingDialog.dismissDialog();

                }

            });



    }


//
private void signInWithCredential(PhoneAuthCredential credential) {
    // inside this method we are checking if
    // the code entered is correct or not.
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // if the code is correct and the task is successful
                        // we are sending our user to new activity.
                        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("flag", "true");
                        editor.apply();


                        String no = getIntent().getStringExtra("keyNo");
                        Intent extraIntent = new Intent(signin.this, studentReq.class);
                        extraIntent.putExtra("keyNo", no);
                        startActivity(extraIntent);
                        finish();
                    } else {
                        // if the code is not correct then we are
                        // displaying an error message to the user.
                        Toast.makeText(signin.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
}


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        generateOTPBtn.setText("Sent");
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                edtOTP.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(signin.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
}