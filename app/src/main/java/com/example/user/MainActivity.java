package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    GridLayout custGridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custGridLayout=findViewById(R.id.usersidegrid);
        setSingleEvent(custGridLayout);

    }

    private void setSingleEvent(GridLayout custGridLayout) {
        for(int i=0;i<custGridLayout.getChildCount();i++)
        {
            CardView cardView=(CardView)custGridLayout.getChildAt(i);
            final int finali=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finali==0)
                    {
                        startActivity(new Intent(MainActivity.this,profile.class));
                    }
                    else if(finali==1)
                    {
                        startActivity(new Intent(MainActivity.this,bluetooth.class));
                    }
                    else if(finali==2)
                    {
                        startActivity(new Intent(MainActivity.this,payment.class));
                    }
                    else if(finali==3)
                    {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this,Login.class));
                    }
                }
            });
        }
    }
}