package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class ReflexListener extends AppCompatActivity {

    private View screen;
    private ProximityHandler proximityHandler;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflex_listener);

        screen = findViewById(R.id.reflex_root);
        soundManager = new SoundManager(this);
        proximityHandler = new ProximityHandler(this);

        startListener();
    }

    private void startListener() {
        TextView countDownTxt = (TextView) findViewById(R.id.reflex_listener_countdown);
        countDownTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50f);
        countDownTxt.setTextColor(Color.parseColor("#FF0000"));
        countDownTxt.setText("Czekaj...");

        int randomTime = ThreadLocalRandom.current().nextInt(3000, 8000);
        new CountDownTimer(randomTime, 1) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                screen.setBackgroundResource(R.color.native_green);
                countDownTxt.setText("");
                soundManager.playStartClappingSound();
                startListening();
            }
        }.start();
    }

    private void startListening() {
        proximityHandler.startProximity();
    }
}