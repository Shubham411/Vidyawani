package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Profile3 extends AppCompatActivity {

    private String mVid;
    EditText editText;
    Button verify;
    String name,email,pass,phno;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile3);

        mAuth=FirebaseAuth.getInstance();
        editText=(EditText)findViewById(R.id.editTextCode);
        verify=(Button)findViewById(R.id.verify);

        Intent i=getIntent();
        name=i.getStringExtra("name");
        email=i.getStringExtra("email");
        pass=i.getStringExtra("pass");
        phno=i.getStringExtra("phno");

        String mobile=i.getStringExtra("phno");
        sendVerificationCode(mobile);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=editText.getText().toString();
                verifyVerificationCode(code);
                finishActivity(0);
            }
        });


    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBacks);
    }

    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(mVid,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(Profile3.this, "OTP Verified", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(Profile3.this, Profile4.class);
                    intent.putExtra("mobile",phno);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("pass",pass);
                    intent.putExtra("phno",phno);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finishActivity(0);
                    startActivity(intent);

                }
                else
                    Toast.makeText(Profile3.this, "OTP Not Verifiedddd...!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            finishActivity(0);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Profile3.this, "Error"+e, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            mVid=s;
        }
    };

}
