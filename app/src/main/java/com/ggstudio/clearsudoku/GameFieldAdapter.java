package com.ggstudio.clearsudoku;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Andrey on 25.11.2014.
 */
public class GameFieldAdapter extends BaseAdapter{

    private Context mContext;

    public TextView[] mTextViews;

    public GameFieldAdapter (Context context){
        mContext = context;
//        initArray();
        mTextViews = new TextView[82];
        for (int i = 1; i < 82; i++){
            mTextViews[i] = new TextView(mContext);
        }
    }

//    public void initArray (){
//        mTextViews = new TextView[9][9];
//        for (int i = 0; i < 9; i++){
//            for (int j = 0; j < 9; j++){
//                mTextViews[i][j] = new TextView(mContext);
//            }
//        }
//    }

    @Override
    public int getCount (){
        return mTextViews.length;
    }

    @Override
    public Object getItem (int position){
        return mTextViews[position];
    }

    @Override
    public long getItemId (int position){
        return 0;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
//        mTextViews = new TextView[81];
//        for (int i = 0; i < 81; i++){
//            mTextViews[i] = new TextView(mContext);
//        }
        TextView textView = new TextView(mContext);
        textView.setText(Integer.toString(position));
        textView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT,GridView.AUTO_FIT));

        return textView;

    }
}
