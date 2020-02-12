package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        String taskToView = getIntent().getStringExtra("task");
        TextView taskDetailTitle = findViewById(R.id.textView);
        taskDetailTitle.setText(taskToView);
    }
}
