package com.example.newBoston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author brata@tremend.ro
 * @since 21.03.2013
 */
public class Data extends Activity implements View.OnClickListener {
    Button start, startFor;
    EditText sendET;
    TextView gotAnswer;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initialize();
    }

    private void initialize () {
        start = (Button) findViewById(R.id.bSA);
        startFor = (Button) findViewById(R.id.bSAFR);
        sendET = (EditText) findViewById(R.id.etSend);
        gotAnswer = (TextView) findViewById(R.id.tvGot);
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);

    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.bSA:
                String bread = sendET.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("bread", bread);
                Intent intent = new Intent(Data.this, OpenedClass.class);
                intent.putExtra("basket", basket);
                startActivity(intent);
                break;
            case R.id.bSAFR:
                Intent intent1 = new Intent(Data.this, OpenedClass.class);
                startActivityForResult(intent1, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle basket = data.getExtras().getBundle("backpack");
            String s = basket.getString("answer");
            gotAnswer.setText(s);
        }
    }
}
