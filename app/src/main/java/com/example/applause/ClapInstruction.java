package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ClapInstruction extends AppCompatActivity {

    private ImageView instruction;
    private AppCompatButton understandBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clap_instruction);

        instruction = findViewById(R.id.instruction_animation);
        understandBtn = findViewById(R.id.understand_btn);

        ((AnimationDrawable) instruction.getDrawable()).start();

        understandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}