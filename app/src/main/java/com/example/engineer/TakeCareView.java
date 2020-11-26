package com.example.engineer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

public class TakeCareView extends View
{
    private Bitmap academic_view;
    private Bitmap health_view;
    private Bitmap social_view;

    private TakeCare engineer = new TakeCare();

    public TakeCareView(Context context)
    {
        super(context);

        academic_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        health_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        social_view = BitmapFactory.decodeResource(getResources(), R.drawable.line);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        academic_view.setHeight(10);
        health_view.setHeight(10);
        social_view.setHeight(10);

        canvas.drawBitmap(academic_view, engineer.getAcademic(),0, null);
        canvas.drawBitmap(health_view, engineer.getHealth(),20, null);
        canvas.drawBitmap(social_view, engineer.getSocial(),40, null);
    }
}
