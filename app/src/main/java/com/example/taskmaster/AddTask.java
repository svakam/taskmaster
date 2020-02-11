package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    static String TAGADD = "va.addTask";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // log for oncreate success
        Log.w(TAGADD, "we are in onCreate");

        final Button submit = findViewById(R.id.addTaskSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView declareSubmitted = findViewById(R.id.addTaskDecSubmit);
                declareSubmitted.setVisibility(View.VISIBLE);
            }
        });
    }
}
