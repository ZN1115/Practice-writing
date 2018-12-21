package com.example.zn.practicewriting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton bt_Video_Area;
    private ImageButton bt_Write_Area;
    private TextView tv_Video_Area;
    private TextView tv_Write_Area;
    final int[] Write_Area_Loc = new int[4];
    final int[] Video_Area_Loc = new int[4];
    int ToNextPageCount = 0;//數字為2進寫字，為6進影片
    boolean CanToNext = false;//判斷是否可以進區域
    boolean StatusVideo = false;//判斷現在是什麼狀態
    boolean StatusWrite = false;
    boolean moving = false;//是否移動中
    AnimationSet as;
    int mWidth;
    int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {
        StatusVideo = false;
        StatusWrite = false;
        super.onStart();
    }

    public void init(){
        bt_Video_Area = (ImageButton) findViewById(R.id.bt_main_video);
        bt_Write_Area = (ImageButton) findViewById(R.id.bt_main_write);
        tv_Video_Area = (TextView)findViewById(R.id.tv_main_video);
        tv_Write_Area = (TextView)findViewById(R.id.tv_main_write);

        as = new AnimationSet(bt_Video_Area,bt_Write_Area,tv_Video_Area,tv_Write_Area);
        as.AnimationInit();

        mWidth = getResources().getDisplayMetrics().widthPixels/4 - getResources().getDisplayMetrics().widthPixels/100;
        mHeight = getResources().getDisplayMetrics().heightPixels/4;
    }
    //是否點擊
    public boolean onTouchEvent(MotionEvent event) {
        if(moving == true){
        }
        else{
            if(ToNextPageCount == 1)
                ToNextPageCount = 0;
            else if(ToNextPageCount == 3)
                ToNextPageCount = 0;
            if(StatusVideo == true){
                as.animation_Back((float)mWidth,-(float)mHeight,"Video");
                bt_Write_Area.setEnabled(true);
                StatusVideo = false;
            }
            else if(StatusWrite == true){
                as.animation_Back(-(float)mWidth,-(float)mHeight,"Write");
                bt_Video_Area.setEnabled(true);
                StatusWrite = false;
            }
            return CanToNext = false;
        }
        return false;
    }
    //當點擊寫字區
    public void To_Write(View view) {
        moving = true;
        StatusWrite = true;
        CanToNext = true;
        ToNextPageCount++;
        bt_Video_Area.setEnabled(false);
        if(CanToNext == true && ToNextPageCount == 2){
            CanToNext = false;
            ToNextPageCount = 0;
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, StrokeList.class);
            startActivity(intent);

            as.animation_Back(-(float)mWidth,-(float)mHeight,"Write");
            bt_Video_Area.setEnabled(true);
            moving = false;
        }
        else{
            getLoc();
            moving = as.animation_Move(-(float)mWidth/*-180.0F*/,-(float)mHeight/*-270.0F*/,"Write");
        }
    }
    //當點擊影片區
    public void To_Video(View view){
        moving = true;
        StatusVideo = true;
        CanToNext = true;
        ToNextPageCount += 3;
        bt_Write_Area.setEnabled(false);
        if(CanToNext == true && ToNextPageCount == 6){
            CanToNext = false;
            ToNextPageCount = 0;
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,video_area.class);
            startActivity(intent);

            as.animation_Back((float)mWidth,-(float)mHeight,"Video");
            bt_Write_Area.setEnabled(true);
            moving = false;
        }
        else{
            getLoc();
            moving = as.animation_Move((float)mWidth,-(float)mHeight,"Video");
        }
    }
    //取得兩button的位置
    private void getLoc() {
        int []video_Loc = new int[4];
        int []write_Loc = new int[4];
        video_Loc[0] = bt_Video_Area.getLeft();
        video_Loc[1] = bt_Video_Area.getTop();
        video_Loc[2] = bt_Video_Area.getRight();
        video_Loc[3] = bt_Video_Area.getBottom();

        write_Loc[0] = bt_Write_Area.getLeft();
        write_Loc[1] = bt_Write_Area.getTop();
        write_Loc[2] = bt_Write_Area.getRight();
        write_Loc[3] = bt_Write_Area.getBottom();
        for(int i=0;i<video_Loc.length;i++){
            Video_Area_Loc[i] = video_Loc[i];
            Write_Area_Loc[i] = write_Loc[i];
        }
    }
}