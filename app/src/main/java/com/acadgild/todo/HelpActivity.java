package com.acadgild.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Pri on 9/15/2017.
 */

public class HelpActivity extends AppCompatActivity{
    TextView textView;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Help");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textView = (TextView) findViewById(R.id.help_list);
        textView.setText("1. Click on  add icon in toolbar to create a new task" + "\n \n" +
                "2. To edit any task, short click on that task." + "\n \n" +
                "3. To mark any task complete and vice-versa, long press on task." + "\n \n" +
                "4. To view completed tasks click on completed tasks button on toolbar." + "\n \n" +
                "5. To delete a task permanently, long press on tasks on CompletedTaskActivity.");
    }
    }

