package com.example.newBoston;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author brata@tremend.ro
 * @since 27.03.2013
 */
public class SharedPrefs extends Activity implements View.OnClickListener {
    public static final String SHARED_STRING = "sharedString";
    static final String FILENAME = "MyShareString";
    EditText shareData;
    TextView dataResult;
    SharedPreferences someData;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shar_prefs);
        setupVariable();
        someData = getSharedPreferences(FILENAME, 0);
    }

    private void setupVariable () {
        Button save = (Button) findViewById(R.id.bSavePrefs);
        Button load = (Button) findViewById(R.id.bLoadPrefs);
        shareData = (EditText) findViewById(R.id.etShareData);
        dataResult = (TextView) findViewById(R.id.tvResultShare);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.bSavePrefs:
                String stringData = shareData.getText().toString();
                SharedPreferences.Editor editor = someData.edit();
                editor.putString(SHARED_STRING, stringData);
                editor.commit();
                break;
            case R.id.bLoadPrefs:
                someData = getSharedPreferences(FILENAME, 0);
                String dataReturned = someData.getString(SHARED_STRING, "noData");
                dataResult.setText(dataReturned);
                break;
        }
    }
}
