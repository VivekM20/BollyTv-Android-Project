package com.example.vivek_2.bollyTV;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    EditText name,lstname,email,password,confpassword;
    Button submit;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toolbar=findViewById(R.id.tool);
        progressDialog=new ProgressDialog(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  Sign up");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        lstname=findViewById(R.id.lstname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confpassword=findViewById(R.id.confpassword);
        progressDialog=new ProgressDialog(this);
        submit=findViewById(R.id.submit);
        
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                final String name_str=name.getText().toString();
                String lstname_str=lstname.getText().toString();
                String email_str= email.getText().toString();
                String password_str=password.getText().toString().trim();
                String conf_str=confpassword.getText().toString().trim();

                if(TextUtils.isEmpty(name_str))
                {
                    name.setError("Required Field");
                    return;
                }
                if(TextUtils.isEmpty(lstname_str))
                {
                    lstname.setError("Required Field");
                    return;
                }
                if(TextUtils.isEmpty(email_str))
                {
                    email.setError("Required Field");
                    return;
                }
                if(password_str.length()<5)
                {
                    password.setError("Password should be of minimum 6 characters");
                    return;
                }
                else
                {
                    progressDialog.setMessage("Registering User...");
                    progressDialog.show();

                    if(password_str.equals(conf_str))
                    {
                        mAuth.createUserWithEmailAndPassword(email_str,password_str)
                                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.setMessage("Registering user...");
                                            progressDialog.show();
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            UserProfileChangeRequest request=new UserProfileChangeRequest.Builder().setDisplayName(name_str).build();
                                            user.updateProfile(request);


                                            Toast.makeText(SignupActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                                            Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                                            startActivity(i);
                                            Toast.makeText(SignupActivity.this, "Login with registered Email", Toast.LENGTH_SHORT).show();
                                            finish();
                                            //updateUI(user);
                                        } else {
                                            progressDialog.hide();
                                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            //updateUI(null);
                                        }
                                    }
                                });
                    }
                    else
                    {
                        progressDialog.hide();
                        confpassword.setError("Password doesn't match");
                        return;
                    }
                }
            }
        });






    }
}
