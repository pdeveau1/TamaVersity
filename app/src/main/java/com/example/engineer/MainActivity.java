package com.example.engineer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private TakeCareView gameView;

    private Handler handler = new Handler();
    private final static long Interval = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");


        //gameView = new TakeCareView(this, engineer);
        gameView = new TakeCareView(this);

        setContentView(gameView);

            //creates interval to continuously update the screen view
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                gameView.invalidate();
                            }
                        });

                }
            }, 0, Interval);
    }
}

