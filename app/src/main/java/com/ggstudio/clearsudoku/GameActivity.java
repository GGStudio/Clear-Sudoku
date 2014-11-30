package com.ggstudio.clearsudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by Andrey on 23.09.2014.
 */
public class GameActivity extends Activity {

    private TextView textViewDifficulty, textViewTime;
    private String clue;
    private GridView mGrView;
    private TextView label_11, label_21;
    public static TextView[][] textViews = new TextView[9][9];

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        textViewDifficulty = (TextView) findViewById(R.id.textViewDifficulty);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        String s;
        int id;

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){

                s = "label_" + (i+1);
                s += (j+1);
                id = getResources().getIdentifier(s, "id", "com.ggstudio.clearsudoku");
                textViews[i][j] = (TextView) findViewById(id);
            }
        }

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                textViews[i][j].setText("V"+i+j);
            }
        }

        clue = getIntent().getExtras().getString("difficulty");
        textViewDifficulty.setText(clue);
    }


}
