package com.example.engineer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndActivity extends AppCompatActivity
{
    private TakeCare engineer;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        button = (Button) findViewById(R.id.button);
        TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("lastAcademic",engineer.getAcademic());
                editor.putFloat("lastHealth",engineer.getHealth());
                editor.putFloat("lastSocial",engineer.getSocial());
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), SaveEngineer.class);
                startActivity(intent);
                finish();
            }
        });
    }
}