package com.example.zn.practicewriting.Animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.widget.ImageButton;
import android.widget.TextView;

public class AnimatorEx extends AnimatorListenerAdapter{

    boolean moving_Tmp;
    public AnimatorEx(){
        super();
    }

    public void onAnimationEnd_move(Animator animation,TextView tv) {
        super.onAnimationEnd(animation);
        ObjectAnimator animator=ObjectAnimator.ofFloat(tv,"alpha",0.0F,1.0F).setDuration(1000);
        animator.start();
        moving_Tmp = false;
    }

    public void onAnimationEnd_back(Animator animation, Float float_x, Float float_y, ImageButton bt_tmpA,ImageButton bt_tmpB) {
        super.onAnimationEnd(animation);
        ObjectAnimator animator;
        //bt_Video_Area回原位
        animator = ObjectAnimator.ofFloat(bt_tmpA,"translationX",float_x,0.0F).setDuration(1000);
        animator.start();
        animator = ObjectAnimator.ofFloat(bt_tmpA,"translationY",float_y,0.0F).setDuration(1000);
        animator.start();
        //bt_Write_Area出現
        animator = ObjectAnimator.ofFloat(bt_tmpB,"alpha",0.0F,1.0F).setDuration(1000);
        animator.start();
    }

    public boolean getMovingStatus(){
        return moving_Tmp;
    }
}
