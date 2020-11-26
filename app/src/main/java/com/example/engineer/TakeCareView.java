package com.example.engineer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        backgroundImage = Bitmap.createScaledBitmap(backgroundImage, canvasWidth,
                canvasHeight, true);
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        canvas.drawBitmap(academic_view, canvasWidth - engineer.getAcademic(),0, null);
        canvas.drawBitmap(health_view, canvasWidth - engineer.getHealth(),70, null);
        canvas.drawBitmap(social_view, canvasWidth - engineer.getSocial(),140, null);
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
        //event when single tap occurs
        @Override
        public boolean onSingleTapUp(MotionEvent e)
        {
            engineer.study();
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e)
        {
            engineer.socialize();
            return true;
        }
    }
}

