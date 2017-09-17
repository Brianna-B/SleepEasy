package com.example.lumoteam.sleepeasy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class log extends AppCompatActivity {
    public android.widget.EditText sleepTime;
    public android.widget.EditText wakeTime;
    public android.widget.EditText outOfBedTime;
    public float sleepEfficiency;
    private android.widget.Button saveLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        saveLog = (Button) findViewById(R.id.saveLog);
        saveLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                sleepTime = (EditText) findViewById(R.id.sleepTime);
                wakeTime = (EditText) findViewById(R.id.wakeTime);
                outOfBedTime = (EditText) findViewById(R.id.outOfBedTime);
            }

        });

    }
}