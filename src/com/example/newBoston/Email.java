package com.example.newBoston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author brata@tremend.ro
 * @since 21.03.2013
 */
public class Email extends Activity implements View.OnClickListener {

    EditText personsEmail;
    EditText intro;
    EditText personsName;
    EditText stupidThings;
    EditText hatefulAction;
    EditText outro;
    String emailAdd;
    String beginning;
    String name;
    String stupidAction;
    String hatefulAct;
    String out;
    Button sendEmail;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        initializeVars();
        sendEmail.setOnClickListener(this);
    }

    private void initializeVars () {
        // TODO Auto-generated method stub
        personsEmail = (EditText) findViewById(R.id.etEmails);
        intro = (EditText) findViewById(R.id.etIntro);
        personsName = (EditText) findViewById(R.id.etName);
        stupidThings = (EditText) findViewById(R.id.etThings);
        hatefulAction = (EditText) findViewById(R.id.etAction);
        outro = (EditText) findViewById(R.id.etOutro);
        sendEmail = (Button) findViewById(R.id.bSentEmail);
    }

    public void onClick (View v) {
        convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated();
        String emailaddress[] = {emailAdd};
        String message = "Well hello "
                + name
                + " I just wanted to say "
                + beginning
                + ".  Not only that but I hate when you "
                + stupidAction
                + ", that just really makes me crazy.  I just want to make you "
                + hatefulAct
                + ".  Welp, thats all I wanted to chit-chatter about, oh and"
                + out
                + ".  Oh also if you get bored you should check out www.mybringback.com"
                + '\n' + "PS. I think I love you...   :( ";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "I hate you");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(emailIntent);
    }

    private void convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated () {
        // TODO Auto-generated method stub
        emailAdd = personsEmail.getText().toString();
        beginning = intro.getText().toString();
        name = personsName.getText().toString();
        stupidAction = stupidThings.getText().toString();
        hatefulAct = hatefulAction.getText().toString();
        out = outro.getText().toString();
    }

    @Override
    protected void onPause () {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}