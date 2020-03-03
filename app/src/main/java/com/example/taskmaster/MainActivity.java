package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static String TAG = "va.main";

    // oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView, R
        // actually set contents to be the activity_main XML file
        setContentView(R.layout.activity_main);
        // setup work
        // logging: verbose, debug, info, warning, error, wtf
        Log.w(TAG, "we are in onCreate");

        // Room replaced with DynamoDB
//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "task")
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build();
//        UserDao dao = db.userDao();
//        dao.addTask(newTask2);
//        dao.addTask(newTask);
        List<Task> listOfTasks = new LinkedList<>();
        Task newTask = new Task("Gym", "Hit the gym by 6!", "done");
        Task newTask2 = new Task("Guitar", "Play some Van Halen", "in progress");
        listOfTasks.add(newTask);
        listOfTasks.add(newTask2);
//        Log.i(TAG, listOfTasks.toString());

        // upon clicking Add Task, go to Add Task page
        final Button addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTask.class);
                startActivity(i);
            }
        });

        // recyclerview setup
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mainfragment);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // upon clicking All Tasks, go to All Tasks page
        final Button allTasks = findViewById(R.id.allTasks);
        // anonymous inner class
        allTasks.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AllTasks.class);
                startActivity(i);
            }
        });


        MyTaskRecyclerViewAdapter adapter = new MyTaskRecyclerViewAdapter(listOfTasks, null, this);
        recyclerView.setAdapter(adapter);

        // redirect to settings
        ImageView settings = findViewById(R.id.settingsLogo);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TaskSettings.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "resumed");

        TextView mainHeader = findViewById(R.id.mainHeader);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPreferences.getString("username", "") + " Tasks";
        mainHeader.setText(username);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "stopped");
    }
}
