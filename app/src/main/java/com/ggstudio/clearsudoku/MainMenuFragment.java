package com.ggstudio.clearsudoku;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenuFragment extends Fragment {

    private Button buttonNewGame;
    private NewGameFragment newGameFragment;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_main_menu, container, false);
        final View mainMenuFragment = inflater.inflate(R.layout.fragment_main_menu, container, false);

        buttonNewGame = (Button) mainMenuFragment.findViewById(R.id.buttonNewGame);
        newGameFragment = new NewGameFragment();

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.myContainer, newGameFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });




        return mainMenuFragment;
    }


}
