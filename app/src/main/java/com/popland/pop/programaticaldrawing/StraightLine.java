package com.popland.pop.programaticaldrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by hai on 30/08/2017.
 */

public class StraightLine extends View {
Paint paint = new Paint();
Path path = new Path();
float startX, startY, lastX, lastY;

    public StraightLine(Context context) {
        super(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX,startY,lastX,lastY,paint);//draw straight line according to finger
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                lastX = event.getX();
                lastY = event.getY();
        }
        invalidate();
        return true;
    }
}
