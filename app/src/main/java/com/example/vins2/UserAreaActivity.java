package com.example.vins2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        final TextView textView3 = (TextView) findViewById(R.id.textView3);

        //Intent intent = getIntent();
        //String email = intent.getStringExtra("email");

        //String message = email + " witaj w swoim panelu użytkownika";
        //welcomeMessage.setText(message);
        //editTextTextEmailAddress.setText(email);
    }
}