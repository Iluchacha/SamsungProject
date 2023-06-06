package com.example.teethproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import java.util.Objects;

public class MyDraw extends View{

    public MyDraw (Context context) {
        super(context);
    }

    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.background);

    dbUpdate mDBConnector;

    float metrics = getResources().getDisplayMetrics().density;
//    int width = getResources().getDisplayMetrics().widthPixels;
//    int height = getResources().getDisplayMetrics().heightPixels;
//    int width = this.getWidth();
//    int height = this.getHeight();
//    int nHeight = (int) (444 * (width / 290));
    int width = 0;
    int height = 0;
    int nHeight = 0;
    float currentX = 0;
    float currentY = 0;
    int start = 0;
    int[][] cords = {
            {27, 250, 16}, {26, 284, 17}, {34, 319, 17}, {47, 350, 17}, {69, 378, 17}, {88, 400, 11},
            {108, 410, 10}, {132, 415, 11}, {155, 414, 11}, {178, 409, 12}, {197, 398, 12},
            {216, 376, 16}, {240, 347, 17}, {255, 315, 17}, {261, 281, 17}, {257, 247, 16},
            {22, 197, 16}, {22, 166, 15}, {31, 134, 17}, {42, 104, 16}, {56, 78, 16}, {73, 54, 13},
            {93, 35, 17}, {124, 28, 16}, {158, 28, 16}, {191, 35, 13}, {211, 57, 13}, {225, 81, 15},
            {243, 107, 16}, {254, 135, 16}, {261, 167, 17}, {258, 199, 15}
    };
    String[] names = {
            getResources().getString(R.string.wisdom), getResources().getString(R.string.molar2),
            getResources().getString(R.string.molar1), getResources().getString(R.string.premolar2),
            getResources().getString(R.string.premolar1), getResources().getString(R.string.canine),
            getResources().getString(R.string.side_incisor), getResources().getString(R.string.incisor),
            getResources().getString(R.string.incisor), getResources().getString(R.string.side_incisor), getResources().getString(R.string.canine),
            getResources().getString(R.string.premolar1), getResources().getString(R.string.premolar2),
            getResources().getString(R.string.molar1), getResources().getString(R.string.molar2),
            getResources().getString(R.string.wisdom), getResources().getString(R.string.wisdom),
            getResources().getString(R.string.molar2), getResources().getString(R.string.molar1),
            getResources().getString(R.string.premolar2), getResources().getString(R.string.premolar1),
            getResources().getString(R.string.canine), getResources().getString(R.string.side_incisor),
            getResources().getString(R.string.incisor), getResources().getString(R.string.incisor),
            getResources().getString(R.string.side_incisor), getResources().getString(R.string.canine), getResources().getString(R.string.premolar1),
            getResources().getString(R.string.premolar2), getResources().getString(R.string.molar1),
            getResources().getString(R.string.molar2), getResources().getString(R.string.wisdom), "Ротовая область"
    };

//    Bitmap image1 = Bitmap.createScaledBitmap(image, width, nHeight, false);
//    int start = (int) (height / 2 - image1.getHeight() / 2);

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        width = this.getWidth();
        height = this.getHeight();
        nHeight = (int) (444 * (width / 290));

        Bitmap image1 = Bitmap.createScaledBitmap(image, width, nHeight, false);
        start = (int) (height / 2 - image1.getHeight() / 2);

        Paint[] tPaints = {new Paint(), new Paint(), new Paint(), new Paint()};

        paint.setStyle(Paint.Style.FILL);
        tPaints[0].setStyle(Paint.Style.FILL);
        tPaints[1].setStyle(Paint.Style.FILL);
        tPaints[2].setStyle(Paint.Style.FILL);

//        System.out.println(width + " " + nHeight);
//        System.out.println((nHeight / 444) * 250 + start);
//        System.out.println(((width / 290) * 43 - (width / 290) * 10) * (3 / 2));

        canvas.drawBitmap(image1, 0, start, paint);

        paint.setAntiAlias(true);
        tPaints[0].setAntiAlias(true);
        tPaints[1].setAntiAlias(true);
        tPaints[2].setAntiAlias(true);

        paint.setColor(Color.rgb(0,133,119));
        tPaints[0].setColor(Color.GREEN);
        tPaints[1].setColor(Color.RED);
        tPaints[2].setColor(Color.DKGRAY);

        paint.setTextSize(75);
        paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
//        canvas.drawText("СТОМАТОЛОГИЧЕСКАЯ", 100, 200, paint);
//        canvas.drawText("КАРТА", 400, 300, paint);

        paint.setTextSize(40);
//        canvas.drawCircle(75,1900, (nHeight / 444) * 10, tPaints[0]);
//        canvas.drawText("- вылечен", 125, 1910, paint);
//
//        canvas.drawCircle(75,2000, (nHeight / 444) * 10, tPaints[2]);
//        canvas.drawText("- нуждается в лечении", 125, 2010, paint);
//
//        canvas.drawCircle(75,2100, (nHeight / 444) * 10, tPaints[3]);
//        canvas.drawText("- удалён", 125, 2110, paint);

//        canvas.drawCircle((float) (width / 4), 300, 100, paint);
//        canvas.drawCircle((float) (width / 4) * 3, 300, 100, paint);

        //canvas.drawOval(new RectF((int) ((width / 290.0) * 43), (nHeight / 444) * 45  + start, (int) ((width / 290.0) * 238), (nHeight / 444) * 400 + start), paint);
         mDBConnector = new dbUpdate(this.getContext());
        for (int i = 0; i < 32; i++) {
            //canvas.drawCircle((int) ((width / 290.0) * cords[i][0]), cords[i][1] * (nHeight / 444) + start, (nHeight / 444) * cords[i][2], paint);
            int st = mDBConnector.getStatus(i);
            if (st != -1)
                canvas.drawCircle((int) ((width / 290.0) * cords[i][0]), cords[i][1] * (nHeight / 444) + start, (nHeight / 444) * 10, tPaints[st]);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            System.out.println("\n" + event.getX() + " " + event.getY());
            currentX = event.getX();
            currentY = event.getY();
        }
        return false;
    }

    int isClicked(float x, float y){
        for (int i = 0; i < 32; i++) {
            //double oX = (width / 290.0) * cords[i][0];
            //double oY = cords[i][1] * (nHeight / 444) + start;
            double oX = (width / 290.0) * cords[i][0];
            double oY = cords[i][1] * (nHeight / 444);
            double r = (nHeight / 444) * cords[i][2];
            if ((oX - x) * (oX - x) + (oY - y) * (oY - y) <= r * r)
                return i;
            else if (Objects.equals(names[i], "Ротовая область") && (x * x) / (oX * oX) + (y * y) / (oY * oY) <= 1)
                return i;
        }
        return 100;
    }

    String getName(int i){
        return names[i];
    }

    }

