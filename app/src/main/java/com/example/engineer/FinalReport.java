package com.example.engineer;

public class FinalReport
{
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
}
