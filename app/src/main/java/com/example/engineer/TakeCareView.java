//class that creates the view for the mainActivity with graphics to play the game
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

    private Bitmap studyButton;
    private Bitmap eatButton;
    private Bitmap socialButton;

    private Bitmap studyBar;
    private Bitmap eatBar;
    private Bitmap socialBar;

    private Bitmap think;
    private Bitmap write_think;
    private TakeCare engineer;

    private int count;

    public Bitmap writeOnDrawable(int drawableId, String text){
        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        bm = Bitmap.createScaledBitmap(think, canvasWidth/3, canvasHeight/3, true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(35);
        Canvas canvas = new Canvas(bm);
        canvas.drawText(text, bm.getWidth()/8, bm.getHeight()/2, paint);
        return bm;
    }


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
        x = BitmapFactory.decodeResource(getResources(),R.drawable.redx);

        //creates bitmap for background
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.dorm);

        //creates bitmap for studying button
        studyButton = BitmapFactory.decodeResource(getResources(), R.drawable.book);

        //creates bitmap for eating button
        eatButton = BitmapFactory.decodeResource(getResources(), R.drawable.food);

        //creates bitmap for socializing button
        socialButton = BitmapFactory.decodeResource(getResources(), R.drawable.social);

        //creates bitmap for thinking
        think = BitmapFactory.decodeResource(getResources(), R.drawable.think);

        count = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextSize(50);

        Paint paintEng = new Paint();
        paintEng.setColor(Color.BLACK);
        paintEng.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paintEng.setTextSize(50);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        //draws background
        backgroundImage = Bitmap.createScaledBitmap(backgroundImage, canvasWidth, canvasHeight, true);
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        //draws player
        player = Bitmap.createScaledBitmap(player,canvasWidth, canvasHeight, true);
        canvas.drawBitmap(player, 0, canvasWidth/4, null);

        //draws exit button
        x = Bitmap.createScaledBitmap(x,canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(x, canvasWidth - canvasWidth/10, canvasHeight - canvasHeight/10, null);

        //draws button for studying
        studyButton = Bitmap.createScaledBitmap(studyButton, canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(studyButton,  canvasWidth/15, canvasHeight - canvasHeight/9, null);

        //draws button for health
        eatButton = Bitmap.createScaledBitmap(eatButton, canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(eatButton, canvasWidth/6, canvasHeight - canvasHeight/9, null);

        //draws button for social
        socialButton = Bitmap.createScaledBitmap(socialButton, canvasWidth/10, canvasHeight/10, true);
        canvas.drawBitmap(socialButton, canvasWidth/4, canvasHeight - canvasHeight/9, null);

        //draws 3 progress bars: 1 for academics, 1 for health, and 1 for social
        canvas.drawBitmap(academic_view, canvasWidth - engineer.getAcademic()*10,0, null);
        canvas.drawBitmap(health_view, canvasWidth - engineer.getHealth()*10,70, null);
        canvas.drawBitmap(social_view, canvasWidth - engineer.getSocial()*10,140, null);

        //draws 3 percentages, 1 for each bar
        canvas.drawText(String.valueOf((int)engineer.getAcademic()) + "%", canvasWidth - engineer.getAcademic()*10, academic_view.getHeight()/2, paint);
        canvas.drawText(String.valueOf((int)engineer.getHealth()) + "%", canvasWidth - engineer.getHealth()*10, health_view.getHeight()/2 + 70, paint);
        canvas.drawText(String.valueOf((int)engineer.getSocial()) + "%", canvasWidth - engineer.getSocial()*10, social_view.getHeight()/2 + 140, paint);

        //draws additional small icons to 3 bars
        studyBar = Bitmap.createScaledBitmap(studyButton, canvasWidth/30, canvasHeight/40, true);
        canvas.drawBitmap(studyBar, canvasWidth - engineer.getAcademic()*10 + 90,0, null);
        eatBar = Bitmap.createScaledBitmap(eatButton, canvasWidth/30, canvasHeight/40, true);
        canvas.drawBitmap(eatBar, canvasWidth - engineer.getHealth()*10 + 90,academic_view.getHeight(), null);
        socialBar = Bitmap.createScaledBitmap(socialButton, canvasWidth/30, canvasHeight/40, true);
        canvas.drawBitmap(socialBar, canvasWidth - engineer.getSocial()*10 + 90,academic_view.getHeight() + health_view.getHeight(), null);

        //draws thinking bubble
        write_think = writeOnDrawable(R.drawable.think, engineer.getCurrentState());
        canvas.drawBitmap(write_think, 2*canvasWidth/3 + 12,canvasHeight/7, null);

        if(count != 300)
        {
            count++;
        }
        else
        {
            engineer.decrease();
            count = 0;
        }
    }

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
    }
}

