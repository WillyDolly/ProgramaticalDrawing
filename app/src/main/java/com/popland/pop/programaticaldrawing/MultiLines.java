package com.popland.pop.programaticaldrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hai on 05/09/2017.
 */

public class MultiLines extends View {
    Paint paint = new Paint();
    float[] points =  {0,0,500,500,100,700,200,500} ;

    public MultiLines(Context context){
        super(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawLines(points,paint); // points[0] - points[1]....points[2]-points[3]

        //Automatically draw continuous lines
        Path path = new Path();
        path.moveTo(points[0],points[1]);
        for(int i=2;i<points.length;i+=2){
            path.lineTo(points[i],points[i+1]);
        }
        canvas.drawPath(path,paint);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        invalidate();
//        return true;
//    }
}
