package com.example.teethproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends Activity{

    Button teeth2;
    private MyDraw gt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        gt = new MyDraw(this);
        setContentView(gt);
        //setContentView(R.layout.activity_main);


//
//        float metrics = getResources().getDisplayMetrics().density;
//
//        teeth2 = findViewById(R.id.teeth2);
////        teeth2.setScaleX(32 * metrics);
////        teeth2.setScaleY(32 * metrics);
//        teeth2.setX(15 * metrics);
//        teeth2.setY(387 * metrics);
//        teeth2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("\n" + metrics + "\n" + teeth2.getScaleX() + "\n" + teeth2.getScaleY() + "\n");
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(gt);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN){
//            float metrics = getResources().getDisplayMetrics().density;
            System.out.println("\n" + event.getX() + " " + event.getY());
            int v = gt.isClicked(event.getX(), event.getY() - 100);
            Intent i = new Intent(MainActivity.this, TeethActivity.class);
            if (v != 100){
                System.out.println("\nYES\n");
                Toast.makeText(this, Integer.toString(v), Toast.LENGTH_SHORT).show();
                i.putExtra("teethId", v);
                i.putExtra("teethName", gt.getName(v));
                startActivity(i);
            }
            else{
                System.out.println("\nNO\n");
                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }



}