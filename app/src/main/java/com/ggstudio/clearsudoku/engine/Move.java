package com.ggstudio.clearsudoku.engine;

/**
 * Created by Андрей on 07.01.2015.
 */
import com.ggstudio.clearsudoku.engine.Enums.*;
public class Move {
    public Move(int x,int y, int value,MoveType type) {
        _x=x;
        _y=y;
        _value=value;
        _type=type;
    }

    private int _x,_y;
    private int _value;
    private MoveType _type;
}
