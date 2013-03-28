package com.example.newBoston;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author brata@tremend.ro
 * @since 28.03.2013
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner spinner;
    private String[] paths = {"Music", "Picture", "Download"};
    private TextView canWrite, canRead;
    private String state;
    private Boolean canR, canW;
    private File path = null;
    private File file = null;
    EditText saveFile;
    Button confirm, save;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_data);
        initializate();
    }

    private void initializate () {
        canRead = (TextView) findViewById(R.id.tvCanRead);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        spinner = (Spinner) findViewById(R.id.spinner);
        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        saveFile = (EditText) findViewById(R.id.etSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        save.setOnClickListener(this);
        confirm.setOnClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
        int selectPosition = spinner.getSelectedItemPosition();
        switch (selectPosition) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected (AdapterView<?> parent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.bSaveFile:
                String fileName = saveFile.getText().toString();
                file = new File(path, fileName + ".png");
                stateOfMedia();
                if (canW == canR) {
                    path.mkdir();
                    InputStream is = null;
                    OutputStream os = null;
                    try {
                        is = getResources().openRawResource(R.drawable.ball);
                        os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_SHORT);
                        t.show();
                        MediaScannerConnection.scanFile(ExternalData.this,
                                new String[]{fileName.toString()},
                                null,
                                new MediaScannerConnection.MediaScannerConnectionClient() {
                                    @Override
                                    public void onMediaScannerConnected () {

                                    }

                                    @Override
                                    public void onScanCompleted (String s, Uri uri) {
                                        Toast t = Toast.makeText(ExternalData.this, "scan complete", Toast.LENGTH_SHORT);
                                        t.show();
                                    }
                                });
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is.close();
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void stateOfMedia () {
        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            canRead.setText("true");
            canWrite.setText("true");
            canR = canW = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            canRead.setText("true");
            canR = true;
            canW = false;
        } else {
            canWrite.setText("false");
            canRead.setText("false");
            canR = canW = false;
        }
    }

}