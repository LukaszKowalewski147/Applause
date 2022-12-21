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
            double[] zArray = (double[]) extras.get("zArrayKey");
            long[] timeArray = (long[]) extras.get("timeArrayKey");
            accelerationVectors = Helper.convertToLinkedList(zArray, timeArray);
        } else {
            //TODO: handle extras reading
        }

        maxParamTxt = findViewById(R.id.max_parameter_txt);
        maxParam = findViewById(R.id.max_parameter);
        avgParamTxt = findViewById(R.id.avg_parameter_txt);
        avgParam = findViewById(R.id.avg_parameter);
        saveBtn = findViewById(R.id.save_btn);
        mainMenuBtn = findViewById(R.id.main_menu_btn);

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

        prepareSummary();
    }

    private void prepareSummary() {
        ClapsAnalyzer analyzer = new ClapsAnalyzer(accelerationVectors, sessionType);
        clapsSession = analyzer.analyze();

        if (clapsSession.isNoClaps())
        {
            Toast.makeText(this, "Klaśnij minimum 2 razy!", Toast.LENGTH_SHORT).show();
            backToMenu();
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
        }
    }

    private void prepareSpeedSummary() {
        maxParamTxt.setText("Maksymalna szybkość:");
        maxParam.setText(clapsSession.getMaxSpeed() + "/s");

        avgParamTxt.setText("Średnia szybkość:");
        avgParam.setText(clapsSession.getAvgSpeed() + "/s");
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

    private void saveResults() {

        //TODO: handle save

        Toast.makeText(this, "Comming soon", Toast.LENGTH_SHORT).show();
    }

    private void backToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}