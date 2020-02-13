package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

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
        final Button addTask = findViewById(R.id.addTask);
        // anonymous inner class: define a class that implements View.OnClickListener, right here inline
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTask.class);
                startActivity(i);
            }
        });

        final Button allTasks = findViewById(R.id.allTasks);
        // anonymous inner class
        allTasks.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AllTasks.class);
                startActivity(i);
            }
        });

        // redirect to gym task
        TextView gym = findViewById(R.id.gymTitle);
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView gym = findViewById(R.id.gymTitle);
                Intent i = new Intent(MainActivity.this, TaskDetail.class).putExtra("task", gym.getText().toString());
                startActivity(i);
            }
        });

        // redirect to guitar task
        TextView playGuitar = findViewById(R.id.guitarTitle);
        playGuitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView guitar = findViewById(R.id.guitarTitle);
                Intent i = new Intent(MainActivity.this, TaskDetail.class).putExtra("task", guitar.getText().toString());
                startActivity(i);
            }
        });

        // redirect to run task
        TextView run = findViewById(R.id.runTitle);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView run = findViewById(R.id.runTitle);
                Intent i = new Intent(MainActivity.this, TaskDetail.class).putExtra("task", run.getText().toString());
                startActivity(i);
            }
        });

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
        Log.i(TAG, username);

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
