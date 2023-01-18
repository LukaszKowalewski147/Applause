package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private TextView loginTxt;
    private TextView passwordTxt;
    private TextView loginHint;
    private AppCompatButton loginBtn;
    private AppCompatButton registrationBtn;
    private AppCompatButton showAccountsBtn;
    private SwitchCompat loginAdminSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTxt = findViewById(R.id.login_input);
        passwordTxt = findViewById(R.id.password_input);
        loginHint = findViewById(R.id.login_hint);
        loginBtn = findViewById(R.id.login_btn);
        registrationBtn = findViewById(R.id.registration_btn);
        showAccountsBtn = findViewById(R.id.show_accounts_btn);
        loginAdminSwitch = findViewById(R.id.login_admin_switch);

        loginAdminSwitch.setChecked(false);
        manageAdminRights();

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
        showAccountsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAccounts();
            }
        });
        loginAdminSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                manageAdminRights();
            }
        });

        loginHint.setText("");
    }

    private void logIn() {
        loginHint.setText("");
        String login = loginTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        if (jsonCommunicator.loginConfirmed(login, password)) {
            Session.login = login;
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

    private void manageAdminRights() {
        if (loginAdminSwitch.isChecked()) {
            showAccountsBtn.setVisibility(View.VISIBLE);
        } else {
            showAccountsBtn.setVisibility(View.GONE);
        }
    }

    private void showAccounts() {
        loginHint.setText("");
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        jsonCommunicator.showAccounts();
    }
}