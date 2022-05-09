package com.example.all_crud_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView txtUser, txtPassword, textBack;
    Button btnLogin;
    FirebaseAuth mAuth;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtEmailLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        back = findViewById(R.id.backIconLogin);
        textBack = findViewById(R.id.textBack);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            checkLoginUser();
        });

        back.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });

        textBack.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });
    }

    private void checkLoginUser() {
        String email = txtUser.getText().toString();
        String password = txtPassword.getText().toString();

        if(email.isEmpty()) {
            txtUser.setError("Email not null !");
            txtUser.requestFocus();
        }
        else if(password.isEmpty()) {
            txtPassword.setError("Password not null !");
            txtPassword.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, TodoApp.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Error: " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}