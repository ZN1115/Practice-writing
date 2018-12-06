package com.example.zn.practicewriting;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.LinkedHashMap;

public class SortWord extends Activity {


    private RecyclerView recycler_view;
    private MyAdapter adapter;
    private LinkedHashMap<Integer,String> mData = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recycleview);

        // 準備資料，塞50個項目到ArrayList裡
        for (int i = 1; i <= 50; i++) {
            if(i<11)
                mData.put(i,"NO.100");
            else if(11 <= i && i < 21)
                mData.put(i,"NO.101");
            else if(21 <= i && i <31)
                mData.put(i,"NO.102");
            else if(31 <= i && i<41)
                mData.put(i,"NO.103");
            else
                mData.put(i,"NO.104");
        }

        for(int number : mData.keySet()){
            System.out.println(number + " : "+mData.get(number));
        }

        // 連結元件
        recycler_view = (RecyclerView) findViewById(R.id.RecycleView);
        // 設置RecyclerView為列表型態
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recycler_view.setLayoutManager(layoutManager);


        // 將資料交給adapter
        adapter = new MyAdapter(mData);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(adapter);
    }
    /*點擊事件*/public void myItemClick(View view){
    }

}
