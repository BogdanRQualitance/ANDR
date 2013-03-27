package com.example.newBoston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * @author brata@tremend.ro
 * @since 27.03.2013
 */
public class Flipper extends Activity implements View.OnClickListener {
    ViewFlipper flipper;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        flipper.setOnClickListener(this);
        flipper.setFlipInterval(1000);
        flipper.startFlipping();
    }

    @Override
    public void onClick (View view) {
        flipper.showNext();
    }
}
