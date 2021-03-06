package com.example.zn.practicewriting.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zn.practicewriting.Board.DrawingBoard;
import com.example.zn.practicewriting.Others.ErrorDialog;
import com.example.zn.practicewriting.Model.DataHandler;
import com.example.zn.practicewriting.R;

import java.util.ArrayList;

public class WordList extends AppCompatActivity {

    private TextView Stroke_Title;
    private RecyclerView rv_WordList;
    private ArrayList<String> DataSize;
    private DataHandler mSender;
    private String word;
    ErrorDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_recycleview);
        init();

        Stroke_Title.setText(getBundleData("Title"));

        DataSetting();

        rv_WordList.setLayoutManager(new GridLayoutManager(this,4));
        rv_WordList.setAdapter(new WordAdapter(DataSize));
    }

    //initialize
    public void init(){
        Stroke_Title = findViewById(R.id.tv_Stroke_Title);
        rv_WordList = findViewById(R.id.rv_Word);
        DataSize = new ArrayList<>();
    }

    //get the title or Stroke
    public String getBundleData(String key){
        //Bundle getter
        Bundle bundle_title = this.getIntent().getExtras();
        String temp = bundle_title.getString(key);
        return temp;
    }

    public void DataSetting(){
        String name = getBundleData("Stroke");
        mSender = new DataHandler(this);
        DataSize = mSender.getWordData(name, DataSize);
    }

    public void myItemClick(View view){
        //Get the Position for any View
        int view_Pos = rv_WordList.findContainingViewHolder(view).getAdapterPosition();
        word = DataSize.get(view_Pos);
        dialog = new ErrorDialog(this,R.style.Dialog);
        dialog.show();
    }

    public void To_Word_Mean(View view) {
        dialog.cancel();

        Intent intent = new Intent();
        intent.setClass(WordList.this,WordMean.class);

        Bundle bundle = new Bundle();
        bundle.putString("wordName",word);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void To_Word_DrawingBoard(View view) {
        dialog.cancel();
        //Intent Setting
        Intent intent = new Intent();
        intent.setClass(WordList.this,DrawingBoard.class);

        Bundle bundle = new Bundle();
        bundle.putString("wordCode",getBundleData("Stroke"));
        bundle.putString("wordName",word);
        intent.putExtras(bundle);

        //Next Activity
        startActivity(intent);

        //Test
        //Toast.makeText(this,word,Toast.LENGTH_SHORT).show();
    }
}
