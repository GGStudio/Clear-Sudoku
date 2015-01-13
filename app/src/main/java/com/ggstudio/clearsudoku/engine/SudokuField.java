package com.ggstudio.clearsudoku.engine;


import com.ggstudio.clearsudoku.engine.Enums.*;
public class SudokuField {

    public SudokuField() {
        cells	= new Cell[9][9];
        rows 	= new Group[9];
        cols	= new Group[9];
        blocks 	= new Group[9];

        createCells();
        createRows();
        createColumns();
        createGroups();
        fillStartingValues();
    }


    private void createCells(){
        for (int i = 0; i < 9; i++) {
            for(int j=0;j<9;j++){
                cells[i][j]=new Cell(i+1, j+1);
            }
        }
    }

    private void createRows(){
        for(int i=0;i<9;i++)
            rows[i]=new Group(GroupType.ROW,cells[i][0],cells[i][1],cells[i][2],cells[i][3],cells[i][4],cells[i][5],cells[i][6],cells[i][7],cells[i][8]);
    }

    private void createColumns(){
        for(int i=0;i<9;i++)
            cols[i]= new Group(GroupType.COLUMN,cells[0][i],cells[1][i], cells[2][i], cells[3][i], cells[4][i], cells[5][i], cells[6][i], cells[7][i], cells[8][i]);
    }

    private void createGroups(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                blocks[i*3+j]=new Group(GroupType.BLOCK,
                        cells[3*i][3*j],cells[3*i][3*j+1],cells[3*i][3*j+2],
                        cells[3*i+1][3*j],cells[3*i+1][3*j+1],cells[3*i+1][3*j+2],
                        cells[3*i+2][3*j],cells[3*i+2][3*j+1],cells[3*i+2][3*j+2]);
            }
        }
    }
    public void fillStartingValues(){
        cells[0][2].setValue(5);
        cells[0][5].setValue(4);
        cells[0][8].setValue(9);
        cells[6][2].setValue(1);
        cells[6][5].setValue(3);
        cells[3][4].setValue(2);
    }

    public boolean checkDefined(){
        boolean flag = true;

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (!cells[i][j].isDefined())
                    flag =false;
            }
        }

        return flag;
    }

    public boolean checkFinished(){
        if (!checkDefined()) return false;

        int sum=0;
        for(int i=0;i<9;i++)
            sum+=rows[i].getSum();
        if (sum!=45)
            return false;

        sum=0;
        for(int i=0;i<9;i++)
            sum+=cols[i].getSum();
        if (sum!=45)
            return false;

        sum=0;
        for(int i=0;i<9;i++)
            sum+=blocks[i].getSum();
        if (sum!=45)
            return false;

        for(int i=0;i<9;i++)
            if (!rows[i].isAllUnique())
                return false;

        for(int i=0;i<9;i++)
            if (!cols[i].isAllUnique())
                return false;

        for(int i=0;i<9;i++)
            if (!blocks[i].isAllUnique())
                return false;
        return true;
    }
    public Cell[][] cells;
    public Group[] rows;
    public Group[] cols;
    public Group[] blocks;
}
