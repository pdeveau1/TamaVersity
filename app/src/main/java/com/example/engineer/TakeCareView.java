package com.example.engineer;

import android.content.Context;
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

    private Bitmap backgroundImage;

    private TakeCare engineer = new TakeCare();


    public TakeCareView(Context context)
    {
        super(context);

        gestureDetector = new GestureDetector(context, new GestureListener());

        academic_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        health_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        social_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.dorm);
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

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        backgroundImage = Bitmap.createScaledBitmap(backgroundImage, canvasWidth, canvasHeight, true);
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        canvas.drawBitmap(academic_view, canvasWidth - engineer.getAcademic()*10,0, null);
        canvas.drawBitmap(health_view, canvasWidth - engineer.getHealth()*10,70, null);
        canvas.drawBitmap(social_view, canvasWidth - engineer.getSocial()*10,140, null);

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
        //event when single tap occurs, engineers studies
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            engineer.study();
            return true;
        }
        // event when double tap occurs, engineer socializes
        @Override
        public boolean onDoubleTapEvent(MotionEvent e)
        {
            engineer.socialize();
            return true;
        }
    }
}

//test to push - Lesly
//test to push - Ahsan
