package com.ggstudio.clearsudoku;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
    private Button buttonInv,buttonOk,buttonC;
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
        enterLayout = (RelativeLayout) findViewById(R.id.enterLayout);
        enterLayout.setVisibility(View.GONE);
        enterTextView = (TextView) findViewById(R.id.enterTextView);
        buttonInv = (Button) findViewById(R.id.buttonInv);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonC = (Button) findViewById(R.id.buttonC);

        String s;
        int id;

        // Creating game field
        createField(this, 9, 9);

        // Initialize number buttons
        for (int i=0;i<9;i++){
            s = "button" + (i+1);
            id = getResources().getIdentifier(s,"id", "com.ggstudio.clearsudoku");
            numberButtons[i] = (Button) findViewById(id);
            numberButtons[i].setTag(i+1);
            numberButtons[i].setOnClickListener(new NumberButtonClickListener());
        }

        // OK button listener
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterLayout.setVisibility(View.GONE);
            }
        });

        //Set difficulty level
        clue = getIntent().getExtras().getString("difficulty");
        textViewDifficulty.setText(clue);

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

                final AdGrid adGrid = new AdGrid(activity,nums, oneSize);
                grid.setAdapter(adGrid);
                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        TextView tView = (TextView) view;

                        ULTIMATE_BUFFER = view.getTag().toString();
                        ULTIMATE_BUFFER_INT = view.getId();

                        if (view != null){
                            enterTextView.setText(tView.getText());
                            enterLayout.setVisibility(View.VISIBLE);

                        }
                    }
                });
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

    // Old one listener. Consumed by adapter OnItemClickListener.
//    public class TextClickListener implements TextView.OnClickListener{
//        public void onClick (View view){
//
//            TextView tView = (TextView) view;
//            if (view != null){
//                ULTIMATE_BUFFER = view.getTag().toString();
//                ULTIMATE_BUFFER_INT = view.getId();
//                enterTextView.setText(tView.getText());
//                enterLayout.setVisibility(View.VISIBLE);
//
//            }
//        }
//    }

    /** NumPad click listener */
    public class NumberButtonClickListener implements View.OnClickListener{
        public void onClick (View view){

            if (view != null){
                TextView textTemp = (TextView) findViewById(ULTIMATE_BUFFER_INT);
                textTemp.setText(textTemp.getText() + view.getTag().toString());
                enterTextView.setText(enterTextView.getText() + view.getTag().toString());

            }
        }
    }

    /** Adapter class for GridView element (GameField).*/
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
            GridView.LayoutParams par = new GridView.LayoutParams(oneWidth , oneWidth );

            tv.setLayoutParams(par);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setId(nums.get(location));
            tv.setTag(Integer.toString(nums.get(location)));

            return tv;
        }

    }


}
