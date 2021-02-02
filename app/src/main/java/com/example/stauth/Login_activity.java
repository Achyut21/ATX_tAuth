package com.example.stauth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

;

public class Login_activity extends AppCompatActivity {
    EditText MPassword,MEmail;
    Button btnLogin;
    TextView mCreateBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        MEmail = findViewById(R.id.MEmail);
        MPassword = findViewById(R.id.MPassword);
        btnLogin = findViewById(R.id.btnLogin);
        mCreateBtn = findViewById(R.id.mCreateBtn);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = MEmail.getText().toString().trim();
                String password = MPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    MEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    MPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    MPassword.setError(("Password must be  >= 6 char"));
                    return;
                }
                Log.d("OnCLick", "onClick: button clicked ");

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login_activity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login_activity.this,MainActivity2.class);
                            startActivity(intent);
                            Log.d("OoComplete", "enterd mAuth ");

                        }
                        else {
                            Toast.makeText(Login_activity.this, "Error! Enter correct email and password" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
           mCreateBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(getApplicationContext(),Register_activtity.class));
               }
           });

    }
}