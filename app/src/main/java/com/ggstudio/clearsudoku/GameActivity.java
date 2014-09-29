package com.ggstudio.clearsudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Andrey on 23.09.2014.
 */
public class GameActivity extends Activity {

    private TextView textViewDifficulty, textViewTime;
    private String clue;
    private GameView mView;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        mView = (GameView) findViewById(R.id.gameView);
        textViewDifficulty = (TextView) findViewById(R.id.textViewDifficulty);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        clue = getIntent().getExtras().getString("difficulty");
        textViewDifficulty.setText(clue);
    }
}
