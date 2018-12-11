package com.example.zn.practicewriting;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class SortWord extends Activity {

    private RecyclerView recycler_view;
    private ArrayList<String> DataSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recycleview);
        init();

        String[] temp = getResources().getStringArray(R.array.Stroke);
        for (int i = 0; i < temp.length; i++) {
            DataSize.add(temp[i]);
        }

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
        intent.setClass(SortWord.this,WordList.class);

        //Bundle Title and Stroke Code
        Bundle bundle = new Bundle();
        bundle.putString("Title",DataSize.get(view_Pos));
        bundle.putString("Stroke","Word_"+makeCode(view_Pos+1));
        intent.putExtras(bundle);

        //Next Activity
        startActivity(intent);

        //Test
        String str_Stroke = DataSize.get(view_Pos);
        Toast.makeText(this,str_Stroke,Toast.LENGTH_SHORT).show();
    }

    //make code for data(Hexadecimal)
    public String makeCode(int number){
        String Code = Integer.toHexString(number).toUpperCase();
        while(Code.length() < 3){
            Code = "0"+Code;
        }
        return Code;
    }
}
