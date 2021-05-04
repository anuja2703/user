package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    FirebaseDatabase db1=FirebaseDatabase.getInstance();
    DatabaseReference root1=db1.getReference().child("Users");
    DatabaseReference root2=db1.getReference().child("Rate");
        Button editprofilebtn;
   FirebaseAuth mAuth1;
       public FirebaseUser user;
        TextView nametext,mobtext,unitstext,lastrechargetext,remainingrechargetext,ratedisplaytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth1 = FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        String userid=user.getUid();
        nametext=findViewById(R.id.profile_name_text);
        mobtext=findViewById(R.id.profilemobnumtext);
        unitstext=findViewById(R.id.unitsconsumeddisplay);
        lastrechargetext=findViewById(R.id.profile_last_recharge_text);
        remainingrechargetext=findViewById(R.id.profile_last_recharge_text);
        ratedisplaytext=findViewById(R.id.profile_rate_text);
        editprofilebtn=findViewById(R.id.edit_profile);

        TextView n1=findViewById(R.id.regNametxt);
        TextView m1=findViewById(R.id.regMobiletxt);


        root1.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User uprofile=dataSnapshot.getValue(User.class);
                if(uprofile!=null)
                {
                    String displayname=uprofile.name;
                    String mobdisplay=uprofile.mno;
                    nametext.setText(displayname);
                    mobtext.setText(mobdisplay);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        root2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String v1=dataSnapshot.getValue(String.class);
                ratedisplaytext.setText(v1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile.this,editprofile.class);
                startActivity(i);
            }
        });
    }





}