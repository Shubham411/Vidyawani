package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView adminlogin;
    EditText mobile,pass;
    Button signup,signin;
    BottomNavigationView nv;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mobile=(EditText)findViewById(R.id.mobile);
        pass=(EditText)findViewById(R.id.pass);
        signup=(Button)findViewById(R.id.signup);
        signin=(Button)findViewById(R.id.signin);
        adminlogin=(TextView)findViewById(R.id.adminlogin);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        nv=(BottomNavigationView) findViewById(R.id.bottom_navigation);
        nv.setSelectedItemId(R.id.profile);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(!user.getDisplayName().equals("null")){
            Intent i=new Intent(getApplicationContext(),User_Profile.class);
            startActivity(i);
        }

        nv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        Intent ii=new Intent(Profile.this,MainActivity.class);
                        overridePendingTransition(0,0);
                        startActivity(ii);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:return  true;
                    case R.id.Dashboard:
                        Intent i=new Intent(Profile.this,Dashboard.class);
                        overridePendingTransition(0,0);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mobile1=mobile.getText().toString();
                final String pass1=pass.getText().toString();
                databaseReference= FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int f=0;
                        for (DataSnapshot ds: dataSnapshot.getChildren())
                        {
                            String key = ds.getKey();
                            databaseReference.child(key);

                           Users u= dataSnapshot.child(key).getValue(Users.class);

                           if((mobile1.equals(u.phno))&&(pass1.equals(u.pass))){
                                f=1;
                               final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                               UserProfileChangeRequest request=new UserProfileChangeRequest.Builder().setDisplayName(u.phno).build();
                               user.updateProfile(request)
                                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void aVoid) {

                                            //   Toast.makeText(getApplicationContext(), ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                                           }

                                       })
                                       .addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull Exception e) {

                                           }
                                       });



                               Intent i=new Intent(getApplicationContext(),Profile5.class);
                               startActivity(i);
                           }

                        }
                        if(f==0)
                        Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Profile.this,Profile2.class);
                startActivity(i);
            }
        });



        if((getIntent().getStringExtra("name"))!=null)
        {
            Intent i=getIntent();
            databaseReference=FirebaseDatabase.getInstance().getReference("Users");
            Users u=new Users(i.getStringExtra("name"),i.getStringExtra("email"),i.getStringExtra("phno"),i.getStringExtra("pass"));
            databaseReference.child(databaseReference.push().getKey()).setValue(u);
        }

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),Adminlogin1.class);
               startActivity(i);
            }
        });
    }
}
