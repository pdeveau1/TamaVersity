package com.example.engineer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

public class TakeCareView extends View
{
    GestureDetector gestureDetector;

    private int canvasWidth;
    private int canvasHeight;

    private String date;

    private Bitmap academic_view;
    private Bitmap health_view;
    private Bitmap social_view;

    private Bitmap player;

    private Bitmap x;

    private Bitmap backgroundImage;

    //testing UI buttons
    private Bitmap studyButton;
    private Bitmap eatButton;
    private Bitmap socialButton;

    private Bitmap studyBar;
    private Bitmap eatBar;
    private Bitmap socialBar;

    private Bitmap think;

    private TakeCare engineer;


    public TakeCareView(Context context, TakeCare new_engineer, String new_date)
    {
        super(context);
        engineer = new_engineer;
        date = new_date;
        gestureDetector = new GestureDetector(context, new GestureListener());

        //creates bitmap for the status bars
        academic_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        health_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        social_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);

        //creates engineer bitmap
        player = BitmapFactory.decodeResource(getResources(),R.drawable.student);

        //creates bitmap for exit button
        x = BitmapFactory.decodeResource(getResources(),R.drawable.redx);

        //creates bitmap for background
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.dorm);

        studyButton = BitmapFactory.decodeResource(getResources(), R.drawable.book);
        eatButton = BitmapFactory.decodeResource(getResources(), R.drawable.food);
        socialButton = BitmapFactory.decodeResource(getResources(), R.drawable.social);

        think = BitmapFactory.decodeResource(getResources(), R.drawable.think);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //paint.style = Paint.Style.FILL;
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextSize(50);

        Paint paintEng = new Paint();
        paintEng.setColor(Color.WHITE);
        paintEng.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paintEng.setTextSize(100);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        backgroundImage = Bitmap.createScaledBitmap(backgroundImage, canvasWidth, canvasHeight, true);
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        player = Bitmap.createScaledBitmap(player,canvasWidth, canvasHeight, true);
        canvas.drawBitmap(player, 0, canvasWidth/4, null);

        x = Bitmap.createScaledBitmap(x,canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(x, canvasWidth - canvasWidth/10, canvasHeight - canvasHeight/10, null);

        //button for studying
        studyButton = Bitmap.createScaledBitmap(studyButton, canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(studyButton,  canvasWidth/15, canvasHeight - canvasHeight/9, null);
        //button for health
        eatButton = Bitmap.createScaledBitmap(eatButton, canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(eatButton, canvasWidth/6, canvasHeight - canvasHeight/9, null);
        //button for social
        socialButton = Bitmap.createScaledBitmap(socialButton, canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(socialButton, canvasWidth/4, canvasHeight - canvasHeight/9, null);

        canvas.drawBitmap(academic_view, canvasWidth - engineer.getAcademic()*10,0, null);
        canvas.drawBitmap(health_view, canvasWidth - engineer.getHealth()*10,70, null);
        canvas.drawBitmap(social_view, canvasWidth - engineer.getSocial()*10,140, null);

        canvas.drawText(String.valueOf((int)engineer.getAcademic()) + "%", canvasWidth - engineer.getAcademic()*10, academic_view.getHeight()/2, paint);
        canvas.drawText(String.valueOf((int)engineer.getHealth()) + "%", canvasWidth - engineer.getHealth()*10, health_view.getHeight()/2 + 70, paint);
        canvas.drawText(String.valueOf((int)engineer.getSocial()) + "%", canvasWidth - engineer.getSocial()*10, social_view.getHeight()/2 + 140, paint);

        studyBar = Bitmap.createScaledBitmap(studyButton, canvasWidth/30, canvasHeight/40, true);
        canvas.drawBitmap(studyBar, canvasWidth - engineer.getAcademic()*10 + 90,0, null);
        eatBar = Bitmap.createScaledBitmap(eatButton, canvasWidth/30, canvasHeight/40, true);
        canvas.drawBitmap(eatBar, canvasWidth - engineer.getHealth()*10 + 90,academic_view.getHeight(), null);
        socialBar = Bitmap.createScaledBitmap(socialButton, canvasWidth/30, canvasHeight/40, true);
        canvas.drawBitmap(socialBar, canvasWidth - engineer.getSocial()*10 + 90,academic_view.getHeight() + health_view.getHeight(), null);

        canvas.drawBitmap(think, canvasWidth/3,0, null);
        canvas.drawText(engineer.getCurrentState(), canvasWidth/2, canvasHeight/4, paintEng);
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            engineer.study();
        }
        return true;
    }*/
   // delegate the event to the gesture detector
   @Override
   public boolean onTouchEvent(MotionEvent e)
   {
       return gestureDetector.onTouchEvent(e);
   }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onDown(MotionEvent e)
        {
            return true;
        }
        //event when single tap occurs, engineers studies
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            int mouse_x = (int)e.getX();
            int mouse_y = (int)e.getY();
            if(mouse_x > canvasWidth - canvasWidth/10 && mouse_y > canvasHeight - canvasHeight/10)
            {
                Intent endIntent = new Intent(getContext(), EndActivity.class);
                endIntent.putExtra("Engineer", engineer);
                endIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(endIntent);
            }
            //study button
            else if(mouse_x > canvasWidth/15 && mouse_x < canvasWidth/6 && mouse_y > canvasHeight - canvasHeight/9)
            {
                engineer.study();
            }
            //health button
            else if(mouse_x > canvasWidth/6 && mouse_x < canvasWidth/4 && mouse_y > canvasHeight - canvasHeight/9)
            {
                engineer.eat();
            }
            //social button
            else if(mouse_x > canvasWidth/4 && mouse_x < canvasWidth/3 && mouse_y > canvasHeight - canvasHeight/9)
            {
                engineer.socialize();
            }
            return true;
        }
        /*
        // event when double tap occurs, engineer socializes
        @Override
        public boolean onDoubleTapEvent(MotionEvent e)
        {
            engineer.socialize();
            return true;
        }
        // event when long tap occurs, engineer eats
        public void onLongPress(MotionEvent e)
        {
            engineer.eat();
        }*/
    }
}

//test to push - Lesly
//test to push - Ahsan
