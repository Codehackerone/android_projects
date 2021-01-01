package com.example.parseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        text="WELCOME "+text.toUpperCase();
        TextView textView = (TextView) findViewById(R.id.welcometext);
        textView.setText(text);
    }
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
    public void onclicksignout(View view)
    {
        Intent intent=new Intent(getApplicationContext(),loginActivity.class);
        startActivity(intent);
    }
    public void onclickwebview(View view)
    {
        Intent intent=new Intent(getApplicationContext(),webview.class);
        startActivity(intent);
    }
}