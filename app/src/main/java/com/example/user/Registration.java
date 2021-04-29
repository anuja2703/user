package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Registration extends AppCompatActivity {
    private EditText etname,etmno,etmail,etpass;
    private Button registeruser;
    private FirebaseAuth mAuth;
    private TextView gobacktohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();
        gobacktohome=(TextView) findViewById(R.id.regGoBack);
        registeruser=(Button) findViewById(R.id.regbtn);
        etname=(EditText) findViewById(R.id.regNametxt);
        etmno=(EditText)findViewById(R.id.regMobiletxt);
        etmail=(EditText)findViewById(R.id.regEmailTxt);
        etpass=(EditText)findViewById(R.id.editTextTextPassword);

        gobacktohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,OwnerHome.class));
            }
        });

        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etname.getText().toString();
                String email=etmail.getText().toString();
                String mno=etmno.getText().toString();
                String pass=etpass.getText().toString();
                if(name.isEmpty())
                {
                    etname.setError("Enter the name");
                    etname.requestFocus();
                    return;
                }
                if(mno.isEmpty())
                {
                    etmno.setError("Enter mobile number");
                    etmno.requestFocus();
                    return;
                }
                if(email.isEmpty())
                {
                    etmail.setError("Enter email address");
                    etmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    etmail.setError("Enter valid email");
                    etmail.requestFocus();
                    return;
                }
                if(pass.isEmpty())
                {
                    etpass.setError("Enter the password");
                    etpass.requestFocus();
                    return;
                }
                if(pass.length()<6)
                {
                    etpass.setError("Minimum password length should be 6 character");
                    etpass.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    User myuser=new User(name,mno,email);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(myuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Toast.makeText(Registration.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(Registration.this,"Failed to register!!!",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(Registration.this,"Failed to register!!!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}