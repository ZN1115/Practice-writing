package com.example.zn.practicewriting;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class SortWord extends Activity {

    private RecyclerView recycler_view;
    private StrokeAdapter adapter;
    private ArrayList<String> DataSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recycleview);
        DataSize=new ArrayList<>();

        // 準備資料，塞50個項目到ArrayList裡
        for (int i = 1; i <= 16; i++) {
            DataSize.add(String.valueOf(i)+"筆劃");
        }
        for(int i=0;i<DataSize.size();i++){
            System.out.println(DataSize.get(i));
        }

        // 連結元件
        recycler_view = (RecyclerView) findViewById(R.id.RecycleView);
        // 設置RecyclerView為列表型態
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_view.setLayoutManager(layoutManager);

        // 將資料交給adapter
        adapter = new StrokeAdapter(DataSize);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(adapter);
    }

    /*點擊事件*/
    public void myItemClick(View view){

        Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show();
    }

}
