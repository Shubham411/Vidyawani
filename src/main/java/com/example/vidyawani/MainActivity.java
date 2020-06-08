package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nv=(BottomNavigationView) findViewById(R.id.bottom_navigation);
        nv.setSelectedItemId(R.id.home);

        nv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:return true;
                    case R.id.profile:
                        Intent i=new Intent(MainActivity.this,Profile.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Dashboard:
                        Intent ii=new Intent(MainActivity.this,Dashboard.class);
                        startActivity(ii);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
}
