package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TaskSettings extends AppCompatActivity {

    static String TAG = "va.settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_settings);

        final Button savePrefs = findViewById(R.id.settingsSubmit);
        savePrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEntry = findViewById(R.id.editUser);
                String editedUser = usernameEntry.getText().toString();
                if (!editedUser.equals("")) {
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = p.edit();
                    editor.putString("username", editedUser);
                    editor.apply();
                    Log.w(TAG, editedUser);
                    Intent i = new Intent(TaskSettings.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter a new username.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP|Gravity.START,0,0);
                    toast.show();
                    Log.w(TAG, "no");
                }
            }
        });
    }
}
