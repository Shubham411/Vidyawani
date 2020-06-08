package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dashboard extends AppCompatActivity {

    ImageView programs,profile,posts,notices;
    BottomNavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        programs=(ImageView)findViewById(R.id.Dprograms);
        profile=(ImageView)findViewById(R.id.Dprofile);
        posts=(ImageView)findViewById(R.id.Dposts);
        notices=(ImageView)findViewById(R.id.Devents);

        nv=(BottomNavigationView) findViewById(R.id.bottom_navigation);
        nv.setSelectedItemId(R.id.Dashboard);


        nv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        Intent ii=new Intent(Dashboard.this,MainActivity.class);
                        startActivity(ii);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        Intent i=new Intent(Dashboard.this,Profile.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.Dashboard:
                        return  true;
                }
                return false;
            }
        });


        programs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),User_Programs.class));
                Toast.makeText(getApplicationContext(), "Programs", Toast.LENGTH_SHORT).show();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),User_Feedback.class));
            }
        });

        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Posts", Toast.LENGTH_SHORT).show();
            }
        });

        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),User_Events.class));
                Toast.makeText(getApplicationContext(), "Events", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
