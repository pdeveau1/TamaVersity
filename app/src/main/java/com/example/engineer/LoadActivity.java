//class for activity when prompting the user to load an old engineer or create a new one
package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadActivity extends AppCompatActivity
{
    private static int SPLASH_TIME_OUT = 5000;

    private Button load_eng;
    private Button new_eng;

    //animation variables
    Animation topAnimation, bottomAnimation, middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        load_eng = (Button) findViewById(R.id.load_eng);
        new_eng = (Button) findViewById(R.id.new_eng);

        //animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);



        //setting animations
        load_eng.setAnimation(topAnimation);
        new_eng.setAnimation(bottomAnimation);


        //if user clicks to load the past engineer
        load_eng.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //gets the saved information
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                float academics = preferences.getFloat("lastAcademic",20);
                float health = preferences.getFloat("lastHealth",20);
                float social = preferences.getFloat("lastSocial",20);
                String date = preferences.getString("Date","01/01/1999");
                //creates new TakeCare object with saved data
                TakeCare engineer = new TakeCare(academics, health, social);
                //creates new intent to go from LoadActivity to MainActivity
                Intent mainIntent = new Intent(LoadActivity.this, MainActivity.class);
                //passes to mainActivity the engineer
                mainIntent.putExtra("Engineer", engineer);
                mainIntent.putExtra("Date", date);
                //starts main activity
                startActivity(mainIntent);
            }
        });

        new_eng.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //creates new TakeCare object for new engineer
                TakeCare engineer = new TakeCare();
                //creates new intent to go from LoadActivity to MainActivity
                Intent dateIntent = new Intent(LoadActivity.this, DateActivity.class);
                //passes to mainActivity the engineer
                dateIntent.putExtra("Engineer", engineer);
                //starts main activity
                startActivity(dateIntent);
            }
        });

    }
}