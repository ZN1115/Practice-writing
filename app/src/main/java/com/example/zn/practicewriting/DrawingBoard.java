package com.example.zn.practicewriting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class DrawingBoard extends AppCompatActivity {

    public ImageView mImageView;
    private GifImageView mGifImageView;
    private GifDrawable mGifDrawable;
    private Button btn_Multi_Status;
    private Button btn_NextWord;
    private Timer mTimer;
    private DrawTool mDrawTool;
    private DataHandler mSender;
    private ArrayList<DrawableDataType> DataSize;
    private String wordTmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_board);

        init();
        DataSize = mSender.getDrawableData(getBundleData("wordCode"),DataSize);

        try{
            int resId = mSender.getResId(wordTmp,DataSize);
            mGifDrawable = new GifDrawable(getResources(),resId);
            mGifImageView.setImageDrawable(mGifDrawable);
            mGifDrawable.setLoopCount(1);
            mGifDrawable.stop();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        mDrawTool = new DrawTool();
        mDrawTool.initPaint();
        mDrawTool.setOwner(mImageView);
        mImageView.setOnTouchListener(mDrawTool.touch);
    }

    public void init(){
        mGifImageView = findViewById(R.id.iv_Gif);
        mImageView = findViewById(R.id.iv_Canvas);
        btn_Multi_Status = findViewById(R.id.anime_Start);
        btn_NextWord = findViewById(R.id.word_Next);
        DataSize = new ArrayList<>();
        mSender = new DataHandler(this);
        wordTmp = getBundleData("wordName");
    }

    public String getBundleData(String key){
        Bundle bundle_title = this.getIntent().getExtras();
        String temp = bundle_title.getString(key);
        return temp;
    }

    public void resetClick(View view) {
        mGifDrawable.reset();
        btn_Multi_Status.setText(R.string.btn_Starting);
        btn_Multi_Status.setEnabled(false);
        btn_NextWord.setEnabled(false);

        int exec_Time = mGifDrawable.getDuration();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mGifDrawable.stop();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!mGifDrawable.isPlaying()){
                            btn_Multi_Status.setText(R.string.btn_Restart);
                            btn_Multi_Status.setEnabled(true);
                            btn_NextWord.setEnabled(true);
                            cancel();
                        }
                    }
                });
                cancel();
            }
        },exec_Time);
    }

    public void resumeCanvas(View view) {
        switch (view.getId()){
            case R.id.canvas_Clear:
                mDrawTool.resumeCanvas();
                Toast.makeText(DrawingBoard.this,
                        "清除畫板成功，可以重新開始繪圖",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public void wordNext(View view) {
        btn_Multi_Status.setText(R.string.btn_Start);
        try{
            wordTmp = mSender.getNextWord(wordTmp,DataSize);
            mGifDrawable.recycle();

            int resId = mSender.getResId(wordTmp,DataSize);
            mGifDrawable = new GifDrawable(getResources(),resId);
            mGifImageView.setImageDrawable(mGifDrawable);
            mGifDrawable.setLoopCount(1);
            mGifDrawable.stop();
        }
        catch (IndexOutOfBoundsException e){
            Toast.makeText(this,"已經是最後一個字了!",Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
