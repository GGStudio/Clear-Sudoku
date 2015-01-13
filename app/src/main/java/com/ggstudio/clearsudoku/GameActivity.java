package com.ggstudio.clearsudoku;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ggstudio.clearsudoku.engine.Enums;
import com.ggstudio.clearsudoku.engine.Sudoku;

/**
 * Created by Andrey on 23.09.2014.
 */
public class GameActivity extends Activity{


    private Sudoku sudoku;
    private GameView gameView;
    private TextView enterTextView, currentTextView;
    private String clue;
    private static RelativeLayout enterLayout,rootLayout;
    private Button buttonInv,buttonOk,buttonC;
    private int coordX,coordY;
    private Animation animationFadeIn, animationFadeOut;
    public static Button[] numberButtons = new Button[9];
    public static TextView[][] textViews = new TextView[9][9];
    public static int ULTIMATE_BUFFER_INT;

    public GameActivity(){

    }

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        TextView textViewDifficulty = (TextView) findViewById(R.id.textViewDifficulty);
        TextView textViewTime = (TextView) findViewById(R.id.textViewTime);
        sudoku = new Sudoku();
        gameView = (GameView)findViewById(R.id.gameView);
        rootLayout = (RelativeLayout) findViewById(R.id.rootRelativeLayout);
        enterLayout = (RelativeLayout) findViewById(R.id.enterLayout);
        enterLayout.setVisibility(View.GONE);
        enterTextView = (TextView) findViewById(R.id.enterTextView);
        buttonInv = (Button) findViewById(R.id.buttonInv);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonC = (Button) findViewById(R.id.buttonC);
        String s;
        int id;

        gameView.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = gameView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                if (gameView.getMeasuredWidth() != 0){
                    Log.d("SIZE",Integer.toString(gameView.getMeasuredHeight()));
                    params.height = gameView.getMeasuredWidth();
                }

            }
        });
        /** Initialize number buttons. */
        for (int i=0;i<9;i++){
            s = "button" + (i+1);
            id = getResources().getIdentifier(s,"id", "com.ggstudio.clearsudoku");
            numberButtons[i] = (Button) findViewById(id);
            numberButtons[i].setTag(i+1);
            numberButtons[i].setOnClickListener(new NumberButtonClickListener(i+1));
        }
        /** OK button listener. */
        buttonOk.setOnClickListener(new OkButtonClickListener());
        /** Set difficulty level. */
        clue = getIntent().getExtras().getString("difficulty");
        textViewDifficulty.setText(clue);

        animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        animationFadeIn.setAnimationListener(new AnimationFadeIn());
        animationFadeOut.setAnimationListener(new AnimationFadeOut());


    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        //получаем координаты ячейки, по которой тапнули
//        coordX = (int)(event.getX());
//        coordY = (int)(event.getY());
//        float oneNinth = gameView.getWidth()/9;
//        int cellX = 8;
//        int cellY = 8;
//        //ИНВЕРСИЯ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Потому что в матрице
//        // сначало идёт вверх номер строки!
//        for (int i=0;i<9;i++){
//            if (coordX < oneNinth + i*(oneNinth)){
//                if (i>cellY){
//
//                } else {
//                    cellY = i;
//                }
//            }
//            if (coordY < oneNinth + i*(oneNinth)){
//                if (i>cellX){
//
//                } else {
//                    cellX = i;
//                }
//            }
//        }
//        //gameView.drawNumber();
//        gameView.invalidate();
//        Toast.makeText(this,Integer.toString(cellX)+Integer.toString(cellY),Toast.LENGTH_SHORT).show();
//        return true;
//    }

    public static void showEnterLayout(){
        enterLayout.setVisibility(View.VISIBLE);
    }

    /** Button "OK" performing change of layouts and checking uniquity of cells.*/
    public class OkButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick (View view){

//            TextView textTemp = (TextView) findViewById(ULTIMATE_BUFFER_INT);
//
//            int a = Integer.parseInt((String)currentTextView.getTag());
//            int i = a/10;
//            int j = a%10;
//
//            currentTextView.setText(sudoku.field.cells[i][j].digitsToString());
//
//
//            if (!sudoku.field.cells[i][j].row.isAllUnique() | !sudoku.field.cells[i][j].column.isAllUnique() |
//                    !sudoku.field.cells[i][j].block.isAllUnique()){
//                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
//            }
            enterLayout.startAnimation(animationFadeOut);
            enterLayout.setVisibility(View.GONE);
        }
    }

    /** NumPad click listener. */
    public class NumberButtonClickListener implements View.OnClickListener{
        int number;
        public NumberButtonClickListener(int x){

            number = x;

        }
        @Override
        public void onClick (View view){

            if (view != null){
                TextView textTemp = (TextView) findViewById(ULTIMATE_BUFFER_INT);


                //textTemp.setText(textTemp.getText() + view.getTag().toString());
                int a = Integer.parseInt((String)currentTextView.getTag());
                int i = a/10;
                int j = a%10;

                sudoku.field.cells[i][j].trigger(number);



                enterTextView.setText(sudoku.field.cells[i][j].digitsToString());


            }
        }
    }

    /** Animation fade in. */
    public class AnimationFadeIn implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation){
            enterLayout.startAnimation(animationFadeIn);
        }

        @Override
        public void onAnimationRepeat(Animation animation){

        }

        @Override
        public void onAnimationEnd(Animation animation){

        }
    }

    /** Animation fade out. */
    public class AnimationFadeOut implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation){
            enterLayout.startAnimation(animationFadeOut);
        }

        @Override
        public void onAnimationRepeat(Animation animation){

        }

        @Override
        public void onAnimationEnd(Animation animation){

        }
    }

}
