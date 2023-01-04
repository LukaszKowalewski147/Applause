package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    private EditText loginInput;
    private EditText passwordInput;
    private AppCompatButton registerBtn;
    private AppCompatButton deleteAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginInput = findViewById(R.id.login_input);
        passwordInput = findViewById(R.id.password_input);
        registerBtn = findViewById(R.id.register_btn);
        deleteAccountBtn = findViewById(R.id.delete_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
        deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });
    }

    private void registerAccount() {
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        jsonCommunicator.registerAccount(login, password);
        finish();
    }

    private void deleteAccount() {
        String login = loginInput.getText().toString();
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        jsonCommunicator.deleteAccount(login);
    }
}