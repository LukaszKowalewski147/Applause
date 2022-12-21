package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Queue;

public class ClapListener extends AppCompatActivity {

    private SessionType sessionType;
    private AccelerometerHandler accelerometerHandler;
    private AppCompatButton stopListeningBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clap_listener);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sessionType = (SessionType) extras.get("sessionTypeKey");
        } else {
            //handle
        }

        accelerometerHandler = new AccelerometerHandler(this);
        startListening();

        stopListeningBtn = findViewById(R.id.stop_listening_btn);

        stopListeningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopListening();
            }
        });
    }

    private void startListening() {
        accelerometerHandler.startAccelerometer();
    }

    private void stopListening() {
        Queue<AccelerationVector> accelerationVectors = accelerometerHandler.handleAccelerometerStop();
        double[] zArray = Helper.convertToDoubleArray(accelerationVectors);
        long[] timeArray = Helper.convertTimeToLongArray(accelerationVectors);

        Intent intent = new Intent(this, ClapsSummary.class);
        intent.putExtra("sessionTypeKey", sessionType);
        intent.putExtra("zArrayKey", zArray);
        intent.putExtra("timeArrayKey", timeArray);
        startActivity(intent);
    }
}