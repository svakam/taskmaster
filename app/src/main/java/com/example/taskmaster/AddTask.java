package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.CreateTaskMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.w3c.dom.Text;

import javax.annotation.Nonnull;

import type.CreateTaskInput;

public class AddTask extends AppCompatActivity {

    static String TAGADD = "va.addTask";
    private AWSAppSyncClient mAWSAppSyncClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // set up AWS in Java code - AppSyncClient to communicate with AWS
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        // log for oncreate success
        Log.w(TAGADD, "we are in onCreate");

        final Button submit = findViewById(R.id.addTaskSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titleEditText = findViewById(R.id.editText);
                final TextView declareSubmitted = findViewById(R.id.addTaskDecSubmit);
                if (titleEditText.getText().length() == 0) {
                    declareSubmitted.setText("Please enter a title.");
                } else {
                    declareSubmitted.setText("Submitted!");
                }
                declareSubmitted.setVisibility(View.VISIBLE);
            }
        });
    }

    public void viewTask(View v) {
        Intent i = new Intent(this, TaskDetail.class);
        TextView taskNameEditText = findViewById(R.id.taskDetailTitle);
        String taskName = taskNameEditText.getText().toString();
        String body = "hi";
        String state = "sup";

        CreateTaskInput input = CreateTaskInput.builder()
                .title(taskName)
                .body(body)
                .state(state)
                .build();
        mAWSAppSyncClient.mutate(CreateTaskMutation.builder().input(input).build())
                .enqueue(new GraphQLCall.Callback<CreateTaskMutation.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
                        Log.i(TAGADD, response.data().toString());
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                            Log.i(TAGADD, "failure");
                    }
                });
    }
}
