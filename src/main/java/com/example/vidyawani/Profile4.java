package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile4 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    TextView name,email,phno,pass;
    Button b1;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile4);


        name=(TextView)findViewById(R.id.name11);
        email=(TextView)findViewById(R.id.email11);
        phno=(TextView)findViewById(R.id.phno11);
        pass=(TextView)findViewById(R.id.pass11);



         i=getIntent();
        name.setText("Name :- "+i.getStringExtra("name"));
        email.setText("Email :- "+i.getStringExtra("email"));
        pass.setText("Password :-"+i.getStringExtra("pass"));
        phno.setText("Phone Number :-"+i.getStringExtra("phno"));



        b1=(Button)findViewById(R.id.disp11);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile4.this, Profile.class);
                intent.putExtra("name",i.getStringExtra("name"));
                intent.putExtra("email",i.getStringExtra("email"));
                intent.putExtra("pass",i.getStringExtra("pass"));
                intent.putExtra("phno",i.getStringExtra("phno"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finishActivity(0);
                startActivity(intent);
            }
        });
    }
}
