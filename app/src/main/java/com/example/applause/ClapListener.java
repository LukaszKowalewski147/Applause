package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

public class ClapListener extends AppCompatActivity {

    AccelerometerHandler accelerometerHandler;

    AppCompatButton stopListeningBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clap_listener);

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
        accelerometerHandler.handleAccelerometerStop();
        finish();
    }
}