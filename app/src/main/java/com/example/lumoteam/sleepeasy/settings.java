package com.example.lumoteam.sleepeasy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


        String key = getApplicationContext().getString(R.string.preference_settings);
        final SharedPreferences settingsPref = getApplicationContext().getSharedPreferences(key, Context.MODE_PRIVATE);
        final String time_key = getApplicationContext().getString(R.string.play_time_min);
        final String duration_key = getApplicationContext().getString(R.string.sleep_duration_min);

        playTime = (EditText) findViewById(R.id.playTime);
        idealSleepDuration = (EditText) findViewById(R.id.idealSleepDuration);
        playTime.setText("play time: " + settingsPref.getInt(time_key, 60));
        idealSleepDuration.setText("sleep duration: " + settingsPref.getInt(duration_key, 8*60));

        saveSetting = (Button) findViewById(R.id.saveSetting);
        saveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                SharedPreferences.Editor editor = settingsPref.edit();
                editor.putInt(time_key, Integer.getInteger(playTime.getText().toString()));
                editor.putInt(duration_key, Integer.getInteger(idealSleepDuration.getText().toString()));
                editor.apply();

                android.content.Intent intent = new Intent(settings.this, HomePage.class);
                startActivity(intent);
            }
        });

    }

}
