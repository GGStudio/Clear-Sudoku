package com.ggstudio.clearsudoku;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NewGameFragment extends Fragment {

    private Button buttonEasy;
    private Button buttonModerate;
    private Button buttonHard;

    public NewGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View newGameFragment = inflater.inflate(R.layout.fragment_new_game, container, false);
        final Intent intent = new Intent(NewGameFragment.this.getActivity(), GameActivity.class);

        buttonEasy = (Button) newGameFragment.findViewById(R.id.buttonEasy);
        buttonModerate = (Button) newGameFragment.findViewById(R.id.buttonModerate);
        buttonHard = (Button) newGameFragment.findViewById(R.id.buttonHard);

        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("difficulty", "easy");
                startActivity(intent);
            }
        });

        buttonModerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("difficulty", "moderate");
                startActivity(intent);
            }
        });

        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("difficulty", "hard");
                startActivity(intent);
            }
        });

        //return inflater.inflate(R.layout.fragment_new_game, container, false);
        return newGameFragment;
    }


}
