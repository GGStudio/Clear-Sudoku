package com.ggstudio.clearsudoku.engine;

/**
 * Created by Андрей on 07.01.2015.
 */
public class Enums {
    public enum	MoveType{
        INCLUDE,EXCLUDE
    }
    public enum GroupType{
        BLOCK, ROW, COLUMN
    }
    public enum Complexity {
        EASY, NORMAL, HARD
    }
    public enum CellState {
        BLANK, DEFINED, START
    }
}
