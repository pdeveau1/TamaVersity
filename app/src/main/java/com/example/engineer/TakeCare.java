//class to update the values of the engineer
package com.example.engineer;
import java.io.Serializable;

public class TakeCare implements Serializable
{
    //minimum one of the states can be
    private static int MIN_VAL = 0;
    //maximum one of the states can be
    private static int MAX_VAL = 100;

    private float academics;
    private float health;
    private float social;
    private float totalPoints;
    private String name;


    private boolean pointCheck;

    private String currentState;

    public TakeCare()
    {
        //initially set ALL states to 20%
        academics = 20;
        health = 20;
        social = 20;
        //initially set total number of points to zero
        totalPoints = 0;

        //default name is Jimmy!
        name = "Jimmy";

        //default string state is: Say hello to Jimmy
        currentState = "Say hello to " + name;

        pointCheck = false;
    }

    //resets all data to previous collected data
    public TakeCare(float old_academics, float old_health, float old_social, String old_name)
    {
        academics = old_academics;
        health = old_health;
        social = old_social;
        totalPoints = 0;
        name = old_name;
        currentState = "Say hello to " + name;
        pointCheck = false;
    }

    //given string new_name, update name from Jimmy -> given
    public void setName(String new_name)
    {
        name = new_name;
        currentState = "Say hello to " + name;
    }

    //user clicks book button -> academics
    public void study()
    {
        academics += 6;
        totalPoints += 6;
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

    //user clicks pizza -> health
    public void eat()
    {
        health += 3;
        academics -= 1;
        totalPoints += 3;
        if(health >= MAX_VAL)
        {
            health = MAX_VAL;
        }
    }

    //user clicks friends -> social
    public void socialize()
    {
        social += 5;
        health += 2;
        totalPoints += 7;
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

    public String getCurrentState()
    {
        //if social is ahead of academics by 10, user will need to study
        if(social >= academics + 10)
        {
            currentState = "I should go study";
        }

        //if social is ahead of academics by 20, user will need to study - URGENT
        if(social >= academics + 20)
        {
            currentState = "I should REALLY go study!";
        }

        //if academics is ahead of health by 10, user will need to eat
        else if(academics >= health + 10)
        {
            currentState = "I need to go eat";
        }

        //if academics is ahead of health by 20, user will need to eat - URGENT
        else if(academics >= health + 20)
        {
            currentState = "I REALLY need to go eat :/";
        }

        //if academics is ahead of social by 15, user will need to see friends
        else if(academics >= social + 15)
        {
            currentState = "I miss my friends";
        }

        //if academics is ahead of social by 30, user will need to see friends - URGENT
        else if(academics >= social + 15)
        {
            currentState = "I REALLY miss my friends :(";
        }

        //otherwise, assume user is doing good
        else if(totalPoints != 0)
        {
            currentState = "Fine";
        }
        return currentState;
    }

    public boolean checkPoints()
    {
        if (totalPoints >= 5)
        {
            return true;
        }
        else {
            return false;
        }
        //System.out.printf("%d",totalpoints);

    }

    public void decrease()
    {
        health = health - 3;
        social = social - 2;
        academics = academics - 1;
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

    //public String getCurrentState() { return currentState; }

    public float getMax()
    {
        return MAX_VAL;
    }

    public float getMin()
    {
        return MIN_VAL;
    }

    public String getName(){return name;}
}
