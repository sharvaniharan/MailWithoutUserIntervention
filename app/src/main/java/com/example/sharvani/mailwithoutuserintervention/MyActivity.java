package com.example.sharvani.mailwithoutuserintervention;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MyActivity extends Activity {
    public static final String DEBUGTAG = "SHARVANI TESTING";
    public String emailProvided;
    public String message;
    public EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        email = (EditText) findViewById(R.id.editText3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   public void sendMail(View v){
           emailProvided = null;
           emailProvided = email.getText().toString().trim();
           if (!(emailProvided.isEmpty())) {
               message = "Hi , This is just to show how amazing Android is. It can send mails without user intervention";

               new AlertDialog.Builder(this)
                       .setTitle("Email Sending Facility")
                       .setMessage(
                               "An email will be sent to "
                                       + emailProvided
                                       )
                       .setPositiveButton(android.R.string.yes,
                               new DialogInterface.OnClickListener() {
                                   public void onClick(
                                           DialogInterface dialog,
                                           int which) {
                                       //positive response will trigger send action
                                       new MyAsyncTask().execute();

                                   }
                               })
                       .setNegativeButton(android.R.string.no,
                               new DialogInterface.OnClickListener() {
                                   public void onClick(
                                           DialogInterface dialog,
                                           int which) {


                                   }
                               })
                       .setIcon(android.R.drawable.ic_dialog_alert)
                       .show();
           }

    }
    private class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... arg0) {
            Mail m = new Mail("onestopgrocery2014@gmail.com", "onestop@123");

            String[] toArr = {emailProvided };
            m.setTo(toArr);
            m.setFrom("onestopgrocery2014@gmail.com");
            m.setSubject("Hi!!");
            m.setBody(message);

            try {

                if (m.send()) {

                } else {

                }
            } catch (Exception e) {
                Toast.makeText(MyActivity.this,
                        "There was a problem sending the email.",
                        Toast.LENGTH_LONG).show();
                Log.e(MyActivity.DEBUGTAG, "Could not send email", e);
            }
            return "SUCCESS";

        }

        @Override
        protected void onPostExecute(String result) {


        }

    }
}

