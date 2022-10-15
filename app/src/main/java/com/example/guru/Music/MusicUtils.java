package com.example.guru.Music;

import android.util.Log;

public class MusicUtils {

    private static final String TAG = "MusicUtils";

    public static final int MAX_PROGRESS = 10000;

    public static String millisecondsToTime(long millis) {
        String time = "";

        int hours = (int) millis / (60*60*1000);
        int minutes = (int) (millis % (60*60*1000)) / (60*1000);
        int seconds = (int) (millis % (60*1000)) / 1000;

        boolean hasHours = false;
        if (hours>0) {
            hasHours = true;
            time = time + hours + ":";
        }
        if (minutes>0) {
            if (hasHours && minutes < 10) {
                String minutesString = "0" + minutes;
                time = time + minutesString + ":";
            } else {
                time = time + minutes + ":";
            }
        } else if (hasHours) {
            time = time + "00:";
        } else {
            time = time + "0:";
        }

        if (seconds<10) {
            time = time + "0" + seconds;
        } else {
            time = time + seconds;
        }
        return time;
    }

    public static int getSeekProgress(long currentDuration, long totalDuration) {
        Double progress = ((currentDuration / (double) totalDuration) * MAX_PROGRESS);
        return progress.intValue();
    }

    public static int progressToTimer(int progress, int totalDuration) {
        Log.d(TAG, "progress: " + progress);
        Log.d(TAG, "totalduration: " + totalDuration);
        int currentDuration = (int) (((double) progress)/MAX_PROGRESS * totalDuration);
        return currentDuration;
    }

}
