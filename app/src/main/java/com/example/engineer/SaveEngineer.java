package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SaveEngineer extends AppCompatActivity
{
    TextView tv_academic;

    float lastAcademic, lastHealth, lastSocial;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveengineer);

        tv_academic = (TextView) findViewById(R.id.tv_academic);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastAcademic = preferences.getFloat("lastAcademic",0);
        lastHealth = preferences.getFloat("lastHealth",0);
        lastSocial = preferences.getFloat("lastSocial",0);

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
