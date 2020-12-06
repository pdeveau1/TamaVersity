//Activity that first loads displaying activity_start.xml with a logo and title of app then goes to InstructionScreen
package com.example.engineer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;

    //hooks
    ImageView logoPic;
    TextView name;


    //animation variables
    Animation topAnimation, bottomAnimation, middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

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
                //creates new intent to go from StartActivity to InstructionScreen
                Intent intent = new Intent(StartActivity.this, InstructionScreen.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

