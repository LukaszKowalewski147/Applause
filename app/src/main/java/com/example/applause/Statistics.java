package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Statistics extends AppCompatActivity {

    private RelativeLayout reflexLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        reflexLayout = findViewById(R.id.avg_reflex_stats_layout);

        reflexLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph();
            }
        });
    }

    private void openGraph() {
        Intent intent = new Intent(this, StatsGraph.class);
        startActivity(intent);
    }
}