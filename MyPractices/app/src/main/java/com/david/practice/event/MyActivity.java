package com.david.practice.event;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.david.practice.R;
import com.david.practice.adapter.MyAdapter;

/**
 * Created by Administrator on 2016/11/3.
 */
public class MyActivity extends Activity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        Button btnChange = (Button)findViewById(R.id.btn_change);
        listView = (ListView)findViewById(R.id.my_list_view);
        listView.setAdapter(new MyAdapter(MyActivity.this));
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList();
            }
        });
    }
    public void showList(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(listView,"scaleY",0,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    public void hideList(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(listView,"scaleY",1f,0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideList();
        return super.onTouchEvent(event);
    }
}
