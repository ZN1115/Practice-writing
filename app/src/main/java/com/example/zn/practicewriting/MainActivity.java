package com.example.zn.practicewriting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    private ImageButton Video_Array;
    private ImageButton Write_Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Video_Array = (ImageButton) findViewById(R.id.bt_main_video);
        Write_Array = (ImageButton) findViewById(R.id.bt_main_video);

    }

    public void To_Write(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SortWord.class);
        startActivity(intent);
    }
}