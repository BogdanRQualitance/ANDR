package com.example.newBoston;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author brata@tremend.ro
 * @since 22.03.2013
 */
public class Prefs extends PreferenceActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        addPreferencesFromResource(R.xml.prefs);
    }
}
