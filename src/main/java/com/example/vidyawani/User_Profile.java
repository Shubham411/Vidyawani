package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Profile extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView name,email,phno,pass,heading;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        name=(TextView)findViewById(R.id.username);
        email=(TextView)findViewById(R.id.useremail);
        phno=(TextView)findViewById(R.id.userphno);
        pass=(TextView)findViewById(R.id.userpass);
        logout=(Button)findViewById(R.id.logout);
        heading=(TextView) findViewById(R.id.heading);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                String temp=user.getDisplayName();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String key = ds.getKey();
                    databaseReference.child(key);
                    Users u= dataSnapshot.child(key).getValue(Users.class);
                    if(temp.equals(u.phno))
                    {
                        heading.setText("Welcome " +u.name);
                        name.setText("Name :- "+u.name);
                        email.setText("Email :- "+u.mail);
                        pass.setText("Password :-"+u.pass);
                        phno.setText("Phone Number :-"+u.phno);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest request=new UserProfileChangeRequest.Builder().setDisplayName("null").build();
                user.updateProfile(request)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(getApplicationContext(), "Logout Succsful..!", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),Profile.class);
                                startActivity(i);
                            }

                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
