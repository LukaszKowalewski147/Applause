package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    AppCompatButton loginBtn;
    AppCompatButton registrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_btn);
        registrationBtn = findViewById(R.id.registration_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrationPage();
            }
        });
    }

    private void logIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openRegistrationPage() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}