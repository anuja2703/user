package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button myprofilebtn,bluetoothbtn,paymentbtn,logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myprofilebtn=(Button)findViewById(R.id.my_profile);
        bluetoothbtn=(Button)findViewById(R.id.bluetooth);
        paymentbtn=(Button)findViewById(R.id.payment);
        logoutbtn=(Button)findViewById(R.id.log_out);

        myprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,profile.class);
                startActivity(intent);

            }
        });
        bluetoothbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,bluetooth.class);
                startActivity(intent);

            }
        });
        paymentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,payment.class);
                startActivity(intent);

            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,payment.class);
                startActivity(intent);

            }
        });

    }
}