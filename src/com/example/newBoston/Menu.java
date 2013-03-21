package com.example.newBoston;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author brata@tremend.ro
 * @since 12.03.2013
 */
public class Menu extends ListActivity {
    String classes[] = {"MyActivity", "TextPlay", "Email", "Camera", "exemplu4", "exemplu5"};

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
}
