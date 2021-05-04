package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editprofile extends AppCompatActivity {
    EditText curmobnum, newmobnum, newpass1;
    Button updatebtn1, passresetbtn;
    FirebaseAuth mAuth2;
    public FirebaseUser user1;
    FirebaseDatabase db3 = FirebaseDatabase.getInstance();
    DatabaseReference root3 = db3.getReference().child("Users");
    private TextView gobacktologin;
    // DatabaseReference root2=db1.getReference().child("Rate");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        updatebtn1=findViewById(R.id.updatebtn);
        gobacktologin=(TextView)findViewById(R.id.gobackeditprofile);
        curmobnum = findViewById(R.id.curr_mob_num);
        newmobnum = findViewById(R.id.new_mob_num);
        newpass1 = findViewById(R.id.newpass);
        mAuth2 = FirebaseAuth.getInstance();
        user1 = FirebaseAuth.getInstance().getCurrentUser();
        String userid1 = user1.getUid();

        gobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(editprofile.this,profile.class));
            }
        });
        passresetbtn = findViewById(R.id.resetpass);

            root3.child(userid1).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User uprofile1 = dataSnapshot.getValue(User.class);
                    if (uprofile1 != null) {
                        String displaycurrmob = uprofile1.mno;
                        curmobnum.setText(displaycurrmob);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

         updatebtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference root4=db3.getReference().child("Users").child(userid1);
                    String s=newmobnum.getText().toString();
                    root4.child("mno").setValue(s);
                    Toast.makeText(editprofile.this, "Mobile Number Updated", Toast.LENGTH_SHORT).show();
                }
            });


        passresetbtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String ip1 = newpass1.getText().toString();

                user1.updatePassword(ip1);
                Toast.makeText(editprofile.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                newpass1.setText("");
            }

        });
//kkk
    }
}



