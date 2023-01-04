package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private TextView loginTxt;
    private TextView passwordTxt;
    private TextView loginHint;
    private AppCompatButton loginBtn;
    private AppCompatButton registrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTxt = findViewById(R.id.login_input);
        passwordTxt = findViewById(R.id.password_input);
        loginHint = findViewById(R.id.login_hint);
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
        loginHint.setText("");
    }

    private void logIn() {
        String login = loginTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        if (jsonCommunicator.loginConfirmed(login, password))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Logowanie nie powiodło się", Toast.LENGTH_SHORT).show();
            loginHint.setText("Nieprawidłowy login lub hasło");
        }
    }

    private void openRegistrationPage() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}