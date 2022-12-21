package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PreClapMenu extends AppCompatActivity {

    private AppCompatButton singleSpeedBtn;
    private AppCompatButton singleForceBtn;
    private AppCompatButton singleQualityBtn;
    private AppCompatButton singleQuantityBtn;
    private AppCompatButton singleReflexBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_clap_menu);

        singleSpeedBtn = findViewById(R.id.single_speed);
        singleForceBtn = findViewById(R.id.single_force);
        singleQualityBtn = findViewById(R.id.single_quality);
        singleQuantityBtn = findViewById(R.id.single_quantity);
        singleReflexBtn = findViewById(R.id.single_reflex);

        singleSpeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClapListener(SessionType.SPEED);
            }
        });
        singleForceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClapListener(SessionType.FORCE);
            }
        });
        singleQualityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon", Toast.LENGTH_SHORT).show();
                //startClapListener(SessionType.QUALITY);
            }
        });
        singleQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClapListener(SessionType.QUANTITY);
            }
        });
        singleReflexBtn.setOnClickListener(new View.OnClickListener() {
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
    }
}