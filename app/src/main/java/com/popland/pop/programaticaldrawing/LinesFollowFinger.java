package com.popland.pop.programaticaldrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class LinesFollowFinger extends AppCompatActivity {
Button BTN1,BTN2,BTN3,BTN4;
RelativeLayout RL;
ArrayList<Point> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines_follow_finger);
        BTN1 = (Button)findViewById(R.id.BTN1);
        BTN2 = (Button)findViewById(R.id.BTN2);
        BTN3 = (Button)findViewById(R.id.BTN3);
        BTN4 = (Button)findViewById(R.id.BTN4);
        RL = (RelativeLayout)findViewById(R.id.activity_lines_follow_finger);
        RL.addView(new Lines(this));
    }

    class Lines extends View {
        Paint paint = new Paint();
        //float[] points = new float[8];

        public Lines(Context context) {
            super(context);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(20);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Path path = new Path();

            boolean first = true;
            for(Point point : points){
                if(first){
                    first = false;
                    path.moveTo(point.x,point.y);
                }else
                    path.lineTo(point.x,point.y);
            }
            canvas.drawPath(path,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                Point point = new Point();
                point.x = event.getX();
                point.y = event.getY();
                points.add(point);
                invalidate();
                return true;
            }
            return super.onTouchEvent(event);
        }

        //        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            switch(event.getAction()){
//                case MotionEvent.ACTION_DOWN:
//                    if(event.getX()>=BTN1.getLeft() && event.getX()<=BTN1.getRight()
//                            && event.getY()>=BTN1.getTop() && event.getY()<=BTN1.getBottom()){
//                        points[0] = event.getX();
//                        points[1] = event.getY();
//                    }
//                    if(event.getX()>=BTN3.getLeft() && event.getX()<=BTN3.getRight()
//                            && event.getY()>=BTN3.getTop() && event.getY()<=BTN3.getBottom()){
//                        points[4] = event.getX();
//                        points[5] = event.getY();
//                    }
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    if(event.getX()>=BTN2.getLeft() && event.getX()<=BTN2.getRight()
//                            && event.getY()>=BTN2.getTop() && event.getY()<=BTN2.getBottom()){
//                        points[2] = event.getX();
//                        points[3] = event.getY();
//                    }
//                    if(event.getX()>=BTN4.getLeft() && event.getX()<=BTN4.getRight()
//                            && event.getY()>=BTN4.getTop() && event.getY()<=BTN4.getBottom()){
//                        points[6] = event.getX();
//                        points[7] = event.getY();
//                    }
//            }
//            invalidate();
//            return true;
//        }
    }
}
