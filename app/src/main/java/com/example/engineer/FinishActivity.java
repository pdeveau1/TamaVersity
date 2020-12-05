package com.example.engineer;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {
    private TakeCare engineer;
    private FinalReport finalR;
    TextView socialGrade;
    TextView academicGrade;
    TextView healthGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        socialGrade = (TextView) findViewById(R.id.socialGrade);
        academicGrade = (TextView) findViewById(R.id.academicGrade);
        healthGrade = (TextView) findViewById(R.id.healthGrade);

        socialGrade.setText(finalR.calcGrade(engineer.getSocial()));
        academicGrade.setText(finalR.calcGrade(engineer.getAcademic()));
        healthGrade.setText(finalR.calcGrade(engineer.getHealth()));
    }
}