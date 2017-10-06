package com.popland.pop.programaticaldrawing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Free(View v){
        setContentView(new FreeFinger(MainActivity.this));
    }

    public void Straight(View v){
        setContentView(new StraightLine(MainActivity.this));
    }

    public void Complex(View v){
        Intent i = new Intent(MainActivity.this,ComplexStraightLine.class);
        startActivity(i);
    }

    public void Multi(View v){
        setContentView(new MultiLines(MainActivity.this));
    }

    public void Lines(View v){
        Intent i = new Intent(MainActivity.this,LinesFollowFinger.class);
        startActivity(i);
    }
}
