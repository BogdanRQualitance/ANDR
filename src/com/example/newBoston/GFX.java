package com.example.newBoston;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author brata@tremend.ro
 * @since 25.03.2013
 */
public class GFX extends Activity {
    MyBringBack ourView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }
}
