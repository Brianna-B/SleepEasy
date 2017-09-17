package com.example.lumoteam.sleepeasy;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import android.widget.EditText;

public class log extends AppCompatActivity {
    public android.widget.EditText sleepTime;
    public android.widget.EditText wakeTime;
    public android.widget.EditText outOfBedTime;
    public float sleepEfficiency;
    private android.widget.Button saveLog;

    private Calendar sleepCalendar;
    private Calendar wakeupCalendar;
    private Calendar outOfBedCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Button saveLog = (Button) findViewById(R.id.saveLog);
        final TextView sleepTime = (TextView) findViewById(R.id.sleepTime);
        sleepTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment fragment = new TimePickerFragment();
                fragment.setListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        sleepCalendar = Calendar.getInstance();
                        sleepCalendar.set(Calendar.HOUR, hourOfDay);
                        sleepCalendar.set(Calendar.MINUTE, minute);
                        if(hourOfDay < 12) sleepCalendar.add(Calendar.DAY_OF_YEAR, -1);
                    }
                });
                fragment.show(getSupportFragmentManager(), "sleepTime");
            }
        });
        TextView wakeTime = (TextView) findViewById(R.id.wakeTime);
        wakeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment fragment = new TimePickerFragment();
                fragment.setListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        wakeupCalendar = Calendar.getInstance();
                        wakeupCalendar.set(Calendar.HOUR, hourOfDay);
                        wakeupCalendar.set(Calendar.MINUTE, minute);
                    }
                });
                fragment.show(getSupportFragmentManager(), "wakeTime");
            }
        });
        final TextView outOfBedTime = (TextView) findViewById(R.id.outOfBedTime);
        outOfBedTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment fragment = new TimePickerFragment();
                fragment.setListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        outOfBedCalendar = Calendar.getInstance();
                        outOfBedCalendar.set(Calendar.HOUR, hourOfDay);
                        outOfBedCalendar.set(Calendar.MINUTE, minute);
                    }
                });
                fragment.show(getSupportFragmentManager(), "outOfBedTime");
            }
        });
        saveLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RatingBar ratingBar = (RatingBar) findViewById(R.id.sleepWell);
                String key = getApplicationContext().getString(R.string.preference_getIntoBedDate);
                SharedPreferences datePref = getApplicationContext().getSharedPreferences(key, Context.MODE_PRIVATE);
                Date intoBedDate = new Date(datePref.getLong(key, 0));
                NoisePickManager.INSTANCE.update(sleepCalendar.getTime(), intoBedDate, wakeupCalendar.getTime(), new Date(), ratingBar.getNumStars()*20);
                android.content.Intent intent = new Intent(log.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}