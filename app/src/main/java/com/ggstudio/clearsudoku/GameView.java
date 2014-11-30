package com.ggstudio.clearsudoku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Andrey on 26.09.2014.
 */
public class GameView extends View {

    private Paint canvasPaint, thickLine, thinLine, drawPaint;
    private Path borderPath, drawPath;
    private Bitmap canvasBitmap;
    private Canvas drawCanvas;

    private float oneNinth, oneThird, whole;
    private String TAG = "myLog";

    public GameView (Context context, AttributeSet attrs){
        super(context, attrs);
        onSetup();
        //gameFieldDrawing();
    }

    //Custom method similar to onCreate()
    public void onSetup() {
        thickLine = new Paint();
        thinLine = new Paint();
        borderPath = new Path();

        drawPaint = new Paint();
        drawPath = new Path();

        thickLine.setColor(Color.BLACK);
        thickLine.setAntiAlias(true);
        thickLine.setStrokeWidth(7);
        thickLine.setStyle(Paint.Style.STROKE);
        //thickLine.setStrokeJoin(Paint.Join.BEVEL);
        thickLine.setStrokeCap(Paint.Cap.SQUARE);

        thinLine.setColor(Color.BLACK);
        thinLine.setAntiAlias(true);
        thinLine.setStrokeWidth(3);
        thinLine.setStyle(Paint.Style.STROKE);
       // thinLine.setStrokeJoin(Paint.Join.BEVEL);
        thinLine.setStrokeCap(Paint.Cap.SQUARE);
        Log.d("MYTAG","OK!!!");

    }

    @Override
    public void onSizeChanged (int w, int h, int oldW, int oldH){
        super.onSizeChanged(w, h, oldW, oldH);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        whole = drawCanvas.getWidth();
        oneThird = drawCanvas.getWidth() / 3;
        oneNinth = drawCanvas.getWidth() / 9;

    }

    @Override
    protected void onDraw (Canvas canvas) {

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

        thinLine.setTextSize(58);
        drawCanvas.drawText("7", oneThird + oneNinth/3, oneThird + oneNinth * 4/5, thinLine);
//        Paint paint = new Paint();
//        //canvas.drawPaint(paint);
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(16);
//        canvas.drawText("9", 2*oneThird, 2*oneThird, paint);
        /*
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

        drawCanvas.drawLine(oneThird, 0, oneThird, whole, thickLine);
        drawCanvas.drawLine(oneThird+oneThird, 0, oneThird+oneThird, whole, thickLine);
        drawCanvas.drawLine(0, oneThird, whole, oneThird, thickLine);
        drawCanvas.drawLine(0,oneThird+oneThird,whole, oneThird + oneThird, thickLine);

        //drawing lines

        for (int i=0; i<9; i++){
            drawCanvas.drawLine(oneNinth + oneNinth * i, 0, oneNinth + oneNinth*i, whole, thinLine);
            drawCanvas.drawLine(0,oneNinth + oneNinth * i,whole, oneNinth + oneNinth * i, thinLine);
        }*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        int flagX = 0;
        int flagY = 0;
        //Toast.makeText(getContext(), "OK!", Toast.LENGTH_SHORT);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (touchX < oneThird) {
                    if (touchX < oneNinth){
                        flagX = 1;
                    } else if (touchX < 2*oneNinth){
                        flagX = 2;
                    } else {
                        flagX = 3;
                    }
                } else if (touchX < 2*oneThird){
                    if (touchX < 4*oneNinth){
                        flagX = 4;
                    } else if (touchX < 5*oneNinth){
                        flagX = 5;
                    } else {
                        flagX = 6;
                    }
                } else {
                    if (touchX < 7*oneNinth){
                        flagX = 7;
                    } else if (touchX < 8*oneNinth){
                        flagX = 8;
                    } else {
                        flagX = 9;
                    }
                }

                // Y now************************

                if (touchY < oneThird) {
                    if (touchY < oneNinth){
                        flagY = 1;
                    } else if (touchY < 2*oneNinth){
                        flagY = 2;
                    } else {
                        flagY = 3;
                    }
                } else if (touchY < 2*oneThird){
                    if (touchY < 4*oneNinth){
                        flagY = 4;
                    } else if (touchY < 5*oneNinth){
                        flagY = 5;
                    } else {
                        flagY = 6;
                    }
                } else {
                    if (touchY < 7*oneNinth){
                        flagY = 7;
                    } else if (touchY < 8*oneNinth){
                        flagY = 8;
                    } else {
                        flagY = 9;
                    }
                }

                Toast.makeText(getContext(), Integer.toString(flagX) + Integer.toString(flagY), Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;

        }
        return true;
    }

    /*public void gameFieldDrawing(){

        //draw gamefield lines
        for (int i=0; i<2; i++){
            drawCanvas.drawLine(xOneThird+xOneThird*i,0,xOneThird+xOneThird*i,yWhole,thinLine);
            drawCanvas.drawLine(yOneThird+yOneThird*i,0,yOneThird+yOneThird*i,xWhole,thinLine);
        }

    }*/




}
