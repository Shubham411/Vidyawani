package com.example.vidyawani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.dankito.richtexteditor.android.RichTextEditor;

public class User_Feedback extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText editor;
    Button b;
    RatingBar ratingBar;
    TextView Avg_star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__feedback);

        b=(Button)findViewById(R.id.sendfeedback);
        editor=(EditText) findViewById(R.id.editor);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        Avg_star=(TextView)findViewById(R.id.avg_rating);
        final TableLayout tl = (TableLayout) findViewById(R.id.feedback_table);


        databaseReference = FirebaseDatabase.getInstance().getReference("Feedbacks");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer count = 0;
                float sum=0;
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
                label_date.setGravity(Gravity.LEFT);
                label_date.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                label_date.setId(Integer.parseInt("20"));
                label_date.setText("Feedbacks ");
                label_date.setTextColor(Color.BLACK);
                label_date.setPadding(5, 5, 5, 5);
                tr_head.addView(label_date);// add the column to the table row here



                tl.addView(tr_head, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    databaseReference.child(key);

                    Feedbacks f=dataSnapshot.child(key).getValue(Feedbacks.class);


                    String fname = f.fname;
                    String fdesc = f.fdesc;
                    float frating=f.star;
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
                    dynamic_pno.setText(" "+fname+"\n    "+fdesc+"\n    Star Rating = "+frating);
                    dynamic_pno.setTextColor(Color.BLACK);
                    dynamic_pno.setPadding(5, 5, 5, 5);
                    tr.addView(dynamic_pno);// add the column to the table row here



// finally add this to the table row
                    tl.addView(tr, new TableLayout.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    count++;
                    sum=sum+frating;


                }

                Avg_star.setText("Average Rating = "+(float)(sum/count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(User_Feedback.this, ""+databaseError, Toast.LENGTH_SHORT).show();
            }
        });



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user.getDisplayName().equals("null")){
                    Toast.makeText(User_Feedback.this, "User Not Logged in", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference= FirebaseDatabase.getInstance().getReference("Feedbacks");
                    Feedbacks f=new Feedbacks(user.getDisplayName().toString(),editor.getText().toString(),ratingBar.getRating());
                    databaseReference.child(databaseReference.push().getKey()).setValue(f);
                    Toast.makeText(User_Feedback.this, "Feedback Send Succesfully..!  ", Toast.LENGTH_SHORT).show();
                    editor.setText("");
                    ratingBar.setRating(0);
                }

            }
        });

    }
}
