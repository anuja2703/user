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

public class Registration extends AppCompatActivity {
    private EditText etname,etmno,etmail,etpass;
    private Button register;
    private FirebaseAuth mAuth;
    private TextView goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        goback=(TextView) findViewById(R.id.regGoBack);
        mAuth=FirebaseAuth.getInstance();
        register=(Button) findViewById(R.id.regbtn);
        etname=(EditText) findViewById(R.id.regNametxt);
        etmno=(EditText)findViewById(R.id.regMobiletxt);
        etmail=(EditText)findViewById(R.id.regEmailTxt);
        etpass=(EditText)findViewById(R.id.editTextTextPassword);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,OwnerHome.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=etname.getText().toString();
                final String email=etmail.getText().toString();
                final String mno=etmno.getText().toString();
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
                                    User user=new User(name,mno,email);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(Registration.this,"User has been registered successfully",Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(Registration.this,"Failed to register! Try again!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(Registration.this,"Failed to register! Try again!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



            }
        });
    }
}