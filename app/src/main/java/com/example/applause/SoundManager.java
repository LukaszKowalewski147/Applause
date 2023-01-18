package com.example.applause;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class SoundManager extends AppCompatActivity {

    private SoundPool soundPool;
    private int countdown;
    private int startClapping;

    public SoundManager(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(audioAttributes).build();
        } else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        countdown = soundPool.load(context, R.raw.countdown, 1);
        startClapping = soundPool.load(context, R.raw.start_clapping, 1);
    }

    public void playCountdownSound() {
        if (Session.soundsEnabled)
            soundPool.play(countdown, 1.0f, 1.0f, 0, 0, 1);
    }

    public void playStartClappingSound() {
        if (Session.soundsEnabled)
            soundPool.play(startClapping, 1.0f, 1.0f, 0, 0, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
