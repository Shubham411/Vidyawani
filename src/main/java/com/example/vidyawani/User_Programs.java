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

public class User_Programs extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__programs);

        final TableLayout tl = (TableLayout) findViewById(R.id.main_table2);


        databaseReference = FirebaseDatabase.getInstance().getReference("University_Programs");

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
                label_date.setGravity(Gravity.CENTER_HORIZONTAL);
                label_date.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_date.setId(Integer.parseInt("20"));
                label_date.setText("  Program No.  ");
                label_date.setTextColor(Color.BLACK);
                label_date.setPadding(5, 5, 5, 5);
                tr_head.addView(label_date);// add the column to the table row here

                TextView label_weight_kg = new TextView(getApplicationContext());
                label_weight_kg.setGravity(Gravity.CENTER_HORIZONTAL);
                label_weight_kg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_weight_kg.setId(Integer.parseInt("21"));// define id that must be unique
                label_weight_kg.setText("  Program Name  "); // set the text for the header
                label_weight_kg.setTextColor(Color.BLACK); // set the color
                label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
                tr_head.addView(label_weight_kg); // add the column to the table row here

                TextView label_stime = new TextView(getApplicationContext());
                label_stime.setGravity(Gravity.CENTER_HORIZONTAL);
                label_stime.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_stime.setId(Integer.parseInt("20"));
                label_stime.setText("  Start Time  ");
                label_stime.setTextColor(Color.BLACK);
                label_stime.setPadding(5, 5, 5, 5);
                tr_head.addView(label_stime);// add the column to the table row here

                TextView label_etime = new TextView(getApplicationContext());
                label_etime.setGravity(Gravity.CENTER_HORIZONTAL);
                label_etime.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_etime.setId(Integer.parseInt("20"));
                label_etime.setText("  End Time  ");
                label_etime.setTextColor(Color.BLACK);
                label_etime.setPadding(5, 5, 5, 5);
                tr_head.addView(label_etime);// add the column to the table row here

                TextView label_desc = new TextView(getApplicationContext());
                label_desc.setGravity(Gravity.CENTER_HORIZONTAL);
                label_desc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_desc.setId(Integer.parseInt("21"));// define id that must be unique
                label_desc.setText("  Program Description  "); // set the text for the header
                label_desc.setTextColor(Color.BLACK); // set the color
                label_desc.setPadding(5, 5, 5, 5); // set the padding (if required)
                tr_head.addView(label_desc); // add the column to the table row here


                tl.addView(tr_head, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    databaseReference.child(key);

                    University_Programs up = dataSnapshot.child(key).getValue(University_Programs.class);

                    String pno = up.id + "";
                    String pname = up.name;
                    String stime = up.stime;
                    String etime = up.etime;
                    String desc = up.desc;
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
                    dynamic_pno.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_pno.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_pno.setId(count + 200);
                    dynamic_pno.setText(pno);
                    dynamic_pno.setTextColor(Color.BLACK);
                    dynamic_pno.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_pno);// add the column to the table row here

                    TextView dynamic_pname = new TextView(getApplicationContext());
                    dynamic_pname.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_pname.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_pname.setId(count + 200);
                    dynamic_pname.setText(pname);
                    dynamic_pname.setTextColor(Color.BLACK);
                    dynamic_pname.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_pname);// add the column to the table row here

                    TextView dynamic_stime = new TextView(getApplicationContext());
                    dynamic_stime.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_stime.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_stime.setId(count + 200);
                    dynamic_stime.setText(stime);
                    dynamic_stime.setTextColor(Color.BLACK);
                    dynamic_stime.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_stime);// add the column to the table row here

                    TextView dynamic_etime = new TextView(getApplicationContext());
                    dynamic_etime.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_etime.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_etime.setId(count + 200);
                    dynamic_etime.setText(etime);
                    dynamic_etime.setTextColor(Color.BLACK);
                    dynamic_etime.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_etime);// add the column to the table row here

                    TextView dynamic_desc = new TextView(getApplicationContext());
                    dynamic_desc.setGravity(Gravity.LEFT);
                    dynamic_desc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_desc.setId(count + 200);
                    dynamic_desc.setText(desc);
                    dynamic_desc.setTextColor(Color.BLACK);
                    dynamic_desc.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_desc);// add the column to the table row here


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
