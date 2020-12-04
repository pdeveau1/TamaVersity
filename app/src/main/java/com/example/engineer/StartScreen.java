package com.example.engineer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

//LOGO SCREEN
public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread thread = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(5000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    //Following Screen -> Load/New Screen
                    Intent loadIntent = new Intent(StartScreen.this, LoadActivity.class);
                    startActivity(loadIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}