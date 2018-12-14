package com.example.zn.practicewriting;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class StrokeList extends Activity {

    private RecyclerView recycler_view;
    private ArrayList<String> DataSize;
    private DataHandler mSender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recycleview);
        init();

        mSender = new DataHandler(this);
        DataSize = mSender.getStrokeData(DataSize);

        //設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new GridLayoutManager(this, 2));

        //設置adapter給recycler_view
        recycler_view.setAdapter(new StrokeAdapter(DataSize));
    }

    //initialize
    public void init(){
        recycler_view = (RecyclerView) findViewById(R.id.RecycleView);
        DataSize = new ArrayList<>();
    }

    public void myItemClick(View view){
        //Get the Position for any View
        int view_Pos = recycler_view.findContainingViewHolder(view).getAdapterPosition();

        //Intent Setting
        Intent intent = new Intent();
        intent.setClass(StrokeList.this,WordList.class);

        //Bundle Title and Stroke Code
        Bundle bundle = new Bundle();
        String Code = mSender.makeCode(view_Pos+1);

        bundle.putString("Title",DataSize.get(view_Pos));
        bundle.putString("Stroke","Word_"+Code);
        intent.putExtras(bundle);

        //Next Activity
        startActivity(intent);

        //Test
        String str_Stroke = DataSize.get(view_Pos);
        Toast.makeText(this,str_Stroke,Toast.LENGTH_SHORT).show();
    }
}
