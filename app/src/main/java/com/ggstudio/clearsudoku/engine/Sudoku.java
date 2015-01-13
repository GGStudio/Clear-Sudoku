package com.ggstudio.clearsudoku.engine;

/**
 * Created by Андрей on 07.01.2015.
 */
import java.util.ArrayList;
import com.ggstudio.clearsudoku.engine.Enums.*;
public class Sudoku {
    public SudokuField field;
    ArrayList<Move> moves;
    Complexity complexity;
    int turn =	0;

    public Sudoku() {
        moves = new ArrayList<Move>();
        field= new SudokuField();
        complexity=Complexity.NORMAL;
    }
}
