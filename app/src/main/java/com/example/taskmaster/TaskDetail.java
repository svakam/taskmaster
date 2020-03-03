package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        // get title from page
        String taskToView = getIntent().getStringExtra("task");
        TextView taskDetailTitle = findViewById(R.id.taskDetailTitle);
        taskDetailTitle.setText(taskToView);

        // get body from page
        String body = getIntent().getStringExtra("body");
        TextView taskDetailBody = findViewById(R.id.taskDetailDescription);
        taskDetailBody.setText(body);

        // get state from page
        String state = getIntent().getStringExtra("state");
        TextView taskDetailState = findViewById(R.id.taskDetailState);
        taskDetailState.setText(state);

    }
}
