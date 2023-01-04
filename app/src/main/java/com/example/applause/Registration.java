package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    private EditText loginInput;
    private EditText passwordInput;
    private AppCompatButton registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginInput = findViewById(R.id.login_input);
        passwordInput = findViewById(R.id.password_input);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
    }

    private void registerAccount() {
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        jsonCommunicator.writeJson(login, password);
        finish();
    }
}