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

        singleSpeedBtn = findViewById(R.id.speed_btn);
        singleForceBtn = findViewById(R.id.force_btn);
        singleQualityBtn = findViewById(R.id.quality_btn);
        singleQuantityBtn = findViewById(R.id.quantity_btn);
        singleReflexBtn = findViewById(R.id.reflex_btn);

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