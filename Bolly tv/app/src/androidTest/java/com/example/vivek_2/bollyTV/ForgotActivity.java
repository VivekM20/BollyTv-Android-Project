package com.example.vivek_2.bollyTV;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshranjan1.bollytv.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    EditText email;
    Button submit;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        TextView login=findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ForgotActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();


        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=findViewById(R.id.email);
                 String email_str=email.getText().toString();

                 mAuth.sendPasswordResetEmail(email_str).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgotActivity.this, "Link to reset Password sent to registered email", Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(ForgotActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(ForgotActivity.this, "Email not registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
