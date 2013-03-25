package com.example.newBoston;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author brata@tremend.ro
 * @since 12.03.2013
 */
public class Menu extends ListActivity {
    String classes[] = {"MyActivity", "TextPlay", "Email", "Camera", "Data", "exemplu5"};

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        String chess = classes[4];

        String classe = classes[position];
        Class ourClass = null;
        try {
            ourClass = Class.forName("com.example.newBoston." + classe);
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }
        Intent ourIntent = new Intent(Menu.this, ourClass);
        startActivity(ourIntent);
    }

    @Override
    public boolean onCreateOptionsMenu (android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(com.example.newBoston.R.menu.cool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case com.example.newBoston.R.id.aboutUs:
                Intent i = new Intent("com.example.newBoston.ABOUTUS");
                startActivity(i);
                break;
            case com.example.newBoston.R.id.preference:
                Intent i2 = new Intent("com.example.newBoston.PREFS");
                startActivity(i2);
                break;
            case com.example.newBoston.R.id.exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
