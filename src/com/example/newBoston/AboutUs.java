package com.example.newBoston;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * @author brata@tremend.ro
 * @since 22.03.2013
 */
public class AboutUs extends Activity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);
    }
}
