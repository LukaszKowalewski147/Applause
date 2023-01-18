package com.example.applause;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

public class Settings extends AppCompatActivity {

    private TextView accountLoginInfoTxt;
    private SwitchCompat privateModeSwitch;
    private SwitchCompat appSoundsSwitch;
    private SwitchCompat showClapInstructionSwitch;
    private SwitchCompat showProximityInstructionSwitch;
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

        prepareValues();

        privateModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                manageSettingsSwitches();
            }
        });
        appSoundsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                manageSettingsSwitches();
            }
        });
        showClapInstructionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                manageSettingsSwitches();
            }
        });
        showProximityInstructionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                manageSettingsSwitches();
            }
        });
        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
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
                showClapInstruction();
            }
        });
        showProximityInstructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProximityInstruction();
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void prepareValues() {
        accountLoginInfoTxt.setText(Session.login);
        privateModeSwitch.setChecked(Session.privateAccount);
        appSoundsSwitch.setChecked(Session.soundsEnabled);
        showClapInstructionSwitch.setChecked(Session.alwaysShowClapInstruction);
        showProximityInstructionSwitch.setChecked(Session.alwaysShowProximityInstruction);
    }

    private void changePassword() {
        ChangePasswordManager changePasswordManager = new ChangePasswordManager();
        changePasswordManager.show(getSupportFragmentManager(), "change_password_manager");
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

    private void manageSettingsSwitches() {
        Session.privateAccount = privateModeSwitch.isChecked();
        Session.soundsEnabled = appSoundsSwitch.isChecked();
        Session.alwaysShowClapInstruction = showClapInstructionSwitch.isChecked();
        Session.alwaysShowProximityInstruction = showProximityInstructionSwitch.isChecked();
        JSONCommunicator communicator = new JSONCommunicator(this);
        communicator.updateSettings();
    }

    private void showClapInstruction() {
        Intent intent = new Intent(this, ClapInstruction.class);
        startActivity(intent);
    }

    private void showProximityInstruction() {
        Toast.makeText(this, "Comming soon", Toast.LENGTH_SHORT).show();
    }

    private void logout() {
        Session.login = "";
        Intent intent = new Intent(this, Login.class);
        finishAffinity();
        startActivity(intent);
    }
}