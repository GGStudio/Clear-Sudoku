package com.ggstudio.clearsudoku;

/**
 * Created by andrey on 06.01.15.
 */

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.ggstudio.clearsudoku.engine.Enums;
import com.ggstudio.clearsudoku.engine.Sudoku;

import java.util.List;

/** Adapter class for GridView element (GameField).*/
public class GameViewAdapter extends BaseAdapter {

    Activity ac;
    List<Integer> nums;
    int oneWidth;
    Sudoku sudoku;

    GameViewAdapter(Activity ac, List<Integer> nums, int oneWidth) {
        this.ac = ac;
        this.nums = nums;
        this.oneWidth = oneWidth;
    }

    @Override
    public int getCount() {
        return nums.size();
    }

    @Override
    public Object getItem(int location) {
        return nums.get(location);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int location, View v, ViewGroup parent) {

        //int number = nums.get(location);

        TextView tv = new TextView(ac);
        GridView.LayoutParams par = new GridView.LayoutParams(oneWidth , oneWidth );

        tv.setLayoutParams(par);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(18);
        switch (nums.get(location)){
            case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:
                tv.setId(nums.get(location)+11);
                tv.setTag(Integer.toString(nums.get(location)+11));
                break;
            case 9:case 10:case 11:case 12:case 13:case 14:case 15:case 16:case 17:
                tv.setId(nums.get(location)+12);
                tv.setTag(Integer.toString(nums.get(location)+12));
                break;
            case 18:case 19:case 20:case 21:case 22:case 23:case 24:case 25:case 26:
                tv.setId(nums.get(location)+13);
                tv.setTag(Integer.toString(nums.get(location)+13));
                break;
            case 27:case 28:case 29:case 30:case 31:case 32:case 33:case 34:case 35:
                tv.setId(nums.get(location)+14);
                tv.setTag(Integer.toString(nums.get(location)+14));
                break;
            case 36:case 37:case 38:case 39:case 40:case 41:case 42:case 43:case 44:
                tv.setId(nums.get(location)+15);
                tv.setTag(Integer.toString(nums.get(location)+15));
                break;
            case 45:case 46:case 47:case 48:case 49:case 50:case 51:case 52:case 53:
                tv.setId(nums.get(location)+16);
                tv.setTag(Integer.toString(nums.get(location)+16));
                break;
            case 54:case 55:case 56:case 57:case 58:case 59:case 60:case 61:case 62:
                tv.setId(nums.get(location)+17);
                tv.setTag(Integer.toString(nums.get(location)+17));
                break;
            case 63:case 64:case 65:case 66:case 67:case 68:case 69:case 70:case 71:
                tv.setId(nums.get(location)+18);
                tv.setTag(Integer.toString(nums.get(location)+18));
                break;
            case 72:case 73:case 74:case 75:case 76:case 77:case 78:case 79:case 80:
                tv.setId(nums.get(location)+19);
                tv.setTag(Integer.toString(nums.get(location)+19));
                break;
        }

        tv.setText(Integer.toString(tv.getId()));


        return tv;
    }

}