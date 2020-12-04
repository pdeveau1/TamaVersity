package com.example.engineer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity
{
    private TakeCareView gameView;

    private Handler handler = new Handler();
    private final static long Interval = 30;

    private static int SPLASH_TIME_OUT = 5000;

    //hooks
    ImageView logoPic;
    TextView name;


    //animation variables
    Animation topAnimation, bottomAnimation, middleAnimation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");


        //gameView = new TakeCareView(this, engineer);
        gameView = new TakeCareView(this, engineer);

        setContentView(gameView);

        //animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);

        //hooks
        logoPic = findViewById(R.id.logo);
        name = findViewById(R.id.gameName);

        //setting animations
        logoPic.setAnimation(topAnimation);
        name.setAnimation(bottomAnimation);


        //splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //creates new intent to go from MainActivity to LoadActivity
                Intent intent = new Intent(MainActivity.this, LoadActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

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

