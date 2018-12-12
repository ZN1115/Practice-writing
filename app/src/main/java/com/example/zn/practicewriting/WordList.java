package com.example.zn.practicewriting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordList extends AppCompatActivity {

    private TextView Stroke_Title;
    private RecyclerView rv_WordList;
    private ArrayList<String> DataSize;
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
        int resId = getResources().getIdentifier(name,"array",getPackageName());
        String[] temp = getResources().getStringArray(resId);
        for (int i = 0; i < temp.length; i++) {
            DataSize.add(temp[i]);
        }
    }
    public void myItemClick(View view){
        //Get the Position for any View
        int view_Pos = rv_WordList.findContainingViewHolder(view).getAdapterPosition();

        //Intent Setting
        Intent intent = new Intent();
        intent.setClass(WordList.this,DrawingBoard.class);

        //Next Activity
        startActivity(intent);

        //Test
        String word = DataSize.get(view_Pos);
        Toast.makeText(this,word,Toast.LENGTH_SHORT).show();
    }
}
