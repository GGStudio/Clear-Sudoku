package com.ggstudio.clearsudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 23.09.2014.
 */
public class GameActivity extends Activity{

    private TextView textViewDifficulty, textViewTime, enterTextView;
    private String clue;
    private RelativeLayout gridEnterLayout;
    private RelativeLayout enterLayout;
    private Button buttonInv,buttonOk,buttonC;
    private Animation animationFadeIn, animationFadeOut, animationShrink;
    public static TextView[][] textViews = new TextView[9][9];
    public static Button[] numberButtons = new Button[9];
    public static int ULTIMATE_BUFFER_INT;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        textViewDifficulty = (TextView) findViewById(R.id.textViewDifficulty);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        gridEnterLayout = (RelativeLayout) findViewById(R.id.gridEnterLayout);
        enterLayout = (RelativeLayout) findViewById(R.id.enterLayout);
        enterLayout.setVisibility(View.GONE);
        enterTextView = (TextView) findViewById(R.id.enterTextView);
        buttonInv = (Button) findViewById(R.id.buttonInv);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonC = (Button) findViewById(R.id.buttonC);

        String s;
        int id;

        /** Creating game field. */
        createField(this, 9, 9);

        /** Initialize number buttons. */
        for (int i=0;i<9;i++){
            s = "button" + (i+1);
            id = getResources().getIdentifier(s,"id", "com.ggstudio.clearsudoku");
            numberButtons[i] = (Button) findViewById(id);
            numberButtons[i].setTag(i+1);
            numberButtons[i].setOnClickListener(new NumberButtonClickListener());
        }

        /** OK button listener. */
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterLayout.startAnimation(animationFadeOut);
                enterLayout.setVisibility(View.GONE);
            }
        });

        /** Set difficulty level. */
        clue = getIntent().getExtras().getString("difficulty");
        textViewDifficulty.setText(clue);

        animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        animationFadeIn.setAnimationListener(new AnimationFadeIn());
        animationFadeOut.setAnimationListener(new AnimationFadeOut());
    }

    /** Method of creating GameField.*/
    public void createField(final Activity activity, final int columns, final int lines) {

        final GridView grid = (GridView) activity.findViewById(R.id.gridGameLayout);
        final List<Integer> nums = new ArrayList<Integer>(columns * lines);

        grid.setNumColumns(columns);
        for(int i = 0; i < columns * lines; i++) {
            nums.add(i);
        }

        ViewGroup.LayoutParams params = grid.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;

        /** Don`t know whats this. */
        grid.post(new Runnable() {
            @Override
            public void run() {
                int width = grid.getMeasuredWidth();
                int height = grid.getMeasuredHeight();
                int oneWidth = width / columns;
                int oneHeight = height / lines;
                int oneSize = oneWidth > oneHeight ? oneHeight : oneWidth;

                ViewGroup.LayoutParams par = grid.getLayoutParams();
                par.width = (int) Math.floor((oneSize * columns) * 1.003 );
                par.height = (int) Math.floor((oneSize * lines) * 1.007 );

                final GameViewAdapter gameViewAdapter = new GameViewAdapter(activity,nums, oneSize);

                grid.setAdapter(gameViewAdapter);
                grid.setOnItemClickListener(new GridViewItemClickListener());
                grid.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_MOVE){
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

    }

    /** NumPad click listener. */
    public class NumberButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick (View view){

            if (view != null){
                TextView textTemp = (TextView) findViewById(ULTIMATE_BUFFER_INT);
                textTemp.setText(textTemp.getText() + view.getTag().toString());
                enterTextView.setText(enterTextView.getText() + view.getTag().toString());

            }
        }
    }

    /** GridView on item click listener.*/
    public class GridViewItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick (AdapterView<?> adapterView, View view, int position, long id){
            TextView tView = (TextView) view;

            ULTIMATE_BUFFER_INT = view.getId();
            enterTextView.setText(tView.getText());
            enterLayout.startAnimation(animationFadeIn);
            enterLayout.setVisibility(View.VISIBLE);
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
