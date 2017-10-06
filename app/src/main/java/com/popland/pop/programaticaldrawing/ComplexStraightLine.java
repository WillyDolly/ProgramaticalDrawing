package com.popland.pop.programaticaldrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ComplexStraightLine extends AppCompatActivity {
RelativeLayout RL;
Button BTNa, BTNb;
TextView tv;
int[] locA, locB ;
float startX, startY, lastX, lastY;
Line line, line1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_finger_draw);
        RL = (RelativeLayout) findViewById(R.id.activity_free_finger_draw);
        tv = (TextView)findViewById(R.id.TV);
        BTNa = (Button)findViewById(R.id.BTNa);
        locA = new int[2];

        BTNb = (Button)findViewById(R.id.BTNb);
        locB = new int[2];

        line = new Line(ComplexStraightLine.this);
        BTNa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                BTNa.getLocationInWindow(locA);
                BTNb.getLocationInWindow(locB);
                if(line.getParent()!=null)//where is the line's first parent?
                    ((ViewGroup)line.getParent()).removeView(line);
                RL.addView(line);//should in OnCreate directly to have seamless line drawing
                startX = ( BTNa.getLeft() + BTNa.getRight() )/2;
                startY = ( BTNa.getTop() + BTNa.getBottom() )/2;
                return false;
            }
        });

        line1 = new Line(this);
        BTNb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(line1.getParent()!=null)
                    ((ViewGroup)line1.getParent()).removeView(line1);
                RL.addView(line1);
                return false;
            }
        });
    }


    class Line extends View {
        Paint paint = new Paint();

        public Line(Context context) {
            super(context);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.GREEN);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawLine(startX,startY,lastX,lastY,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = ( BTNb.getLeft() + BTNb.getRight() )/2;
                    startY = ( BTNb.getTop() + BTNb.getBottom() )/2;
                    lastX = startX;
                    lastY = startY;
                    break;
                case MotionEvent.ACTION_MOVE:
                        lastX = event.getX();
                        lastY = event.getY();
                        if(lastX>BTNb.getLeft() && lastY>BTNb.getTop() && lastY<BTNb.getBottom()) {//collision detection
                            tv.setVisibility(VISIBLE);
                            lastX = ( BTNb.getLeft() + BTNb.getRight() )/2;
                            lastY = ( BTNb.getTop() + BTNb.getBottom() )/2;
                        }else
                            tv.setVisibility(INVISIBLE);

                    break;
            }
            invalidate();//if line visible, onDraw can be called in future
            return true;
        }

    }

}
