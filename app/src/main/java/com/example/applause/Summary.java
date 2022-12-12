package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    TextView maxParamTxt;
    TextView maxParam;
    TextView avgParamTxt;
    TextView avgParam;
    AppCompatButton saveBtn;
    AppCompatButton mainMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

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
    }

    private void saveResults() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void backToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}