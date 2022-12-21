package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton settingsBtn;
    private AppCompatButton startBtn;
    private AppCompatButton statsBtn;
    private AppCompatButton rankingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsBtn = findViewById(R.id.settings_btn);
        startBtn = findViewById(R.id.start_btn);
        statsBtn = findViewById(R.id.stats_btn);
        rankingBtn = findViewById(R.id.ranking_btn);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStats();
            }
        });
        rankingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRanking();
            }
        });
    }

    private void start() {
        Intent intent = new Intent(this, Instruction.class);
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    private void openStats() {
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    private void openRanking() {
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }
}