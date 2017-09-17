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
import android.view.View.OnClickListener;

import android.widget.Button;
import android.util.Log;

import java.util.Date;

public class HomePage extends AppCompatActivity {
    private android.widget.Button play;
    private android.widget.Button log;
    private android.widget.Button settings;
    private static SoundPlay soundPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        SoundPlay.INSTANCE.init(getApplicationContext());
        NoisePickManager.INSTANCE.init(getApplicationContext(),15);
        log = (Button) findViewById(R.id.Log);
        play = (Button) findViewById(R.id.Play);
        settings = (Button) findViewById(R.id.Settings);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long datetime = new Date().getTime();
                String key = getApplicationContext().getString(R.string.preference_getIntoBedDate);
                SharedPreferences datePref = getApplicationContext().getSharedPreferences(key, Context.MODE_PRIVATE);
                datePref.edit().putLong(key, datetime).apply();
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.content.Intent intent = new Intent(HomePage.this, log.class);
                startActivity(intent);
            }

        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.content.Intent intent = new Intent(HomePage.this, settings.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPlay.stop();
        getDelegate().onDestroy();
    }
}



