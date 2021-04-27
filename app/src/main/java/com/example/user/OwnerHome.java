package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerHome extends AppCompatActivity {
    private Button setRate,addcustomer,custinfo,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);
        setRate=(Button)findViewById(R.id.homeSetRatebtn);
        addcustomer=(Button)findViewById(R.id.homeAddCustomerbtn);
        custinfo=(Button)findViewById(R.id.homeCustomerInfobtn);
        logout=(Button)findViewById(R.id.homeLogoutbtn);
        setRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerHome.this,setNewRate.class));
            }
        });
        addcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerHome.this,Registration.class));
            }
        });
        custinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerHome.this,CustomerInformation.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerHome.this,Login.class));
            }
        });
    }
}