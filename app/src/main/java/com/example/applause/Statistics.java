package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

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

    private TextView avgSpeedTxt;
    private TextView maxSpeedTxt;
    private TextView avgForceTxt;
    private TextView maxForceTxt;
    private TextView avgQualityTxt;
    private TextView maxQualityTxt;
    private TextView avgQuantityTxt;
    private TextView maxQuantityTxt;
    private TextView avgReflexTxt;
    private TextView maxReflexTxt;

    private ArrayList<ClapsSession> clapsSessions;
    private int speedSessionCount;
    private double avgSpeed;
    private double maxSpeed;
    private int forceSessionCount;
    private double avgForce;
    private double maxForce;
    private int qualitySessionCount;
    private double avgQuality;
    private int maxQuality;
    private int quantitySessionCount;
    private double avgQuantity;
    private int maxQuantity;
    private int reflexSessionCount;
    private double avgReflex;
    private int maxReflex;

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

        avgSpeedTxt = findViewById(R.id.avg_speed);
        maxSpeedTxt = findViewById(R.id.max_speed);
        avgForceTxt = findViewById(R.id.avg_force);
        maxForceTxt = findViewById(R.id.max_force);
        avgQualityTxt = findViewById(R.id.avg_quality);
        maxQualityTxt = findViewById(R.id.max_quality);
        avgQuantityTxt = findViewById(R.id.avg_quantity);
        maxQuantityTxt = findViewById(R.id.max_quantity);
        avgReflexTxt = findViewById(R.id.avg_reflex);
        maxReflexTxt = findViewById(R.id.max_reflex);

        prepareData();

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

        showData();
    }

    private void prepareData() {
        JSONCommunicator communicator = new JSONCommunicator(this);
        clapsSessions = communicator.getAllClapsSessions(Session.login);
        initializeVariables();

        for (ClapsSession session : clapsSessions) {
            switch (session.getSessionType()) {
                case SPEED:
                    speedSessionCount++;
                    avgSpeed += session.getAvgSpeed();
                    if (session.getMaxSpeed() > maxSpeed)
                        maxSpeed = session.getMaxSpeed();
                    break;
                case FORCE:
                    forceSessionCount++;
                    avgForce += session.getAvgForce();
                    if (session.getMaxForce() > maxForce)
                        maxForce = session.getMaxForce();
                    break;
                case QUALITY:
                    qualitySessionCount++;
                    int quality = session.getQuality();
                    avgQuality += quality;
                    if (quality > maxQuality)
                        maxQuality = quality;
                    break;
                case QUANTITY:
                    quantitySessionCount++;
                    int quantity = session.getQuantity();
                    avgQuantity += quantity;
                    if (quantity > maxQuantity)
                        maxQuantity = quantity;
                    break;
                case REFLEX:
                    reflexSessionCount++;
                    int reflex = session.getReflex();
                    avgReflex += reflex;
                    if (reflex < maxReflex)
                        maxReflex = reflex;
                    break;
            }
        }

        if (speedSessionCount > 0) {
            avgSpeed /= speedSessionCount;
            avgSpeed = Helper.changePrecision(avgSpeed, 2);
        }

        if (forceSessionCount > 0) {
            avgForce /= forceSessionCount;
            avgForce = Helper.changePrecision(avgForce, 2);
        }

        if (qualitySessionCount > 0) {
            avgQuality /= qualitySessionCount;
            avgQuality = Helper.changePrecision(avgQuality, 2);
        }

        if (quantitySessionCount > 0) {
            avgQuantity /= quantitySessionCount;
            avgQuantity = Helper.changePrecision(avgQuantity, 2);
        }

        if (reflexSessionCount > 0) {
            avgReflex /= reflexSessionCount;
            avgReflex = Helper.changePrecision(avgReflex, 2);
        }
    }

    private void showData() {
        avgSpeedTxt.setText(avgSpeed + "/min");
        maxSpeedTxt.setText(maxSpeed + "/min");
        avgForceTxt.setText(avgForce + "N");
        maxForceTxt.setText(maxForce + "N");
        avgQualityTxt.setText(avgQuality + "%");
        maxQualityTxt.setText(maxQuality + "%");
        avgQuantityTxt.setText(String.valueOf(avgQuantity));
        maxQuantityTxt.setText(String.valueOf(maxQuantity));
        avgReflexTxt.setText(avgReflex + "ms");
        maxReflexTxt.setText(maxReflex + "ms");
    }

    private void initializeVariables() {
        speedSessionCount = 0;
        forceSessionCount = 0;
        qualitySessionCount = 0;
        quantitySessionCount = 0;
        reflexSessionCount = 0;
        avgSpeed = 0.0d;
        maxSpeed = 0.0d;
        avgForce = 0.0d;
        maxForce = 0.0d;
        avgQuality = 0.0d;
        maxQuality = 0;
        avgQuantity = 0.0d;
        maxQuantity = 0;
        avgReflex = 0.0d;
        maxReflex = Integer.MAX_VALUE;
    }

    private void openGraph(SessionType sessionType) {
        Intent intent = new Intent(this, StatsGraph.class);
        intent.putExtra("sessionTypeKey", sessionType);
        startActivity(intent);
    }
}