package com.example.lumoteam.sleepeasy;

/**
 * Created by xuany on 9/16/17.
 */

import android.media.AudioManager;
import android.media.SoundPool;
import android.content.Context;
import android.os.Build;
import java.util.List;


public enum SoundPlay {
    INSTANCE;

    private SoundPool candidates;
    private List<Integer> noises;

    public void init(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.candidates = new SoundPool.Builder()
                    .setMaxStreams(15)
                    .build();
        } else {
            this.candidates = new SoundPool(15, AudioManager.STREAM_MUSIC, 0);
        }
        this.candidates.load(context, R.raw.bird, 1);
        this.candidates.load(context, R.raw.blastwave, 1);
        this.candidates.load(context, R.raw.creek_1, 1);
        this.candidates.load(context, R.raw.creek_2, 1);
        this.candidates.load(context, R.raw.fire_1, 1);
        this.candidates.load(context, R.raw.fire_2, 1);
        this.candidates.load(context, R.raw.insects_1, 1);
        this.candidates.load(context, R.raw.insects_2, 1);
        this.candidates.load(context, R.raw.lake, 1);
        this.candidates.load(context, R.raw.ocean, 1);
        this.candidates.load(context, R.raw.rain_1, 1);
        this.candidates.load(context, R.raw.rain_2, 1);
        this.candidates.load(context, R.raw.thunder, 1);
        this.candidates.load(context, R.raw.waterfall, 1);
        this.candidates.load(context, R.raw.wind, 1);
    }

    public void play(List<Integer> noises) {
        this.noises = noises;
        for (int i = 0; i < noises.size(); i++) {
            candidates.play(noises.get(i)+1,1,1,0,-1,1);
        }
    }

    public void stop() {
        for (int i = 0; i < noises.size(); i++) {
            candidates.stop(i+1);
        }
    }

    public void pause() {
        candidates.autoPause();
    }

    public void resume() {
        candidates.autoResume();
    }
}
