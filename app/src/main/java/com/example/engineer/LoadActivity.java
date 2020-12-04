//class for activity when prompting the user to load an old engineer or create a new one
package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
                //creates new intent to go from LoadActivity to MainActivty
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