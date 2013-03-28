package com.example.newBoston;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author brata@tremend.ro
 * @since 27.03.2013
 */
public class InternalData extends Activity implements View.OnClickListener {
    public static final String SHARED_STRING = "sharedString";
    static final String FILENAME = "InternalString";
    EditText shareData;
    TextView dataResult;
    FileOutputStream fileOutputStream;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.shar_prefs);
        setupVariable();
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
                String data = shareData.getText().toString();
              /*
                File f = new File(FILENAME);
                try {
                    fileOutputStream = new FileOutputStream(f);
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                */
                try {
                    fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                break;
            case R.id.bLoadPrefs:
                new LoadSomeStuff().execute(FILENAME);
//                dataResult.setText(collected);
                break;
        }
    }

    class LoadSomeStuff extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute () {
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected void onProgressUpdate (Integer... values) {
            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute (String s) {
            dataResult.setText(s);
        }

        @Override
        protected String doInBackground (String... params) {
            String collected = null;
            FileInputStream fileInputStream = null;
            for (int i = 0; i < 20; i++) {
                publishProgress(5);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            dialog.dismiss();
            try {
                fileInputStream = openFileInput(FILENAME);
                byte[] dataArray = new byte[fileInputStream.available()];
                while (fileInputStream.read(dataArray) != -1) {
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            return collected;
        }
    }
}
