package com.example.engineer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoadActivity extends AppCompatActivity
{
    private Button load_eng;
    private Button new_eng;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        load_eng = (Button) findViewById(R.id.load_eng);
        new_eng = (Button) findViewById(R.id.new_eng);

        load_eng.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                float academics = preferences.getFloat("lastAcademic",20);
                float health = preferences.getFloat("lastHealth",20);
                float social = preferences.getFloat("lastSocial",20);

                TakeCare engineer = new TakeCare(academics, health, social);
                Intent mainIntent = new Intent(LoadActivity.this, MainActivity.class);
                mainIntent.putExtra("Engineer", engineer);
                startActivity(mainIntent);
            }
        });

        new_eng.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TakeCare engineer = new TakeCare();
                Intent mainIntent = new Intent(LoadActivity.this, MainActivity.class);
                mainIntent.putExtra("Engineer", engineer);
                startActivity(mainIntent);
            }
        });

    }
}