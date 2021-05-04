package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class OwnerHome extends AppCompatActivity {
    private Button setRate,addcustomer,custinfo,logout;
    GridLayout ownergridlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);
        ownergridlayout=(GridLayout)findViewById(R.id.ownersidegrid);
        setSingleEvent(ownergridlayout);
    }

    private void setSingleEvent(GridLayout ownergridlayout) {
        for(int i=0;i<ownergridlayout.getChildCount();i++)
        {
            CardView cardView=(CardView)ownergridlayout.getChildAt(i);
            final int finali=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finali==0)
                    {
                        startActivity(new Intent(OwnerHome.this,setNewRate.class));
                    }
                    else if(finali==1)
                    {
                        startActivity(new Intent(OwnerHome.this,Registration.class));
                    }
                    else if(finali==2)
                    {
                        startActivity(new Intent(OwnerHome.this,CustomerInformation.class));
                    }
                    else if(finali==3)
                    {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(OwnerHome.this,Login.class));
                    }
                }
            });
        }
    }
}