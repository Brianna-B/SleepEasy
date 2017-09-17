package com.example.lumoteam.sleepeasy;

/**
 * Created by xuany on 9/16/17.
 */

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;


public class SoundPlay {
    private SoundPool sounds;

    public SoundPlay(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sounds = new SoundPool.Builder()
                    .setMaxStreams(8)
                    .build();
        } else {
            sounds = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        }
        sounds.load(context, R.raw.bird, 1);
        sounds.load(context, R.raw.blastwave, 1);
        sounds.load(context, R.raw.creek_1, 1);
        sounds.load(context, R.raw.creek_2, 1);
        sounds.load(context, R.raw.fire_1, 1);
        sounds.load(context, R.raw.fire_2, 1);
        sounds.load(context, R.raw.insects_1, 1);
        sounds.load(context, R.raw.insects_2, 1);
        sounds.load(context, R.raw.lake, 1);
        sounds.load(context, R.raw.ocean, 1);
        sounds.load(context, R.raw.rain_1, 1);
        sounds.load(context, R.raw.rain_2, 1);
        sounds.load(context, R.raw.thunder, 1);
        sounds.load(context, R.raw.waterfall, 1);
        sounds.load(context, R.raw.wind, 1);
    }

    public void play(Context context) {
        sounds.play(1,1,1,0,-1,1);
        sounds.play(2,1,1,0,-1,1);
//        sounds.play(3,1,1,0,-1,1);
//        sounds.play(4,1,1,0,-1,1);
        sounds.play(5,1,1,0,-1,1);
        sounds.play(6,1,1,0,-1,1);
        sounds.play(7,1,1,0,-1,1);
        sounds.play(8,1,1,0,-1,1);
//        sounds.play(9,1,1,0,-1,1);
//        sounds.play(10,1,1,0,-1,1);
//        sounds.play(11,1,1,0,-1,1);
//        sounds.play(12,1,1,0,-1,1);
        sounds.play(13,1,1,0,-1,1);
//        sounds.play(14,1,1,0,-1,1);
        sounds.play(15,1,1,0,-1,1);
    }

    public void stop() {
        sounds.stop(1);
        sounds.stop(2);
    }
}
