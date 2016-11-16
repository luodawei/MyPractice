package com.david.practice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.david.practice.adapter.GridViewAdapter;

/**
 * Created by David on 2016/10/16.
 */

public class GridViewPractice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_practice);
        GridView gridView = (GridView)findViewById(R.id.gridview_practie);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(GridViewPractice.this);
        gridView.setAdapter(gridViewAdapter);
    }
}
