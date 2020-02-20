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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView, R
        // actually set contents to be the activity_main XML file
        setContentView(R.layout.activity_main);
        // setup work
        // logging: verbose, debug, info, warning, error, wtf
        Log.w(TAG, "we are in onCreate");

        // database
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        UserDao dao = db.userDao();
        Task newTask = new Task("Gym", "Hit the gym by 6!", "done");
        Task newTask2 = new Task("Guitar", "Play some Van Halen", "in progress");
        dao.addTask(newTask2);
        dao.addTask(newTask);
        List<Task> listOfTasks = dao.getAll();
        Log.i(TAG, listOfTasks.toString());

        final Button addTask = findViewById(R.id.addTask);
        // anonymous inner class: define a class that implements View.OnClickListener, right here inline
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTask.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mainfragment);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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
