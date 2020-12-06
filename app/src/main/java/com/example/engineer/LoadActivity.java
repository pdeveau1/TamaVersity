//class for activity when prompting the user to load an old engineer or create a new one
package com.example.engineer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
                Animation bounceAnimation = AnimationUtils.loadAnimation(LoadActivity.this, R.anim.bounce_animation);
                load_eng.startAnimation(bounceAnimation);
                //gets the saved information
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                float academics = preferences.getFloat("lastAcademic",100);
                float health = preferences.getFloat("lastHealth",100);
                float social = preferences.getFloat("lastSocial",100);
                String name = preferences.getString("Name","Jimmy");
                String date = preferences.getString("Date","3000-01-01");
                //creates new TakeCare object with saved data
                TakeCare engineer = new TakeCare(academics, health, social, name);
                //engineer.setName(preferences.getString("Name","Jimmy"));

                //creates new intent to go from LoadActivity to MainActivity
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                if(date.compareTo(currentDate) <= 0)
                {
                    Intent reportIntent = new Intent(LoadActivity.this, FinishActivity.class);
                    reportIntent.putExtra("Engineer", engineer);
                    startActivity(reportIntent);
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(LoadActivity.this, MainActivity.class);
                    //passes to mainActivity the engineer
                    mainIntent.putExtra("Engineer", engineer);
                    mainIntent.putExtra("Date", date);
                    //starts main activity
                    startActivity(mainIntent);
                    finish();
                }
            }
        });

        new_eng.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Animation bounceAnimation = AnimationUtils.loadAnimation(LoadActivity.this, R.anim.bounce_animation);
                new_eng.startAnimation(bounceAnimation);
                //creates new TakeCare object for new engineer
                TakeCare engineer = new TakeCare();
                //creates new intent to go from LoadActivity to MainActivity
                Intent nameIntent = new Intent(LoadActivity.this, NameActivity.class);
                //passes to mainActivity the engineer
                nameIntent.putExtra("Engineer", engineer);
                //starts main activity
                startActivity(nameIntent);
            }
        });

    }
}