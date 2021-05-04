package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private TextView forpass;
    private Button btnsignin;
    private EditText etmail,etpass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forpass=findViewById(R.id.forgetpasswordtxt);
        btnsignin=findViewById(R.id.loginbtn);
        etmail=findViewById(R.id.emailedittxt);
        etpass=findViewById(R.id.passedittxt);
        mAuth=FirebaseAuth.getInstance();

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,OwnerHome.class));
            }
        });
        forpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,ForgetPassword.class));
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });


    }

    private void userlogin() {
        final String email=etmail.getText().toString().trim();
        String pass=etpass.getText().toString().trim();
        if(email.isEmpty())
        {
            etmail.setError("Please enter email");
            etmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etmail.setError("Please enter valid email");
            etmail.requestFocus();
            return;
        }
        if(pass.isEmpty()) {
            etpass.setError("Please enter password");
            etpass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    if(email.equals("iotproject488@gmail.com"))
                        startActivity(new Intent(Login.this,OwnerHome.class));
                    else
                        startActivity(new Intent(Login.this,MainActivity.class));
                }
                else
                {
                    Toast.makeText(Login.this,"Failed to login! Please, check your login credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}