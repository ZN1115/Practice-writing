package com.example.zn.practicewriting.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zn.practicewriting.Animator.AnimationSet;
import com.example.zn.practicewriting.Others.InternetCheck;
import com.example.zn.practicewriting.Others.VersionCheck;
import com.example.zn.practicewriting.R;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageButton bt_Video_Area;
    private ImageButton bt_Write_Area;
    private TextView tv_Video_Area;
    private TextView tv_Write_Area;
    private final int[] Write_Area_Loc = new int[4];
    private final int[] Video_Area_Loc = new int[4];
    private int ToNextPageCount = 0;//數字為2進寫字，為6進影片
    private boolean CanToNext = false;//判斷是否可以進區域
    private boolean StatusVideo = false;//判斷現在是什麼狀態
    private boolean StatusWrite = false;
    private boolean moving = false;//是否移動中
    private AnimationSet as;
    private int mWidth;
    private int mHeight;
    private Timer mTimer;
    private VersionCheck versionCheck;
    private InternetCheck mITC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if(mITC.InternetState()) {
            versionCheck = new VersionCheck(this);
            VersionTip();
        }

        try{
            Thread.sleep(1000);
        }
        catch (Exception e){}
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
        mITC = new InternetCheck(this);
    }
    //是否點擊
    public boolean onTouchEvent(MotionEvent event) {

        if(!as.getAnimatorStatus()){
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
            as.animation_Move(-(float)mWidth,-(float)mHeight,"Write");
            moving = false;
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
            as.animation_Move((float)mWidth,-(float)mHeight,"Video");
            moving = false;
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

    private void VersionTip(){
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(versionCheck.getDataBase_Version() != null) {
                            if (versionCheck.isNewVersion()) {
                                Toast.makeText(getApplicationContext(), "有新版本，麻煩請到play商店進行更新!", Toast.LENGTH_SHORT).show();
                                cancel();
                            } else {
                                Toast.makeText(getApplicationContext(), "目前為最新版本!", Toast.LENGTH_SHORT).show();
                                cancel();
                            }
                        }
                    }
                });
            }
        },0,1000);
    }
}