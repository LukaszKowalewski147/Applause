package com.example.applause;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class Settings extends AppCompatActivity {

    private TextView accountLoginInfoTxt;
    private Switch privateModeSwitch;
    private Switch appSoundsSwitch;
    private Switch showClapInstructionSwitch;
    private Switch showProximityInstructionSwitch;
    private AppCompatButton changePasswordBtn;
    private AppCompatButton deleteAccountBtn;
    private AppCompatButton showClapInstructionBtn;
    private AppCompatButton showProximityInstructionBtn;
    private AppCompatButton logoutBtn;
    private AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        accountLoginInfoTxt = findViewById(R.id.account_login_info);
        privateModeSwitch = findViewById(R.id.setting_private_mode);
        appSoundsSwitch = findViewById(R.id.setting_app_sounds);
        showClapInstructionSwitch = findViewById(R.id.setting_app_show_clap_instruction);
        showProximityInstructionSwitch = findViewById(R.id.setting_app_show_proximity_instruction);
        changePasswordBtn = findViewById(R.id.change_password_btn);
        deleteAccountBtn = findViewById(R.id.delete_account_btn);
        showClapInstructionBtn = findViewById(R.id.show_clap_instruction_btn);
        showProximityInstructionBtn = findViewById(R.id.show_proximity_instruction_btn);
        logoutBtn = findViewById(R.id.logout_btn);

        privateModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        appSoundsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        showClapInstructionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        showProximityInstructionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });
        showClapInstructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        showProximityInstructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        accountLoginInfoTxt.setText(Session.login);
    }

    private void changePassword() {

    }

    private void deleteAccount() {
        alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Usuń konto")
                .setMessage("Czy chcesz usunąć konto " + Session.login + "?")
                .setCancelable(true)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        JSONCommunicator jsonCommunicator = new JSONCommunicator(getApplicationContext());
                        jsonCommunicator.deleteAccount();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    private void showClapInstruction() {

    }

    private void showProximityInstruction() {

    }

    private void logout() {
        Session.login = "";
        Intent intent = new Intent(this, Login.class);
        finishAffinity();
        startActivity(intent);
    }
}