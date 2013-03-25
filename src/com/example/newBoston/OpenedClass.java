package com.example.newBoston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * @author brata@tremend.ro
 * @since 21.03.2013
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView question;
    TextView test;
    Button returnData;
    RadioGroup selectionList;
    String gotBread, sendData;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name", "Bogdan is....");
        String values = getData.getString("list", "4");
        if (values.contentEquals("1")) {
            question.setText(et);
        }
//        Bundle gotBasket = getIntent().getExtras().getBundle("basket");
//        gotBread = gotBasket.getString("bread");
//        question.setText(gotBread);
    }

    private void initialize () {
        question = (TextView) findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvTest);
        returnData = (Button) findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick (View view) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer", sendData);
        person.putExtra("backpack", backpack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged (RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rSmart:
                sendData = "Probably right!";
                break;
            case R.id.rSexy:
                sendData = "Definitely right!";
                break;
            case R.id.rBoth:
                sendData = "Spot on!";
                break;
        }
        test.setText(sendData);
    }
}
