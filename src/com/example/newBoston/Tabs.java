package com.example.newBoston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * @author brata@tremend.ro
 * @since 26.03.2013
 */
public class Tabs extends Activity implements View.OnClickListener {
    TabHost th;
    TextView showResults;
    long start, stop;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        th = (TabHost) findViewById(R.id.tabHost);
        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button bStart = (Button) findViewById(R.id.bStartWatch);
        Button bStop = (Button) findViewById(R.id.bStopWatch);
        showResults = (TextView) findViewById(R.id.tvShowResults);
        start = stop = 0;

        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        th.setup();
        TabHost.TabSpec tabSpec = th.newTabSpec("tag1");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("StopWatch");
        th.addTab(tabSpec);

        tabSpec = th.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Tab2");
        th.addTab(tabSpec);

        tabSpec = th.newTabSpec("tag3");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("Add a tab");
        th.addTab(tabSpec);
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.bAddTab:
                TabHost.TabSpec spec = th.newTabSpec("tab1");
                spec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent (String s) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("Congrats ");
                        return text;
                    }
                });
                spec.setIndicator("New");
                th.addTab(spec);
                break;
            case R.id.bStartWatch:
                start = System.currentTimeMillis();
                break;
            case R.id.bStopWatch:
                stop = System.currentTimeMillis();
                if (start != 0) {
                    long result = stop - start;
                    int seconds = (int) result / 1000;
                    int millis = (int) result % 100;
                    int minutes = seconds / 60;
                    seconds = seconds % 60;
                    showResults.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
                }
                break;
        }
    }
}
