package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DateActivity extends AppCompatActivity {

//    private static final String TAG = "Date";
//    private TextView mDisplayDate;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DatePicker picker;
    Button btnGet;
    TextView tvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        tvw=(TextView)findViewById(R.id.textView1);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tvw.setText("Selected Date: "+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear());

                //saves date semester ends
                String date = picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear();
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Date",date);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), SaveEngineer.class);
                startActivity(intent);
                finish();

                //takes to main game
                Intent mainIntent = new Intent(DateActivity.this, MainActivity.class);
                //passes to mainActivity the engineer
                mainIntent.putExtra("Engineer", engineer);
                mainIntent.putExtra("Date", date);
                //starts main activity
                startActivity(mainIntent);
            }
        });
    }
}