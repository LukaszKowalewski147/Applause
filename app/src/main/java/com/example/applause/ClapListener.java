package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;

public class ClapListener extends AppCompatActivity {

    private SessionType sessionType;
    private AccelerometerHandler accelerometerHandler;
    private SoundManager soundManager;

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

        stopListeningBtn = findViewById(R.id.stop_listening_btn);

        stopListeningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopListening();
            }
        });

        soundManager = new SoundManager(this);
        accelerometerHandler = new AccelerometerHandler(this);

        startListener();
    }

    private void startListener() {
        TextView countDownTxt = (TextView) findViewById(R.id.clap_listener_countdown);
        countDownTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 200f);
        countDownTxt.setTextColor(Color.parseColor("#FF0000"));

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                countDownTxt.setText(new SimpleDateFormat("s").format(new Date(millisUntilFinished+1000)));
                soundManager.playCountdownSound();
            }

            public void onFinish() {
                soundManager.playStartClappingSound();
                countDownTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40f);
                countDownTxt.setText("Klaszcz!");

                startListening();
            }
        }.start();
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