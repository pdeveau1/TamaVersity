package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {
    DatePicker picker;
    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        picker=(DatePicker)findViewById(R.id.datePicker1);
        btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Animation bounceAnimation = AnimationUtils.loadAnimation(DateActivity.this, R.anim.bounce_animation);
                btnGet.startAnimation(bounceAnimation);
                int day  = picker.getDayOfMonth();
                int month= picker.getMonth();
                int year = picker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(calendar.getTime());
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Date",date);
                editor.apply();
                if(date.compareTo(currentDate) <= 0)
                {
                    Intent finishIntent = new Intent(DateActivity.this, FinishActivity.class);
                    finishIntent.putExtra("Engineer", engineer);
                    startActivity(finishIntent);
                }
                else {
                     //takes to main game
                    Intent mainIntent = new Intent(DateActivity.this, MainActivity.class);
                    //passes to mainActivity the engineer
                    mainIntent.putExtra("Engineer", engineer);
                    mainIntent.putExtra("Date", date);
                    //starts main activity
                    startActivity(mainIntent);
                }
            }
        });
    }
}