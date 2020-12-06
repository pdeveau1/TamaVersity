//if user exits out of game takes to activity_end.xml to save engineer information and exit app
package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity
{
    private TakeCare engineer;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        button = (Button)findViewById(R.id.button);
        engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        //when button clicked saves data
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //animations
                Animation bounceAnimation = AnimationUtils.loadAnimation(EndActivity.this, R.anim.bounce_animation);
                button.startAnimation(bounceAnimation);
                //save data
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("lastAcademic",engineer.getAcademic());
                editor.putFloat("lastHealth",engineer.getHealth());
                editor.putFloat("lastSocial",engineer.getSocial());
                editor.apply();

                Intent save = new Intent(getApplicationContext(), SaveEngineer.class);
                startActivity(save);
                finish();
                //close app
                moveTaskToBack(true);
                finish();
            }
        });
    }
}