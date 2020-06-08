package com.example.vidyawani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Adminlogin2 extends AppCompatActivity {

     ImageView programs,users,posts,feedbacks,notices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin2);

        programs=(ImageView)findViewById(R.id.programs);
        users=(ImageView)findViewById(R.id.users);
        posts=(ImageView)findViewById(R.id.posts);
        feedbacks=(ImageView)findViewById(R.id.feedbacks);
        notices=(ImageView)findViewById(R.id.notices);

        programs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Programs.class);
                startActivity(i);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Total_Users.class));
            }
        });

        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Adminlogin2.this, "Posts", Toast.LENGTH_SHORT).show();
            }
        });

        feedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Adminlogin2.this, "Feedbacks", Toast.LENGTH_SHORT).show();
            }
        });

        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),Events.class));
            }
        });

    }
}
