package com.example.engineer;

public class TakeCare
{
    //minimum one of the states can be
    private static int MIN_VAL = 0;
    //maximum one of the states can be
    private static int MAX_VAL = 1000;

    //sets all states initially to max the states can be
    private float academics;
    private float health;
    private float social;

    private String currentState;

    public TakeCare()
    {
        academics = MAX_VAL;
        health = MAX_VAL;
        social = MAX_VAL;
        currentState = "fine";
    }
    //drop book to engineer it studies
    public void study()
    {
        academics += 3;
        health -= 1;
        social -= 2;

        if(academics > MAX_VAL)
        {
            academics = MAX_VAL;
        }
        if(health < MIN_VAL)
        {
            health = MIN_VAL;
        }
        if(social < MIN_VAL)
        {
            social = MIN_VAL;
        }
    }

    //feed engineer it eats
    public void eat()
    {
        health += 3;
        if(health > MAX_VAL)
        {
            health = MAX_VAL;
        }
    }

    //give engineer a friend it socializes
    public void socialize()
    {
        social += 2;
        health += 1;
        academics -= 2;

        if (social > MAX_VAL)
        {
            social = MAX_VAL;
        }
        if(health < MIN_VAL)
        {
            health = MIN_VAL;
        }
        if(academics < MIN_VAL)
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

}
