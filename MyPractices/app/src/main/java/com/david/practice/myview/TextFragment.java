package com.david.practice.myview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/12/14.
 */
public class TextFragment extends Fragment {
    TextView textView;
    String Host = "异域至尊";
    String[] names = {"张三","李四","王五"};
    String comment = "You are the apple of my eyes";

    int color = Color.RED;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_fragment,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
