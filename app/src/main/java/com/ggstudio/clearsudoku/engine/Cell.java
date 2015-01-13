package com.ggstudio.clearsudoku.engine;

import com.ggstudio.clearsudoku.engine.Enums.*;
public class Cell {
    public Cell(int x,int y) {
        this(x,y,-1);
    }

    public Cell(int x, int y, int val){
        this.setX(x);
        this.setY(y);

        digits = new boolean[9];

        for(int i=0;i<9;i++)
            digits[i]=false;

        if (val>0 && val<10)
        {
            _state = CellState.DEFINED;
        }
        else
        {
            _state = CellState.BLANK;
        }
        _value = val;
        _count = 0;
    }

    public String digitsToString(){
        String s ="";
        for (int i=0;i<digits.length;i++){
            if (digits[i]) s+=i;
        }
        return  s;
    }

    public void setState(CellState _state) {
        this._state = _state;
    }

    public CellState getState() {
        return _state;
    }

    private void setX(int x) {
        this._x = x;
    }

    private void setY(int y) {
        this._y = y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

/*	public void exclude(int excludeValue){
		if (_state==CellState.BLANK){

			if(excludeValue>0 && excludeValue<10){

				if (digits[excludeValue-1]){

					digits[excludeValue-1]=false;
					_count--;
					if (_count==1) {
						_state=CellState.DEFINED;
					}
				}
			}
		}
	}
	*/

    public void trigger(int digit){
        if(digit<0 || digit>=9) return;
        if (_state==CellState.START)return;

        digits[digit]=!digits[digit];

        if (digits[digit])
            _count++;
        else
            _count--;

        if (_count==1){
            _value=findSingleValue();
            _state=CellState.DEFINED;
        } else {
            _value=-1;
            _state=CellState.BLANK;
        }
    }

    private int findSingleValue(){
        int i;
        int s=0;
        int count=0;
        for(i=0;i<9;i++){
            if (digits[i]){
                count++;
                s=i;
            }
        }
        if(count==1) return s;
        return -1;
    }

    public boolean hasDigit(int i){
        if(i>0 && i<10){
            return digits[i];
        } else
            return false;
    }

    public boolean[] getDigits() {
        return digits;
    }

    public void setRow(Group row) {
        this.row = row;
    }

    public void setBlock(Group block) {
        this.block = block;
    }

    public void setColumn(Group column) {
        this.column = column;
    }

    public void setValue(int value) {
        this._value = value;
        _state=CellState.START;
    }
    public boolean isDefined(){
        return (_state==CellState.DEFINED);
    }

    public int getValue() {
        return _value;
    }

    private CellState _state;

    public Group row,block,column;

    private boolean[] digits;
    private int _value;
    private int _count;
    private int _x,_y;
}
