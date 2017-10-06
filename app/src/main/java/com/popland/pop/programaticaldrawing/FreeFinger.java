package com.popland.pop.programaticaldrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hai on 30/08/2017.
 */

public class FreeFinger extends View {
    Paint paint = new Paint();
    Path path = new Path();

    public FreeFinger(Context context) {
        super(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);// get starting point
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);// add line from last point to specified point
                break;
        }
        invalidate();
        return true;
    }
}
