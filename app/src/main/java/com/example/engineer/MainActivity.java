package com.example.engineer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private TakeCareView gameView;
    private static final String TAG = "MainActivity";

    private Handler handler = new Handler();
    private final static long Interval = 30;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        String date = (String)getIntent().getSerializableExtra("Date");
        String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());

        if(date.compareTo(currentDate) < 0)

        {
            Intent finishIntent = new Intent(MainActivity.this, FinishActivity.class);
            startActivity(finishIntent);
        }

        //gameView = new TakeCareView(this, engineer);
        gameView = new TakeCareView(this, engineer);

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

