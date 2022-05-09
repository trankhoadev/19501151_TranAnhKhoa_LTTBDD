package com.example.all_crud_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    Button btnSignUp;
    TextView txtUserName, txtPassword, txtRepassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignUp = findViewById(R.id.btnSignUp);
        txtUserName = findViewById(R.id.txtEmailSignup);
        txtPassword = findViewById(R.id.txtPasswordSignup);
        txtRepassword = findViewById(R.id.txtRePasswordSignup);
        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(view -> {
            checkSignupUser();
        });
    }

    private void checkSignupUser() {
        String userName = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        String rePassword = txtRepassword.getText().toString();

        if(userName.isEmpty()) {
            txtUserName.setError("Name not null!");
            txtUserName.requestFocus();
        }
        else if(password.isEmpty()) {
            txtPassword.setError("Password not null!");
            txtPassword.requestFocus();
        }
        else if(rePassword.isEmpty()) {
            txtRepassword.setError("Repassword not null!");
            txtRepassword.requestFocus();
        }
        else if (!password.equals(rePassword)) {
            txtRepassword.setError("Password not same!");
            txtRepassword.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Create account successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, TodoApp.class));
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "Error: " + task.getException().toString() , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}