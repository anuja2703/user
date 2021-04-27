package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private Button loginbt;
    private TextView forgetpass;
    private EditText emailEdittxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbt=(Button)findViewById(R.id.loginbtn);
        forgetpass=(TextView)findViewById(R.id.forgetpasswordtxt);
        emailEdittxt=(EditText)findViewById(R.id.emailedittxt);
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailEdittxt.getText().toString().equals("iotproject488@gmail.com"))
                    startActivity(new Intent(Login.this,OwnerHome.class));
                else
                    startActivity(new Intent(Login.this,bluetooth.class));
            }
        });
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,ForgetPassword.class));
            }
        });
    }
}