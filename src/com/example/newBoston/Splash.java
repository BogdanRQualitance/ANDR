package com.example.newBoston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * @author brata@tremend.ro
 * @since 07.03.2013
 */
public class Splash extends Activity {
    MediaPlayer ourSong;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ourSong = MediaPlayer.create(Splash.this, R.raw.water_splash);

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("ckeckbox", true);
        if (music) {
            ourSong.start();
        }
        Thread timer = new Thread() {
            @Override
            public void run () {
                try {
                    sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent("com.example.newBoston.MENU");
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause () {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        ourSong.release();
        finish();

    }
}

