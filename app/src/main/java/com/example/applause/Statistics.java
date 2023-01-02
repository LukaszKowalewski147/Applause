package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Statistics extends AppCompatActivity {

    private RelativeLayout avgSpeedLayout;
    private RelativeLayout maxSpeedLayout;
    private RelativeLayout avgForceLayout;
    private RelativeLayout maxForceLayout;
    private RelativeLayout avgQualityLayout;
    private RelativeLayout maxQualityLayout;
    private RelativeLayout avgQuantityLayout;
    private RelativeLayout maxQuantityLayout;
    private RelativeLayout avgReflexLayout;
    private RelativeLayout maxReflexLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        avgSpeedLayout = findViewById(R.id.avg_speed_stats_layout);
        maxSpeedLayout = findViewById(R.id.max_speed_stats_layout);
        avgForceLayout = findViewById(R.id.avg_force_stats_layout);
        maxForceLayout = findViewById(R.id.max_force_stats_layout);
        avgQualityLayout = findViewById(R.id.avg_quality_stats_layout);
        maxQualityLayout = findViewById(R.id.max_quality_stats_layout);
        avgQuantityLayout = findViewById(R.id.avg_quantity_stats_layout);
        maxQuantityLayout = findViewById(R.id.max_quantity_stats_layout);
        avgReflexLayout = findViewById(R.id.avg_reflex_stats_layout);
        maxReflexLayout = findViewById(R.id.max_reflex_stats_layout);

        avgSpeedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.SPEED);
            }
        });
        maxSpeedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.SPEED);
            }
        });
        avgForceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.FORCE);
            }
        });
        maxForceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.FORCE);
            }
        });
        avgQualityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.QUALITY);
            }
        });
        maxQualityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.QUALITY);
            }
        });
        avgQuantityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.QUANTITY);
            }
        });
        maxQuantityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.QUANTITY);
            }
        });
        avgReflexLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.REFLEX);
            }
        });
        maxReflexLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(SessionType.REFLEX);
            }
        });
    }

    private void openGraph(SessionType sessionType) {
        Intent intent = new Intent(this, StatsGraph.class);
        intent.putExtra("sessionTypeKey", sessionType);
        startActivity(intent);
    }
}