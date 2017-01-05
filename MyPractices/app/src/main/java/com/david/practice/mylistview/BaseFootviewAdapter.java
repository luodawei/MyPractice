package com.david.practice.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/15.
 */
public abstract class BaseFootviewAdapter<E> extends BaseAdapter{
    public static final int DEFAULT_SHOW_COUNT = 2;
    protected Context mContext;
    protected ListView mListView;
    protected LayoutInflater inflater;
    protected LinearLayout headView;
    protected Button btnLoadMore;
    protected ArrayList<E> mShowObjects = new ArrayList<E>();
    protected ArrayList<E> mAllObjects = null;
    protected boolean shrink = true;

    @SuppressWarnings("unused")
    private BaseFootviewAdapter(){
    }
    public BaseFootviewAdapter(Context mContext,ListView mListView){
        this.mContext = mContext;
        this.mListView = mListView;
        inflater = LayoutInflater.from(mContext);
    }
}
