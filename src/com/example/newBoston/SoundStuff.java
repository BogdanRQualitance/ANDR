package com.example.newBoston;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * @author brata@tremend.ro
 * @since 26.03.2013
 */
public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {
    SoundPool sp;
    int explosion = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.shoot2, 1);
        mp = MediaPlayer.create(this, R.raw.shoot);
    }

    @Override
    public void onClick (View view) {
        if (explosion != 0) {
            sp.play(explosion, 1, 1, 0, 0, 1);
        }
    }

    @Override
    public boolean onLongClick (View view) {
        mp.start();
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
