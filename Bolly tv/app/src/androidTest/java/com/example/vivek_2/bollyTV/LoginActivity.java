package com.example.vivek_2.bollyTV;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    Button login;
    EditText name,pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        name=(EditText) findViewById(R.id.edt1);
        pas=(EditText) findViewById(R.id.edt2);
        progressDialog=new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        View decorview=getWindow().getDecorView();
        decorview.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        login=findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=name.getText().toString().trim();
                String password=pas.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    name.setError("Enter your Email");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    pas.setError("Enter your Password");
                    return;
                }
                if(password.length()<5)
                {
                    pas.setError("Password should be atleast of 6 characters");
                    return;
                }
                progressDialog.setMessage("Signing in...");
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Signed in as "+email, Toast.LENGTH_SHORT).show();
                                    //updateUI(user);
                                    String name1=user.getDisplayName();
                                    progressDialog.hide();
                                    Intent i=new Intent(LoginActivity.this,HomePage.class);
                                    i.putExtra("name",name1);
                                    startActivity(i);
                                    finish();
                                } else {

                                    Toast.makeText(LoginActivity.this, "Username and Password doesn't match",
                                            Toast.LENGTH_SHORT).show();
                                    progressDialog.hide();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
        Button btn2=findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
                finish();
            }
        });
        TextView forgot_txt=findViewById(R.id.forgot_txt);
        forgot_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Exit app ?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertdialog=builder.create();
        alertdialog.show();
    }
}
