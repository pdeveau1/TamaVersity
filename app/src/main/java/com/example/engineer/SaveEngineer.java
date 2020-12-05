package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SaveEngineer extends AppCompatActivity
{

    float lastAcademic, lastHealth, lastSocial;
            String Date, Name;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveengineer);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastAcademic = preferences.getFloat("lastAcademic",100);
        lastHealth = preferences.getFloat("lastHealth",100);
        lastSocial = preferences.getFloat("lastSocial",100);
        Date = preferences.getString("Date","01-01-3000");
        Name = preferences.getString("Name", "Jimmy");
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
