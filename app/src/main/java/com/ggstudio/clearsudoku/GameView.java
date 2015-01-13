package com.ggstudio.clearsudoku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by andrey on 12.01.15.
 */
public class GameView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private float fieldSize, oneNinthFieldSize, touchX, touchY;
    private int cellX, cellY;
    private GameActivity gameActivity;

    public GameView (Context context, AttributeSet attrs){
        super(context, attrs);
        mPaint = new Paint(Paint.DITHER_FLAG);
        gameActivity = new GameActivity();
    }

    public void drawField (){
        fieldSize = mCanvas.getHeight(); // Узнаем размер поля (должно вызыватся после размерных преоброзований).
        oneNinthFieldSize = fieldSize/9;

        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        mCanvas.drawBitmap(mBitmap, mBitmap.getWidth(), mBitmap.getHeight(), mPaint);
        mCanvas.drawRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight(), mPaint);

        // Отрисовка линий игрового поля
        for (int x=0;x<9;x++){
            if (x==3 | x==6) {
                mPaint.setStrokeWidth(5);
                mCanvas.drawLine((float) x * fieldSize / 9, 0, (float) x * fieldSize / 9, fieldSize, mPaint);
                mCanvas.drawLine(0, (float) x * fieldSize / 9, fieldSize, (float) x * fieldSize / 9, mPaint);
            } else {
                mPaint.setStrokeWidth(3);
                mCanvas.drawLine((float) x * fieldSize / 9, 0, (float) x * fieldSize / 9, fieldSize, mPaint);
                mCanvas.drawLine(0, (float) x * fieldSize / 9, fieldSize, (float) x * fieldSize / 9, mPaint);
            }
        }
    }

    public void drawNumberInCell(String number){
        //Определим ячейку
        cellX = 8;
        cellY = 8;
        //ИНВЕРСИЯ!!! Потому что в матрице сначала идёт вверх номер строки!
        for (int i=0;i<9;i++){
            if (touchX < oneNinthFieldSize + i*(oneNinthFieldSize)){
                if (i>cellY){} else {
                    cellY = i;
                }
            }
            if (touchY < oneNinthFieldSize + i*(oneNinthFieldSize)){
                if (i>cellX){} else {
                    cellX = i;
                }
            }
        }
        Log.d("COORDINATE_X_Y",Float.toString(cellX)+" "+Float.toString(cellY));
        //Зная номер ячейки необходимо высчитать центр ввода
        float x = (oneNinthFieldSize/2 + cellY*oneNinthFieldSize);
        float y = (13*oneNinthFieldSize/20 + cellX*oneNinthFieldSize);
        //Прописываем значение в координаты
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(45);
        mPaint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL));
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint rectPaint = new Paint();
        rectPaint.setStrokeWidth(5);
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setColor(getResources().getColor(R.color.starting_cell));

        mCanvas.drawRect(cellY*oneNinthFieldSize,cellX*oneNinthFieldSize,(cellY+1)*oneNinthFieldSize,(cellX+1)*oneNinthFieldSize,rectPaint);
        mCanvas.drawText(number,x,y,mPaint);
        invalidate();
    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(mBitmap,0,0, mPaint);
        drawField();
        Log.d("TAG","Ok!");
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){

        touchX = event.getX();
        touchY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            drawNumberInCell("5");
            gameActivity.showEnterLayout();

        }
        return true;
    }
}