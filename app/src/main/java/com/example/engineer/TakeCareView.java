//creates the visuals for the game
package com.example.engineer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

public class TakeCareView extends View
{
    GestureDetector gestureDetector;

    private int canvasWidth;
    private int canvasHeight;

    private Bitmap academic_view;
    private Bitmap health_view;
    private Bitmap social_view;

    private Bitmap player;

    private Bitmap x;

    private Bitmap backgroundImage;

    private TakeCare engineer;


    public TakeCareView(Context context, TakeCare new_engineer)
    {
        super(context);
        engineer = new_engineer;

        gestureDetector = new GestureDetector(context, new GestureListener());

        //creates bitmap for the status bars
        academic_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        health_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        social_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);

        //creates engineer bitmap
        player = BitmapFactory.decodeResource(getResources(),R.drawable.student);

        //creates bitmap for exit button
        x = BitmapFactory.decodeResource(getResources(),R.drawable.x);

        //creates bitmap for background
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.dorm);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //sets values for paint
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextSize(50);


        //gets size of screen
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        //updates background image to fit entire screen
        backgroundImage = Bitmap.createScaledBitmap(backgroundImage, canvasWidth, canvasHeight, true);
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        //updates player image to be in bottom middle of screen
        player = Bitmap.createScaledBitmap(player,canvasWidth/3, canvasHeight/2, true);
        canvas.drawBitmap(player, canvasWidth/3, canvasHeight/2, null);

        //puts x in bottom right corner of screen
        x = Bitmap.createScaledBitmap(x,canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(x, canvasWidth - canvasWidth/10, canvasHeight - canvasHeight/10, null);

        //adds the status bars at the top of the screen
        canvas.drawBitmap(academic_view, canvasWidth - engineer.getAcademic()*10,0, null);
        canvas.drawBitmap(health_view, canvasWidth - engineer.getHealth()*10,70, null);
        canvas.drawBitmap(social_view, canvasWidth - engineer.getSocial()*10,140, null);

        //adds percentages for the status bars
        canvas.drawText(String.valueOf((int)engineer.getAcademic()) + "%", canvasWidth - engineer.getAcademic()*10, academic_view.getHeight()/2, paint);
        canvas.drawText(String.valueOf((int)engineer.getHealth()) + "%", canvasWidth - engineer.getHealth()*10, health_view.getHeight()/2 + 70, paint);
        canvas.drawText(String.valueOf((int)engineer.getSocial()) + "%", canvasWidth - engineer.getSocial()*10, social_view.getHeight()/2 + 140, paint);
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
        //event when single tap occurs, engineer studies
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
            else {
                engineer.study();
            }
            return true;
        }
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
        }
    }
}
