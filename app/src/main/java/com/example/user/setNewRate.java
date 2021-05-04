package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class setNewRate extends AppCompatActivity {

   FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root=db.getReference().child("Rate");
    Button setratebtn;
    EditText rateip;
    TextView old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_rate);
        setratebtn=findViewById(R.id.setratebutton);
        rateip=findViewById(R.id.newrateip);
        old=findViewById(R.id.oldrateview);
        setratebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip=rateip.getText().toString();
                root.setValue(ip);

                rateip.setText("");
                Toast.makeText(setNewRate.this, "Rate set Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String v=snapshot.getValue(String.class);
                old.setText(v);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    //tttttt
    }
}