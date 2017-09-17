package com.example.lumoteam.sleepeasy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class settings extends AppCompatActivity {
    public android.widget.EditText playTime;
    public android.widget.EditText idealSleepDuration;
    private android.widget.Button saveSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        saveSetting = (Button) findViewById(R.id.saveSetting);
        saveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                playTime = (EditText) findViewById(R.id.playTime);
                idealSleepDuration = (EditText) findViewById(R.id.idealSleepDuration);
            }
        });

    }

}
