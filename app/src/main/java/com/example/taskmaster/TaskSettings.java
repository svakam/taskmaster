package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TaskSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_settings);

        final Button savePrefs = findViewById(R.id.settingsSubmit);
        savePrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEntry = findViewById(R.id.editUser);
                if (usernameEntry != null) {
                    String editedUser = usernameEntry.getText().toString();
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = p.edit();
                    editor.putString("username", editedUser);
                    editor.apply();
                }
            }
        });

    }
}
