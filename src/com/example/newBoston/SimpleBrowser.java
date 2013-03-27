package com.example.newBoston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author brata@tremend.ro
 * @since 27.03.2013
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {
    WebView ourBrower;
    EditText url;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        ourBrower = (WebView) findViewById(R.id.wvBrowser);
        ourBrower.getSettings().setJavaScriptEnabled(true);
        ourBrower.getSettings().setLoadWithOverviewMode(true);
        ourBrower.getSettings().setUseWideViewPort(true);

        ourBrower.setWebViewClient(new OurViewClient());
        try {
            ourBrower.loadUrl("http://www.google.ro");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button forward = (Button) findViewById(R.id.bFoward);
        Button clearHistory = (Button) findViewById(R.id.bHistory);
        url = (EditText) findViewById(R.id.etUrl);
        url.setText("http://www.google.ro");
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.bGo:
                String theWebSite = url.getText().toString();
                ourBrower.loadUrl(theWebSite);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;
            case R.id.bBack:
                if (ourBrower.canGoBack()) {
                    ourBrower.goBack();
                }
                break;
            case R.id.bFoward:
                if (ourBrower.canGoForward()) {
                    ourBrower.goForward();
                }
                break;
            case R.id.bRefresh:
                ourBrower.reload();
                break;
            case R.id.bHistory:
                ourBrower.clearHistory();
                break;
        }
    }

    private class OurViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url) {
            view.loadUrl(url);
            return true;

        }
    }
}
