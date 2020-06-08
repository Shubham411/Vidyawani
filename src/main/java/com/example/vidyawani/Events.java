package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Events extends AppCompatActivity {

    Button add,del;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        add=(Button)findViewById(R.id.event_dialouge);
        del=(Button)findViewById(R.id.delete_event);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Events.this);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDialog1(Events.this);
            }
        });


        final TableLayout tl = (TableLayout) findViewById(R.id.event_table);

        databaseReference= FirebaseDatabase.getInstance().getReference("University_Events");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer count=0;
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
                label_date.setTextSize(15);
                label_date.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_date.setId(Integer.parseInt("20"));
                label_date.setText("  Event No.  ");
                label_date.setTextColor(Color.BLACK);
                label_date.setPadding(5, 5, 5, 5);
                tr_head.addView(label_date);// add the column to the table row here

                TextView label_weight_kg = new TextView(getApplicationContext());
                label_weight_kg.setTextSize(15);
                label_weight_kg.setGravity(Gravity.CENTER_HORIZONTAL);
                label_weight_kg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_weight_kg.setId(Integer.parseInt("21"));// define id that must be unique
                label_weight_kg.setText("  Event Name  "); // set the text for the header
                label_weight_kg.setTextColor(Color.BLACK); // set the color
                label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
                tr_head.addView(label_weight_kg); // add the column to the table row here

                TextView label_time = new TextView(getApplicationContext());
                label_time.setTextSize(15);
                label_time.setGravity(Gravity.CENTER_HORIZONTAL);
                label_time.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_time.setId(Integer.parseInt("21"));// define id that must be unique
                label_time.setText("  Event Time  "); // set the text for the header
                label_time.setTextColor(Color.BLACK); // set the color
                label_time.setPadding(5, 5, 5, 5); // set the padding (if required)
                tr_head.addView(label_time); // add the column to the table row here

                TextView label_loc = new TextView(getApplicationContext());
                label_loc.setTextSize(15);
                label_loc.setGravity(Gravity.CENTER_HORIZONTAL);
                label_loc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_loc.setId(Integer.parseInt("21"));// define id that must be unique
                label_loc.setText("  Event Location  "); // set the text for the header
                label_loc.setTextColor(Color.BLACK); // set the color
                label_loc.setPadding(5, 5, 5, 5); // set the padding (if required)
                tr_head.addView(label_loc); // add the column to the table row here

                TextView label_stime = new TextView(getApplicationContext());
                label_stime.setGravity(Gravity.CENTER_HORIZONTAL);
                label_stime.setTextSize(15);
                label_stime.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_stime.setId(Integer.parseInt("20"));
                label_stime.setText("  Event Description  ");
                label_stime.setTextColor(Color.BLACK);
                label_stime.setPadding(5, 5, 5, 5);
                tr_head.addView(label_stime);// add the column to the table row here



                tl.addView(tr_head, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String key = ds.getKey();
                    databaseReference.child(key);

                    University_Events ue = dataSnapshot.child(key).getValue(University_Events.class);

                    String no=ue.id;
                    String name=ue.name;
                    String time=ue.time;
                    String loc=ue.loc;
                    String desc=ue.desc;
// Create the table row

                    TableRow tr = new TableRow(getApplicationContext());
                    tr.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    if(count%2!=0) tr.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    tr.setId(100+count);
                    tr.setLayoutParams(new TableLayout.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

//Create two columns to add as table data
                    // Create a TextView to add date
                    TextView dynamic_pno = new TextView(getApplicationContext());
                    dynamic_pno.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_pno.setTextSize(15);
                    dynamic_pno.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_pno.setId(count+200);
                    dynamic_pno.setText(no);
                    dynamic_pno.setTextColor(Color.BLACK);
                    dynamic_pno.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_pno);// add the column to the table row here

                    TextView dynamic_pname = new TextView(getApplicationContext());
                    dynamic_pname.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_pname.setTextSize(15);
                    dynamic_pname.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_pname.setId(count+200);
                    dynamic_pname.setText(name);
                    dynamic_pname.setTextColor(Color.BLACK);
                    dynamic_pname.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_pname);// add the column to the table row here

                    TextView dynamic_time = new TextView(getApplicationContext());
                    dynamic_time.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_time.setTextSize(15);
                    dynamic_time.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_time.setId(count+200);
                    dynamic_time.setText(time);
                    dynamic_time.setTextColor(Color.BLACK);
                    dynamic_time.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_time);// add the column to the table row here

                    TextView dynamic_loc = new TextView(getApplicationContext());
                    dynamic_loc.setGravity(Gravity.CENTER_HORIZONTAL);
                    dynamic_loc.setTextSize(15);
                    dynamic_loc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_loc.setId(count+200);
                    dynamic_loc.setText(loc);
                    dynamic_loc.setTextColor(Color.BLACK);
                    dynamic_loc.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_loc);// add the column to the table row here


                    TextView dynamic_desc = new TextView(getApplicationContext());
                    dynamic_desc.setGravity(Gravity.LEFT);
                    dynamic_desc.setTextSize(15);
                    dynamic_desc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                    dynamic_desc.setId(count+200);
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

    private void showDialog1(Events events) {
        final Dialog dialog = new Dialog(events);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.delete_event);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button delete,cancle;
        final EditText id;

        id=(EditText)dialog.findViewById(R.id.delete_eno);
        delete=(Button)dialog.findViewById(R.id.deletebtn1);
        cancle=(Button)dialog.findViewById(R.id.cancle1);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1= id.getText().toString();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("University_Events").orderByChild("id").equalTo(id1);

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), ""+databaseError, Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(getApplicationContext(), "Deleted Succesfully....!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();



    }

    private void showDialog(Events events) {
        final Dialog dialog = new Dialog(events);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.event_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button ok,cancle;
        final EditText id,name,desc,time,loc;

        id=(EditText)dialog.findViewById(R.id.eno);
        name=(EditText)dialog.findViewById(R.id.ename);
        desc=(EditText)dialog.findViewById(R.id.edesc);
        time=(EditText)dialog.findViewById(R.id.etime);
        loc=(EditText)dialog.findViewById(R.id.eloc);
        ok=(Button)dialog.findViewById(R.id.eok);
        cancle=(Button)dialog.findViewById(R.id.ecn);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1= id.getText().toString();
                String name1=name.getText().toString();
                String desc1=desc.getText().toString();
                String time1=time.getText().toString();
                String loc1=loc.getText().toString();
                databaseReference= FirebaseDatabase.getInstance().getReference("University_Events");
                University_Events ue=new University_Events(id1,name1,desc1,time1,loc1);
                databaseReference.child(databaseReference.push().getKey()).setValue(ue);
                Toast.makeText(getApplicationContext(), "Event Added ", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
