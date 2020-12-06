//When the semester ends loads activity_finish.xml and displays grades for academics, social, and health
package com.example.engineer;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {
    private TakeCare engineer;
    TextView socialGrade;
    TextView academicGrade;
    TextView healthGrade;

    //grade distribution
    String calcGrade(float grade)
    {
        int score = (int)grade;
        switch(score/10)
        {
            case 10:
                return "A+";
            case 9:
                return "A";
            case 8:
                return "B";
            case 7:
                return "C";
            case 6:
                return "D";
            default:
                return "F";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_finish);
        engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        socialGrade = (TextView) findViewById(R.id.socialGrade);
        academicGrade = (TextView) findViewById(R.id.academicGrade);
        healthGrade = (TextView) findViewById(R.id.healthGrade);

        //set grade values for textView
        socialGrade.setText(this.calcGrade(engineer.getSocial()));
        academicGrade.setText(this.calcGrade(engineer.getAcademic()));
        healthGrade.setText(this.calcGrade(engineer.getHealth()));
    }

    //when screen clicked close app
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        moveTaskToBack(true);
        finish();
        return false;
    }
}