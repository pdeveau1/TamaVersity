package com.example.engineer;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.*;

public class TakeCare implements Serializable
{
    //minimum one of the states can be
    private static int MIN_VAL = 0;
    //maximum one of the states can be
    private static int MAX_VAL = 100;

    //sets all states initially to max the states can be
    private float academics;
    private float health;
    private float social;
    private float totalpoints;

    private boolean pointCheck;

    private String currentState;

    public TakeCare()
    {
        academics = 20;
        health = 20;
        social = 20;
        totalpoints = 0;
        currentState = "fine";
        pointCheck = false;
    }

    public TakeCare(float old_academics, float old_health, float old_social)
    {
        academics = old_academics;
        health = old_health;
        social = old_social;
        totalpoints = 0;
        currentState = "fine";
        pointCheck = false;
    }

    //drop book to engineer it studies
    public void study()
    {
        academics += 3;
        totalpoints += 3;
        health -= 1;
        social -= 2;

        if(academics >= MAX_VAL)
        {
            academics = MAX_VAL;
        }
        if(health <= MIN_VAL)
        {
            health = MIN_VAL;
        }
        if(social <= MIN_VAL)
        {
            social = MIN_VAL;
        }
    }

    //feed engineer it eats
    public void eat()
    {
        health += 3;
        totalpoints += 3;
        if(health >= MAX_VAL)
        {
            health = MAX_VAL;
        }
    }

    //give engineer a friend it socializes
    public void socialize()
    {
        social += 2;
        health += 1;
        totalpoints += 3;
        academics -= 2;

        if (social >= MAX_VAL)
        {
            social = MAX_VAL;
        }
        if(health >= MAX_VAL)
        {
            health = MAX_VAL;
        }
        if(academics <= MIN_VAL)
        {
            academics = MIN_VAL;
        }
    }

    public void CurrentState()
    {
        //if social is greater than academics by 10 will need to study
        if(social >= academics + 10)
        {
            currentState = "I should go study";
        }
        //if academics is greater than health by 10 will need to eat
        else if(academics >= health + 10)
        {
            currentState = "I need to go eat";
        }
        //if academics is greater than social by 15 will need to see friends
        else if(academics >= social + 15)
        {
            currentState = "I should take a break and go see my friends";
        }
        //otherwise doing good
        else
        {
            currentState = "Fine";
        }
    }

    public boolean checkPoints()
    {
        if (totalpoints >= 5)
        {
            return true;
        }
        else {
            return false;
        }
        //System.out.printf("%d",totalpoints);

    }

    public float getAcademic()
    {
            return academics;
    }

    public float getHealth()
    {
        return health;
    }

    public float getSocial()
    {
        return social;
    }

    public String getCurrentState() { return currentState; }

    public float getMax()
    {
        return MAX_VAL;
    }

    public float getMin()
    {
        return MIN_VAL;
    }
}
