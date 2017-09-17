package com.example.lumoteam.sleepeasy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wymay on 2017-09-16.
 */

public enum NoisePickManager {
    INSTANCE;

    private Context context;

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    private List<Float> getScores() {
        SharedPreferences scoresPref = context.getSharedPreferences(context.getString(R.string.preference_scores), Context.MODE_PRIVATE);
        Map<String, ?> scoresMap = scoresPref.getAll();
        List<Float> scores = new ArrayList<>();
        for (Map.Entry<String, ?> entry : scoresMap.entrySet()){
            scores.add(Integer.valueOf(entry.getKey()), Float.valueOf(entry.getValue().toString()));
        }
        return scores;
    }

    public List<Integer> pick(int num) {
        List<Integer> pickedNoises = new ArrayList<>();
        List<Float> scores = getScores();
        SharedPreferences lastPickedPref = context.getSharedPreferences(context.getString(R.string.preference_lastpicked), Context.MODE_PRIVATE);
        SharedPreferences.Editor lastPickedEditor = lastPickedPref.edit();
        lastPickedEditor.clear();
        for(int i=0; i<num; ++i) {
            float sum = 0;
            for (Float value : scores) sum += value;
            double ran = Math.random() * sum;
            double count = 0;
            for (int j = 0; j < scores.size(); j++) {
                count += scores.get(j);
                if (ran < count) {
                    scores.set(j, 0.0f);
                    pickedNoises.add(j);
                    lastPickedEditor.putInt(String.valueOf(i), j);
                }
            }
        }
        lastPickedEditor.apply();
        return pickedNoises;
    }

    private void updateWithVal(float value) {
        SharedPreferences trendValuePref = context.getSharedPreferences(context.getString(R.string.preference_trend_value), Context.MODE_PRIVATE);
        Float trendValue = trendValuePref.getFloat(context.getString(R.string.preference_trend_value), 0);
        SharedPreferences lastPickedPref = context.getSharedPreferences(context.getString(R.string.preference_lastpicked), Context.MODE_PRIVATE);
        SharedPreferences scoresPref = context.getSharedPreferences(context.getString(R.string.preference_scores), Context.MODE_PRIVATE);
        SharedPreferences.Editor scoresEditor = scoresPref.edit();

        Float scale = (value - trendValue)/trendValue;
        Map<String, ?> lastPickedMap = lastPickedPref.getAll();
        for(Map.Entry<String, ?> entry : lastPickedMap.entrySet()){
            String scoresKey = String.valueOf(entry.getValue());
            Float currentScores = scoresPref.getFloat(scoresKey, 0);

            Float newScores = currentScores * (1 + scale);
            scoresEditor.putFloat(scoresKey, newScores);
        }
        scoresEditor.apply();
        trendValue = trendValue * 0.6f + value * 0.4f;
        trendValuePref.edit().putFloat(context.getString(R.string.preference_trend_value), trendValue).apply();
    }

    public void update(Date fallAsleepTime, Date getIntoBedTime, Date wakeUpTime, Date plannedWakeUpTime, int selfEval) {
        Float sleepSec = (fallAsleepTime.getTime() - getIntoBedTime.getTime())/1000f;
        Float score1 = sleepSec < 15*60 ? 100 : 100 - (sleepSec/60 - 15);
        Float wakeUpSec = (plannedWakeUpTime.getTime() - wakeUpTime.getTime())/1000f;
        Float score2 = wakeUpSec > 0 ? 100 : 100 + wakeUpSec/60;
        updateWithVal(score1*0.5f + score2*0.25f + selfEval*0.25f);
    }
}
