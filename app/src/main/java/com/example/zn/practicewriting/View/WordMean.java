package com.example.zn.practicewriting.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.zn.practicewriting.Model.DataBaseDataType;
import com.example.zn.practicewriting.Model.FireBaseData;
import com.example.zn.practicewriting.Others.InternetCheck;
import com.example.zn.practicewriting.R;
import java.util.Timer;
import java.util.TimerTask;

public class WordMean extends AppCompatActivity {

    private TextView tv_Word;
    private TextView tv_WordMean;
    private FireBaseData mFireBaseData;
    private DataBaseDataType mDataBase;
    private Timer mTimer;
    private InternetCheck mITC;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_mean);

        init();

        tv_Word.setText(getBundleData("wordName"));

        if(mITC.InternetState()){
            tv_WordMean.setText("載入中...");
            mDataBase = mFireBaseData.getInformation();
            UI_Information_Update();
        }
    }

    private void init(){
        tv_Word = (TextView) findViewById(R.id.tv_word);
        tv_WordMean = (TextView) findViewById(R.id.tv_mean);

        mFireBaseData = new FireBaseData("wordmean",getBundleData("wordName"));
        mITC = new InternetCheck(this);
    }

    private String getBundleData(String key){
        Bundle bundle = this.getIntent().getExtras();
        String temp = bundle.getString(key);
        return temp;
    }

    private void UI_Information_Update(){
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mDataBase.getMeanData() != null) {
                            tv_WordMean.setText(mDataBase.getMeanData());
                            cancel();
                        }
                    }
                });
            }
        },0,1000);
    }

    public void reload(View view) {
        if(mITC.InternetState()){
            tv_WordMean.setText("載入中...");
            mDataBase = mFireBaseData.getInformation();
            UI_Information_Update();
        }
    }
}
