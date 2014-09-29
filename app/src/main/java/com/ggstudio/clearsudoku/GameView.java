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
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

        drawCanvas.drawLine(oneThird, 0, oneThird, whole, thickLine);
        drawCanvas.drawLine(oneThird+oneThird, 0, oneThird+oneThird, whole, thickLine);
        drawCanvas.drawLine(0, oneThird, whole, oneThird, thickLine);
        drawCanvas.drawLine(0,oneThird+oneThird,whole, oneThird + oneThird, thickLine);

        //drawing lines
        /*for (int j=0;j<3;j++){
            for (int i=0; i<9; i++){
                drawCanvas.drawLine(oneThird * j + oneNinth + oneNinth * i, 0, oneThird * j + oneNinth + oneNinth*i, whole, thinLine);
                drawCanvas.drawLine(0,oneThird * j + oneNinth + oneNinth * i,whole, oneThird * j + oneNinth + oneNinth * i, thinLine);
            }
        }*/

        for (int i=0; i<9; i++){
            drawCanvas.drawLine(oneNinth + oneNinth * i, 0, oneNinth + oneNinth*i, whole, thinLine);
            drawCanvas.drawLine(0,oneNinth + oneNinth * i,whole, oneNinth + oneNinth * i, thinLine);
        }

    }

    /*public void gameFieldDrawing(){

        //draw gamefield lines
        for (int i=0; i<2; i++){
            drawCanvas.drawLine(xOneThird+xOneThird*i,0,xOneThird+xOneThird*i,yWhole,thinLine);
            drawCanvas.drawLine(yOneThird+yOneThird*i,0,yOneThird+yOneThird*i,xWhole,thinLine);
        }

    }*/




}
