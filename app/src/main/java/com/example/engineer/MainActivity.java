package com.example.engineer;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        String date = (String)getIntent().getSerializableExtra("Date");


        //gameView = new TakeCareView(this, engineer);
        gameView = new TakeCareView(this, engineer, date);

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

