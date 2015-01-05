package com.ggstudio.clearsudoku;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,
    buttonInv,buttonOk,buttonC;
    public static TextView[][] textViews = new TextView[9][9];
    public static Button[] numberButtons = new Button[9];
    public static String ULTIMATE_BUFFER;
    public static int ULTIMATE_BUFFER_INT;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        textViewDifficulty = (TextView) findViewById(R.id.textViewDifficulty);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        gridEnterLayout = (RelativeLayout) findViewById(R.id.gridEnterLayout);
        //gridEnterLayout.setVisibility(GridLayout.GONE);
        enterLayout = (RelativeLayout) findViewById(R.id.enterLayout);
        enterLayout.setVisibility(View.GONE);
//        button1 = (Button) findViewById(R.id.button1);
//        button2 = (Button) findViewById(R.id.button2);
//        button3 = (Button) findViewById(R.id.button3);
//        button4 = (Button) findViewById(R.id.button4);
//        button5 = (Button) findViewById(R.id.button5);
//        button6 = (Button) findViewById(R.id.button6);
//        button7 = (Button) findViewById(R.id.button7);
//        button8 = (Button) findViewById(R.id.button8);
//        button9 = (Button) findViewById(R.id.button9);
        enterTextView = (TextView) findViewById(R.id.enterTextView);
        buttonInv = (Button) findViewById(R.id.buttonInv);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonC = (Button) findViewById(R.id.buttonC);

        String s;
        int id;

        createField(this, 9, 9);

        // Initialize all of 81 labels
//        for (int i = 0; i < 9; i++){
//            for (int j = 0; j < 9; j++){
//
//                s = "lb" + (i+1);
//                s += (j+1);
//                id = getResources().getIdentifier(s, "id", "com.ggstudio.clearsudoku");
//                textViews[i][j] = (TextView) findViewById(id);
//                textViews[i][j].setOnClickListener(new TextClickListener());
//                textViews[i][j].setTag(Integer.toString(10*(i+1)+(j+1)));
//            }
//        }

        s = "";

        // Initialize number buttons
        for (int i=0;i<9;i++){
            s = "button" + (i+1);
            id = getResources().getIdentifier(s,"id", "com.ggstudio.clearsudoku");
            numberButtons[i] = (Button) findViewById(id);
            numberButtons[i].setTag(i+1);
            numberButtons[i].setOnClickListener(new NumberButtonClickListener());
        }

//        for (int i = 0; i < 9; i++){
//            for (int j = 0; j < 9; j++){
//                textViews[i][j].setText("V"+i+j);
//            }
//        }

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterLayout.setVisibility(View.GONE);
            }
        });

        clue = getIntent().getExtras().getString("difficulty");
        textViewDifficulty.setText(clue);

    }

    public class TextClickListener implements TextView.OnClickListener{
        public void onClick (View view){

            TextView tView = (TextView) view;
            if (view != null){
                ULTIMATE_BUFFER = view.getTag().toString();
                ULTIMATE_BUFFER_INT = view.getId();
                enterTextView.setText(tView.getText());
                enterLayout.setVisibility(View.VISIBLE);

            }
        }
    }

    public class NumberButtonClickListener implements View.OnClickListener{
        public void onClick (View view){

            //int county = 0;

            if (view != null){
                TextView textTemp = (TextView) findViewById(ULTIMATE_BUFFER_INT);

                //enterLayout.setVisibility(View.VISIBLE);

                textTemp.setText(textTemp.getText() + view.getTag().toString());
                enterTextView.setText(enterTextView.getText() + view.getTag().toString());

//                if (county++ == 0){
//                    textTemp.setText(view.getTag().toString());
//                    enterTextView.setText(enterTextView.getText() + view.getTag().toString());
//
//                } else {
//                    textTemp.setText(view.getTag().toString() + "\n");
//                    enterTextView.setText(enterTextView.getText() + " " + view.getTag().toString());
//                }

            }
        }
    }

    public void createField(final Activity activity, final int columns, final int lines) {
        final GridView grid = (GridView) activity.findViewById(R.id.gridGameLayout);
        grid.setNumColumns(columns);
        //grid.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_background));
        //grid.setColumnCount(columns);

        final List<Integer> nums = new ArrayList<Integer>(columns * lines);
        for(int i = 0; i < columns * lines; i++) {
            nums.add(i);
        }

        ViewGroup.LayoutParams params = grid.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;

        grid.post(new Runnable() {
            @Override
            public void run() {
                int width = grid.getMeasuredWidth();
                int height = grid.getMeasuredHeight();

                int oneWidth = width / columns;
                int oneHeight = height / lines;

                int oneSize = oneWidth > oneHeight ? oneHeight : oneWidth;

                ViewGroup.LayoutParams par = grid.getLayoutParams();
                par.width = (int) Math.floor((oneSize * columns) * 1.008 );
                par.height = (int) Math.floor((oneSize * lines) * 1.01 );

                grid.setAdapter(new AdGrid(activity, nums, oneSize));
            }
        });

    }




    public class AdGrid extends BaseAdapter {

        Activity ac;
        List<Integer> nums;
        int oneWidth;
        AdGrid(Activity ac, List<Integer> nums, int oneWidth) {
            this.ac = ac;
            this.nums = nums;
            this.oneWidth = oneWidth;
        }


        @Override
        public int getCount() {
            return nums.size();
        }

        @Override
        public Object getItem(int location) {
            return nums.get(location);
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int location, View v, ViewGroup parent) {

            int number = nums.get(location);

            TextView tv = new TextView(ac);

            int i=0;
//            if (number < 45){
//                GridView.LayoutParams par = new GridView.LayoutParams(oneWidth, oneWidth);
//            }
            GridView.LayoutParams par = new GridView.LayoutParams(oneWidth , oneWidth );

            tv.setLayoutParams(par);
            //tv.setText(Integer.toString(number));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setId(nums.get(location));
            tv.setTag(Integer.toString(nums.get(location)));

            tv.setOnClickListener(new TextClickListener());

            return tv;
        }

    }


}
