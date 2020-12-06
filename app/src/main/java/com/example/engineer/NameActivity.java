//This activity loads activity_name.xml and asks the user to submit the name of the engineer and will go to DateActivity so the user can enter the date
package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {
    String name;
    Button submitButton;
    EditText nameInput;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);


        nameInput = (EditText) findViewById(R.id.nameInput);
        submitButton = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounceAnimation = AnimationUtils.loadAnimation(NameActivity.this, R.anim.bounce_animation);
                submitButton.startAnimation(bounceAnimation);
                //gets input
                name = nameInput.getText().toString();

                //saves input to name
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Name",name);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), SaveEngineer.class);
                startActivity(intent);
                finish();

                TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");
                engineer.setName(name);
                //loads DateActivity
                Intent dateIntent = new Intent(NameActivity.this, DateActivity.class);
                //passes to DateActivity the engineer
                dateIntent.putExtra("Engineer", engineer);
                startActivity(dateIntent);
            }
        });
    }
    private void showToast(String text)
    {
        Toast.makeText(NameActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}