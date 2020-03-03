package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Arrays;

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
                EditText titleEditText = findViewById(R.id.addTitle);
                final TextView declareSubmitted = findViewById(R.id.addTaskDecSubmit);
                if (titleEditText.getText().length() == 0) {
                    declareSubmitted.setText("Please enter a title.");
                } else {
                    String taskName = titleEditText.getText().toString();
                    TextView addBody = findViewById(R.id.addBody);
                    String taskBody = addBody.getText().toString();
                    TextView addState = findViewById(R.id.addState);
                    String taskState = addState.getText().toString();

                    CreateTaskInput input = CreateTaskInput.builder()
                            .title(taskName)
                            .body(taskBody)
                            .state(taskState)
                            .build();
                    mAWSAppSyncClient.mutate(CreateTaskMutation.builder().input(input).build())
                            .enqueue(new GraphQLCall.Callback<CreateTaskMutation.Data>() {
                                @Override
                                public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
                                    Log.i(TAGADD, response.data().toString());
                                    declareSubmitted.setText("Submitted!");
                                }

                                @Override
                                public void onFailure(@Nonnull ApolloException e) {
                                    Log.i(TAGADD, "failure");
                                    Log.e(TAGADD, Arrays.toString(e.getStackTrace()));
                                    Log.e(TAGADD, e.getMessage());
                                    e.printStackTrace();
                                }
                            });
                }
                declareSubmitted.setVisibility(View.VISIBLE);
            }
        });
    }
}
