package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setNewRate extends AppCompatActivity {

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root=db.getReference().child("Rate");
    Button setratebtn;
    EditText rateip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_rate);
        setratebtn=findViewById(R.id.setratebutton);
        rateip=findViewById(R.id.newrateip);
        setratebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip=rateip.getText().toString();
                root.setValue(ip);
            }
        });
    }
}