package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PreClapMenu extends AppCompatActivity {

    private AppCompatButton speedBtn;
    private AppCompatButton forceBtn;
    private AppCompatButton qualityBtn;
    private AppCompatButton quantityBtn;
    private AppCompatButton reflexBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_clap_menu);

        speedBtn = findViewById(R.id.speed_btn);
        forceBtn = findViewById(R.id.force_btn);
        qualityBtn = findViewById(R.id.quality_btn);
        quantityBtn = findViewById(R.id.quantity_btn);
        reflexBtn = findViewById(R.id.reflex_btn);

        speedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClapListener(SessionType.SPEED);
            }
        });
        forceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClapListener(SessionType.FORCE);
            }
        });
        qualityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon", Toast.LENGTH_SHORT).show();
                //startClapListener(SessionType.QUALITY);
            }
        });
        quantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClapListener(SessionType.QUANTITY);
            }
        });
        reflexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startClapListener(SessionType sessionType) {
        Intent intent = new Intent(this, ClapListener.class);
        intent.putExtra("sessionTypeKey", sessionType);
        startActivity(intent);
        if (Session.alwaysShowClapInstruction)
            showClapInstruction();
    }

    private void showClapInstruction() {
        Intent intent = new Intent(this, ClapInstruction.class);
        startActivity(intent);
    }
}