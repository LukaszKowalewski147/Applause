package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PreClapMenu extends AppCompatActivity {

    AppCompatButton singleForceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_clap_menu);

        singleForceBtn = findViewById(R.id.single_force);

        singleForceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClapListener();
            }
        });
    }

    private void startClapListener() {
        Intent intent = new Intent(this, ClapListener.class);
        startActivity(intent);
    }
}