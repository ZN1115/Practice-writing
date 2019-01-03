package com.example.zn.practicewriting.Animator;

import android.animation.ObjectAnimator;
import android.widget.ImageButton;
import android.widget.TextView;

public class AnimationSet {

    private ImageButton bt_Video;
    private ImageButton bt_Write;
    private TextView tv_Video;
    private TextView tv_Write;
    private ObjectAnimator animator;
    private ObjectAnimator animator1;
    public AnimationSet(ImageButton iv,ImageButton iw,TextView tv,TextView tw){
        bt_Video = iv;
        bt_Write = iw;
        tv_Video = tv;
        tv_Write = tw;
    }

    public void AnimationInit(){
        ObjectAnimator tv_animator = ObjectAnimator.ofFloat(tv_Video,"alpha",0.0F,0.0F).setDuration(2000);
        ObjectAnimator bt_animator = ObjectAnimator.ofFloat(tv_Write,"alpha",0.0F,0.0F).setDuration(2000);
        tv_animator.start();
        bt_animator.start();
        animator = new ObjectAnimator();
        animator1 = new ObjectAnimator();
    }

    public void animation_Move(Float float_x,Float float_y,String instruction){
        AnimatorEx ax = new AnimatorEx();

        switch (instruction){
            case "Write":
                //bt_Write_Area按鈕移動
                animator = ObjectAnimator.ofFloat(bt_Write,"translationX",0.0F,float_x).setDuration(1000);
                animator.start();
                animator = ObjectAnimator.ofFloat(bt_Write,"translationY",0.0F,float_y).setDuration(1000);
                animator.start();
                //bt_Write_Area按鈕縮放
                animator = ObjectAnimator.ofFloat(bt_Write,"scaleX",1.0F,1.3F).setDuration(1000);
                animator.start();
                animator1 = ObjectAnimator.ofFloat(bt_Write,"scaleY",1.0F,1.3F).setDuration(1000);
                animator1.start();
                //bt_Video_Area消失
                animator = ObjectAnimator.ofFloat(bt_Video,"alpha",1.0F,0.0F).setDuration(1000);
                animator.start();
                ax.onAnimationEnd_move(animator1,tv_Write);
                break;
            case "Video":
                //bt_Video_Area按鈕移動
                animator = ObjectAnimator.ofFloat(bt_Video,"translationX",0.0F,float_x).setDuration(1000);
                animator.start();
                animator = ObjectAnimator.ofFloat(bt_Video,"translationY",0.0F,float_y).setDuration(1000);
                animator.start();
                //bt_Video_Area按鈕縮放
                animator = ObjectAnimator.ofFloat(bt_Video,"scaleX",1.0F,1.3F).setDuration(1000);
                animator.start();
                animator1 = ObjectAnimator.ofFloat(bt_Video,"scaleY",1.0F,1.3F).setDuration(1000);
                animator1.start();
                //bt_Write_Area消失
                animator = ObjectAnimator.ofFloat(bt_Write,"alpha",1.0F,0.0F).setDuration(1000);
                animator.start();
                ax.onAnimationEnd_move(animator1,tv_Video);
                break;
            default:
                break;
        }
    }

    public void animation_Back(Float float_x,Float float_y,String instruction){
        AnimatorEx ax = new AnimatorEx();

        switch (instruction) {
            case "Write":
                //bt_Write_Area縮放
                animator = ObjectAnimator.ofFloat(bt_Write, "scaleX", 1.3F, 1.0F).setDuration(1000);
                animator.start();
                animator = ObjectAnimator.ofFloat(bt_Write, "scaleY", 1.3F, 1.0F).setDuration(1000);
                animator.start();
                //tv_Write_Area消失
                animator1 = ObjectAnimator.ofFloat(tv_Write, "alpha", 1.0F, 0.0F).setDuration(1000);
                animator1.start();
                ax.onAnimationEnd_back(animator1,float_x,float_y,bt_Write,bt_Video);
                break;
            case "Video":
                animator = ObjectAnimator.ofFloat(bt_Video,"scaleX",1.3F,1.0F).setDuration(1000);
                animator.start();
                animator = ObjectAnimator.ofFloat(bt_Video,"scaleY",1.3F,1.0F).setDuration(1000);
                animator.start();
                //tv_Video_Area消失
                animator1 = ObjectAnimator.ofFloat(tv_Video,"alpha",1.0F,0.0F).setDuration(1000);
                animator1.start();
                ax.onAnimationEnd_back(animator1,float_x,float_y,bt_Video,bt_Write);
                break;
            default:
                break;
        }
    }

    public boolean getAnimatorStatus() {
        return animator1.isRunning();
    }
}
