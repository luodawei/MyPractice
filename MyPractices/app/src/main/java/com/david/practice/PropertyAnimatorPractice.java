package com.david.practice;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/2.
 */
public class PropertyAnimatorPractice extends Activity {
    TextView touchToStart;
    TextView textOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_animator_practice);
        touchToStart = (TextView)findViewById(R.id.touch_to_start);
        textOne = (TextView)findViewById(R.id.text_one);
        startAlphaAnimator();
        touchToStart.setOnClickListener(onClickListener);
    }
    public void startAlphaAnimator(){
        ObjectAnimator alphaAnimatorOne = ObjectAnimator.ofFloat(touchToStart,"alpha",1.0f,0f);
        ObjectAnimator alphaAnimatorTwo = ObjectAnimator.ofFloat(touchToStart,"alpha",0f,1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alphaAnimatorOne).before(alphaAnimatorTwo);
        animatorSet.setDuration(3000);
        animatorSet.start();
        animatorSet.addListener(animatorListener);
    }
    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            startAlphaAnimator();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
    public void startDrawableAnimation(){
        textOne.setBackgroundResource(R.drawable.bg_color_change);
        AnimationDrawable animationDrawable = (AnimationDrawable) textOne.getBackground();
        animationDrawable.start();
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.touch_to_start:
                    startDrawableAnimation();
                    break;
            }
        }
    };
}
