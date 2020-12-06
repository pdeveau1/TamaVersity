package com.example.engineer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class InstructionScreen extends AppCompatActivity{
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_screen);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent loadIntent = new Intent(InstructionScreen.this, LoadActivity.class);
        startActivity(loadIntent);
        return false;
    }
}