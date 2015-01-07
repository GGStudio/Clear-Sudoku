package com.ggstudio.clearsudoku.engine;

import com.ggstudio.clearsudoku.engine.Enums.*;
public class Group {
    public Cell[] cells;
    public GroupType type;

    public Group(GroupType groupType,	Cell c0, Cell c1, Cell c2,
                 Cell c3, Cell c4, Cell c5,
                 Cell c6, Cell c7, Cell c8)
    {
        cells = new Cell[9];
        cells[0]=c0;
        cells[1]=c1;
        cells[2]=c2;
        cells[3]=c3;
        cells[4]=c4;
        cells[5]=c5;
        cells[6]=c6;
        cells[7]=c7;
        cells[8]=c8;

        type=groupType;
        setTypeToCells();
    }

    private void setTypeToCells(){
        if(type==GroupType.BLOCK){
            for(int i=0;i<9;i++)
                cells[i].setBlock(this);
        }
        else if(type==GroupType.ROW){
            for(int i=0;i<9;i++)
                cells[i].setRow(this);
        }
        else if(type==GroupType.COLUMN){
            for(int i=0;i<9;i++)
                cells[i].setColumn(this);
        }

    }

    public int getSum(){
        int sum=0;
        for(int i=0;i<cells.length;i++){
            sum+=cells[i].getValue();
        }
        return sum;
    }
    public boolean isAllUnique(){
        int[] availableDigitsInGroup = new int[9];
        for(int i=0;i<cells.length;i++){
            availableDigitsInGroup[cells[i].getValue()]+=1;
        }

        boolean flag=true;
        for(int i=0; i<availableDigitsInGroup.length; i++){
            if (availableDigitsInGroup[i]!=1)
                flag=false;
        }
        return flag;
    }
}
