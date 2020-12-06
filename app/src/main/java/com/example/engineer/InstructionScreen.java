package com.example.engineer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class InstructionScreen extends AppCompatActivity{
    GestureDetector gestureDetector;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_screen);
        //gestureDetector = new GestureDetector(InstructionScreen.this, InstructionScreen.this);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        this.gestureDetector.onTouchEvent(event);
//        // Be sure to call the superclass implementation
//        return super.onTouchEvent(event);
        Intent loadIntent = new Intent(InstructionScreen.this, LoadActivity.class);
        startActivity(loadIntent);
        return false;
    }
}