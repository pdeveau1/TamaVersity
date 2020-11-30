package com.example.engineer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{


    private TakeCareView gameView;

    private Handler handler = new Handler();
    private final static long Interval = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        gameView = new TakeCareView(this);
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);
    }
}