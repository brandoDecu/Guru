package com.example.guru.Music;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.guru.R;

import static com.example.guru.ApplicationClass.USER_FILENAME_SHAREDPREFS;
import static com.example.guru.Music.MusicUtils.getSeekProgress;
import static com.example.guru.Music.MusicUtils.millisecondsToTime;
import static com.example.guru.Music.MusicUtils.progressToTimer;

public class PlayMusicActivity extends AppCompatActivity {

    private static final String TAG = "PlayMusicActivity";

    private MediaPlayer music;
    private ImageView playButton;
    private TextView title;
    private SeekBar seekbar;
    private TextView progress;
    private TextView duration;
    private Handler mHandler = new Handler();
    private int yogaNidra;
    private int currentSongIndex;
    private int[] songs;
    boolean isAirplaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        playButton = findViewById(R.id.play);
        seekbar = findViewById(R.id.seek_bar);
        seekbar.setMax(MusicUtils.MAX_PROGRESS);
        duration = findViewById(R.id.duration);
        progress = findViewById(R.id.progress);
        title = findViewById(R.id.music_title);

        songs = new int[] {R.raw.med3min, R.raw.med7min, R.raw.med11min, R.raw.med30min,
                R.raw.part1_survey_of_body, R.raw.part_2_61_points_relaxation, R.raw.part_3_ascending_breath,
                R.raw.part_4_yoga_nidra};

        configureButtonsAndSeekbar();


        int index = intent.getIntExtra("index", 0);
        title.setText(intent.getStringExtra("title"));

        SharedPreferences prefs = getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE);
        isAirplaneMode = prefs.getBoolean("airplanemode", false);
        if (isAirplaneMode) {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS), 0);
        }
        play(index);
    }



    private Runnable mUpdateTimeTasks = new Runnable() {
        @Override
        public void run() {
            updateTimerAndSeekBar();
            if (music.isPlaying())  {
                mHandler.postDelayed(this, 100);
            }
        }
    };

    public void play(int index) {
        Log.d(TAG, "song index about to play: " + index);
        currentSongIndex = index;
        if (songs[index] == R.raw.part_4_yoga_nidra) {
            if (yogaNidra == 0) {
                music = MediaPlayer.create(this, R.raw.part1_survey_of_body);
                music.start();
                music.pause();
                playButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
            } else if (yogaNidra == 1) {
                music = MediaPlayer.create(this, R.raw.part_2_61_points_relaxation);
                music.start();
                mHandler.post(mUpdateTimeTasks);
            } else if (yogaNidra == 2) {
                music = MediaPlayer.create(this, R.raw.part_3_ascending_breath);
                music.start();
                mHandler.post(mUpdateTimeTasks);
            } else if (yogaNidra == 3) {
                music = MediaPlayer.create(this, R.raw.part_4_yoga_nidra);
                music.start();
                mHandler.post(mUpdateTimeTasks);
            } else {
                yogaNidra = 0;
                return;
            }
            yogaNidra++;
        } else {
            music = MediaPlayer.create(this, songs[index]);
            Log.d(TAG, "MediaPlayer created with song ID: " + index);
            music.start();
            music.pause();
            playButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
        }

        music.setLooping(false);
        music.setOnCompletionListener(new PlayMusicActivity.OnCompletionListener(this));
        updateTimerAndSeekBar();
    }

    private void configureButtonsAndSeekbar() {

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button clicked. Song ID: " + music.getAudioSessionId());
                if (music.isPlaying()) {
                    Log.d(TAG, "pause called");
                    music.pause();
                    playButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                } else {
                    music.start();
                    playButton.setImageResource(R.drawable.ic_pause_black_36dp);
                    mHandler.post(mUpdateTimeTasks);
                }

            }
        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mUpdateTimeTasks);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mUpdateTimeTasks);
                int currentTime = progressToTimer(seekBar.getProgress(), music.getDuration());
                Log.d(TAG, "seeking to: " + currentTime);
                music.seekTo(currentTime);
                mHandler.post(mUpdateTimeTasks);

            }
        });
    }


    private void updateTimerAndSeekBar() {
        long durationMillis = music.getDuration();
        long progressMillis = music.getCurrentPosition();
        duration.setText(millisecondsToTime(durationMillis));
        progress.setText(millisecondsToTime(progressMillis));
        int seekBarProgress = getSeekProgress(progressMillis, durationMillis);
        seekbar.setProgress(seekBarProgress);


    }


    public class OnCompletionListener implements MediaPlayer.OnCompletionListener {
        private PlayMusicActivity activity;

        public OnCompletionListener(PlayMusicActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            if (songs[activity.currentSongIndex] == R.raw.part_4_yoga_nidra && activity.yogaNidra < 4) {

                mHandler.removeCallbacks(mUpdateTimeTasks);
                music.release();
                activity.play(currentSongIndex);
                Log.d(TAG, "Yoga Nidra Part " + activity.yogaNidra + "completed");


            } else {
                playButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                Log.d(TAG, "Track Completed");
                activity.onBackPressed();
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (music != null && music.isPlaying()) {
            mHandler.removeCallbacks(mUpdateTimeTasks);
            music.release();
            music = null;
        }
        if (isAirplaneMode) {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS), 0);
        }
        super.onBackPressed();
    }


}
