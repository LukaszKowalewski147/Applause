package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    AppCompatButton settingsBtn;
    AppCompatButton statsBtn;
    AppCompatButton rankingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsBtn = findViewById(R.id.settings_btn);
        statsBtn = findViewById(R.id.stats_btn);
        rankingBtn = findViewById(R.id.ranking_btn);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
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