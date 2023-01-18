package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private EditText loginInput;
    private EditText passwordInput;
    private EditText repeatPasswordInput;
    private TextView registrationHint;
    private AppCompatButton registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginInput = findViewById(R.id.login_input);
        passwordInput = findViewById(R.id.password_input);
        repeatPasswordInput = findViewById(R.id.password_check_input);
        registrationHint = findViewById(R.id.registration_hint);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });

        registrationHint.setText("");
    }

    private void registerAccount() {
        registrationHint.setText("");
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();
        String repeatPassword = repeatPasswordInput.getText().toString();
        if (login.matches("") || password.matches("") || repeatPassword.matches("")) {
            registrationHint.setText("Proszę wypełnić wszsytkie pola");
            Toast.makeText(this, "Niepowodzenie", Toast.LENGTH_SHORT).show();
        }
        if (!password.equals(repeatPassword)) {
            registrationHint.setText("Wpisane hasła nie są identyczne");
            Toast.makeText(this, "Niepowodzenie", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        if (jsonCommunicator.registerAccount(login, password)) {
            Toast.makeText(this, "Konto " + login + " zarejestrowane" , Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}