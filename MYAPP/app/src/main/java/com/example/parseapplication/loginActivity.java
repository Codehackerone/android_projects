package com.example.parseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class loginActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_login);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                .clientKey("l9CBo5tn9MFe")
                .server("http://13.127.238.98/parse")
                .build()
        );
    }
    public void onclickregister(View view){
        Intent intent = new Intent(getApplicationContext(), registerActivity.class);
        startActivity(intent);
    }
    public void signin(View view){
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.pass);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        query.whereEqualTo("username", username.getText().toString());
        query.whereEqualTo("password",password.getText().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    if(scoreList.size()==0) {
                        Toast.makeText(loginActivity.this,"Username/Password Incorrect!! Try Again!!",Toast.LENGTH_LONG).show();
                        username.setText("");
                        password.setText("");
                    }
                    else
                    {
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