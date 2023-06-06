package com.example.teethproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity{

    LinearLayout teethClick;
    Button btnClinic, btnDentist;
    private MyDraw gt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        gt = new MyDraw(this);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        teethClick = findViewById(R.id.teethClick);
        btnClinic = findViewById(R.id.btnClinic);
        btnDentist = findViewById(R.id.btnDentist);
        teethClick.addView(gt);
        Intent j = new Intent(MainActivity.this, ClinicActivity.class);
        Intent d = new Intent(MainActivity.this, DentistActivity.class);

        btnClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(j);
            }
        });

        btnDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(d);
            }
        });





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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        setContentView(R.layout.activity_main);
//        teethClick = findViewById(R.id.teethClick);
//        gt = new MyDraw(this);
//        teethClick.addView(gt);
//
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN){
//            float metrics = getResources().getDisplayMetrics().density;
            System.out.println("\n" + event.getX() + " " + event.getY());
            int v = gt.isClicked(gt.currentX , gt.currentY - gt.start);
//            int v = gt.isClicked(event.getX(), teethClick.getY() - 100);
            //Toast.makeText(this, Integer.toString(teethClick.getLeft()) + " " + teethClick.getTop(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, TeethActivity.class);
            if (v != 100){
                System.out.println("\nYES\n");
                i.putExtra("teethId", v);
                i.putExtra("teethName", gt.getName(v));
                startActivity(i);
            }
            else{
                System.out.println("\nNO\n");
            }
        }
        return true;
    }



}