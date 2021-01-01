package com.example.parseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class registerActivity extends AppCompatActivity {

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                .clientKey("l9CBo5tn9MFe")
                .server("http://13.127.238.98/parse")
                .build()
        );
    }
    public void onclicksignin(View view){
        Intent intent = new Intent(getApplicationContext(), loginActivity.class);
        startActivity(intent);
    }
    public void register(View view){
        EditText username = (EditText) findViewById(R.id.username);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.pass);
        ParseObject user=new ParseObject("Users");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        query.whereEqualTo("username", username.getText().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    if(scoreList.size()!=0) {
                        //Toast.makeText(registerActivity.this, scoreList.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(registerActivity.this,"Username Already Registered!! Try Again!!",Toast.LENGTH_SHORT).show();
                        username.setText("");
                        password.setText("");
                        email.setText("");
                    }
                    else
                    {
                        user.put("username", username.getText().toString());
                        user.put("email", email.getText().toString());
                        user.put("password", password.getText().toString());
                        user.saveInBackground();
                        Intent intent=new Intent(getApplicationContext(),welcomeActivity.class);
                        intent.putExtra(Intent.EXTRA_TEXT, username.getText().toString());
                        startActivity(intent);
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

    }
}