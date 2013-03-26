package com.example.newBoston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * @author brata@tremend.ro
 * @since 25.03.2013
 */
public class GFX extends Activity {
    MyBringBack ourView;
    PowerManager.WakeLock wl;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "fullLock");
        ourView = new MyBringBack(this);
        wl.acquire();
        setContentView(ourView);
    }

    @Override
    protected void onPause () {
        super.onPause();
        wl.release();
    }
}
