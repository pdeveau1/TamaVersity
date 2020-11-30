package com.example.engineer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private TakeCareView gameView;
    private TakeCare engineer = new TakeCare();

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

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("lastAcademic",engineer.getAcademic());
                editor.putFloat("lastHealth",engineer.getHealth());
                editor.putFloat("lastSocial",engineer.getSocial());
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), SaveEngineer.class);
                startActivity(intent);
                finish();
            }
        })
    }
}