package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Total_Users extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total__users);
        final TableLayout tl = (TableLayout) findViewById(R.id.user_table);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer count = 0;
                tl.removeAllViews();
                TableRow tr_head = new TableRow(getApplicationContext());
                tr_head.setId(Integer.parseInt("10"));
                tr_head.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                tr_head.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tr_head.setLayoutParams(new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));


                TextView label_date = new TextView(getApplicationContext());
                label_date.setTextSize(25);
                label_date.setGravity(Gravity.CENTER_HORIZONTAL);
                label_date.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_date.setId(Integer.parseInt("20"));
                label_date.setText("Total Users");
                label_date.setTextColor(Color.BLACK);
                label_date.setPadding(5, 5, 5, 5);
                tr_head.addView(label_date);// add the column to the table row here



                tl.addView(tr_head, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    databaseReference.child(key);

                    Users u= dataSnapshot.child(key).getValue(Users.class);


                    String uname = u.name;
                    String umail = u.mail;
                    String uphno = u.phno;
// Create the table row

                    TableRow tr = new TableRow(getApplicationContext());
                    tr.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    if (count % 2 != 0)
                        tr.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    tr.setId(100 + count);
                    tr.setLayoutParams(new TableLayout.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

//Create two columns to add as table data
                    // Create a TextView to add date
                    TextView dynamic_pno = new TextView(getApplicationContext());
                    dynamic_pno.setGravity(Gravity.LEFT);
                    dynamic_pno.setTextSize(18);
                    dynamic_pno.setPadding(5,5,5,5);
                    dynamic_pno.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_pno.setId(count + 200);
                    dynamic_pno.setText("Name = "+uname+"\nEmail = "+umail+"\nContact = "+uphno);
                    dynamic_pno.setTextColor(Color.BLACK);
                    dynamic_pno.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_pno);// add the column to the table row here



// finally add this to the table row
                    tl.addView(tr, new TableLayout.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    count++;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
