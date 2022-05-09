package com.example.all_crud_database;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnRedirectLogin, btnRedirectReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRedirectLogin = findViewById(R.id.btn_redirectLogin);
        btnRedirectReg = findViewById(R.id.btn_redirectRegistry);

        btnRedirectLogin.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LoginActivty.class));
        });

        btnRedirectReg.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RegistryActivity.class));
        });
    }

}