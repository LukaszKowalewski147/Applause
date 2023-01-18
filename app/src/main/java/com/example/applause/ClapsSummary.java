package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class ClapsSummary extends AppCompatActivity {

    private SessionType sessionType;
    private LinkedList<AccelerationVector> accelerationVectors;
    private ClapsSession clapsSession;
    private long reactionTime;

    private TextView maxParamTxt;
    private TextView maxParam;
    private TextView avgParamTxt;
    private TextView avgParam;
    private AppCompatButton saveBtn;
    private AppCompatButton mainMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claps_summary);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sessionType = (SessionType) extras.get("sessionTypeKey");
            if (sessionType != SessionType.REFLEX) {
                double[] zArray = (double[]) extras.get("zArrayKey");
                long[] timeArray = (long[]) extras.get("timeArrayKey");
                accelerationVectors = Helper.convertToLinkedList(zArray, timeArray);
            } else {
                reactionTime = (long) extras.get("reflexKey");
            }
        }

        maxParamTxt = findViewById(R.id.max_parameter_txt);
        maxParam = findViewById(R.id.max_parameter);
        avgParamTxt = findViewById(R.id.avg_parameter_txt);
        avgParam = findViewById(R.id.avg_parameter);
        saveBtn = findViewById(R.id.save_btn);
        mainMenuBtn = findViewById(R.id.main_menu_btn);

        prepareSummary();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveResults();
            }
        });
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });
    }

    private void prepareSummary() {
        ClapsAnalyzer analyzer;
        if (sessionType != SessionType.REFLEX) {
            analyzer = new ClapsAnalyzer(accelerationVectors, sessionType);
            clapsSession = analyzer.analyze();
            if (clapsSession.isNoClaps())
            {
                Toast.makeText(this, "Klaśnij minimum 2 razy!", Toast.LENGTH_SHORT).show();
                backToMenu();
            }
        } else {
            analyzer = new ClapsAnalyzer(reactionTime, sessionType);
            clapsSession = analyzer.analyze();
        }

        switch (sessionType) {
            case SPEED:
                prepareSpeedSummary();
                break;
            case FORCE:
                prepareForceSummary();
                break;
            case QUALITY:
                prepareQualitySummary();
                break;
            case QUANTITY:
                prepareQuantitySummary();
                break;
            case REFLEX:
                prepareReflexSummary();
        }
    }

    private void prepareSpeedSummary() {
        maxParamTxt.setText("Maksymalna szybkość:");
        maxParam.setText(clapsSession.getMaxSpeed() + "/min");

        avgParamTxt.setText("Średnia szybkość:");
        avgParam.setText(clapsSession.getAvgSpeed() + "/min");
    }

    private void prepareForceSummary() {
        maxParamTxt.setText("Maksymalna siła:");
        maxParam.setText(clapsSession.getMaxForce() + "N");

        avgParamTxt.setText("Średnia siła:");
        avgParam.setText(clapsSession.getAvgForce() + "N");
    }

    private void prepareQualitySummary() {
        maxParamTxt.setText("Jakość:");
        maxParam.setText(clapsSession.getQuality() + "%");

        avgParamTxt.setText("");
        avgParam.setText("");
    }

    private void prepareQuantitySummary() {
        maxParamTxt.setText("Ilość klaśnięć:");
        maxParam.setText(clapsSession.getQuantity() + "");

        avgParamTxt.setText("");
        avgParam.setText("");
    }

    private void prepareReflexSummary() {
        maxParamTxt.setText("Czas reakcji:");
        maxParam.setText(clapsSession.getReflex() + "ms");

        avgParamTxt.setText("");
        avgParam.setText("");
    }

    private void saveResults() {
        boolean success = false;
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        switch (sessionType) {
            case SPEED:
                JSONClap<Double> speedSession = new JSONClap<>(sessionType, clapsSession.getAvgSpeed(), clapsSession.getMaxSpeed());
                jsonCommunicator.saveClapsSession(speedSession);
                success = true;
                break;
            case FORCE:
                JSONClap<Double> forceSession = new JSONClap<>(sessionType, clapsSession.getAvgForce(), clapsSession.getMaxForce());
                jsonCommunicator.saveClapsSession(forceSession);
                success = true;
                break;
            case QUALITY:
                JSONClap<Integer> qualitySession = new JSONClap<>(sessionType, clapsSession.getQuality());
                jsonCommunicator.saveClapsSession(qualitySession);
                success = true;
                break;
            case QUANTITY:
                JSONClap<Integer> quantitySession = new JSONClap<>(sessionType, clapsSession.getQuantity());
                jsonCommunicator.saveClapsSession(quantitySession);
                success = true;
                break;
            case REFLEX:
                JSONClap<Integer> reflexSession = new JSONClap<>(sessionType, clapsSession.getReflex());
                jsonCommunicator.saveClapsSession(reflexSession);
                success = true;
                break;
        }
        if (success) {
            Toast.makeText(this, "Zapisano", Toast.LENGTH_SHORT).show();
            saveBtn.setClickable(false);
        }
    }

    private void backToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}