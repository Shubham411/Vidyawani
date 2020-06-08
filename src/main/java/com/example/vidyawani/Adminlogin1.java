package com.example.vidyawani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminlogin1 extends AppCompatActivity {

    Button signin;
    EditText id,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin1);


        id=(EditText)findViewById(R.id.adminid);
        pass=(EditText)findViewById(R.id.pass);
        signin=(Button)findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1=id.getText().toString();
                String pass1=pass.getText().toString();
                if((id1.equals("Shubham"))&&(pass1.equals("Hajare")))
                {
                    Intent i=new Intent(getApplicationContext(),Adminlogin2.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Adminlogin1.this, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
