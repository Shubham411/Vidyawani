package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile2 extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText name,email,pass,phno,repass;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        name=(EditText) findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        repass=(EditText)findViewById(R.id.repass);
        phno=(EditText)findViewById(R.id.phno);
        b1=(Button)findViewById(R.id.signin);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (isValidName())
                {
                    name.setError("this field is Compulsory");
                }

                else if (isValidContact())
                {
                    phno.setError("Enter Valid Contact Number");
                }

                else  if (isValidEmail())
                {
                    email.setError("Please Enter Valid E-Mail");
                }

                else  if (isValidPass())
                {
                    repass.setError("Password Mismatch");
                }


                else {

                    String mobile = phno.getText().toString();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mobile);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                        if((dataSnapshot.child("name").getValue().toString())==null){
                            String name1 = name.getText().toString();
                            String email1 = email.getText().toString();
                            String pass1 = pass.getText().toString();
                            String phno1 = phno.getText().toString();
                            Intent i = new Intent(Profile2.this, Profile3.class);
                            i.putExtra("name", name1);
                            i.putExtra("email", email1);
                            i.putExtra("pass", pass1);
                            i.putExtra("phno", phno1);

                            startActivity(i);

                            //                      }
                            //                    else
                            //                      Toast.makeText(Profile2.this, "Phone Number already Used", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });


    }

    private boolean isValidPass() {
        if(!(pass.getText().toString().equals(repass.getText().toString())) || (TextUtils.isEmpty(pass.getText().toString()))) {
            return true;
        }
        return false;
    }

    private boolean isValidContact() {
        String MobilePattern = "[0-9]{10}";
        if(!phno.getText().toString().matches(MobilePattern) || (TextUtils.isEmpty(phno.getText().toString()))) {
            return true;
        }
        return false;
    }

    private boolean isValidName() {
        if((TextUtils.isEmpty(name.getText().toString()))){
            return true;
        }
        return false;
    }

    private boolean isValidEmail() {
        if((TextUtils.isEmpty(email.getText().toString())) || !(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())){
            return true;
        }
        return false;
    }
}
