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
        final EditText editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        //final TextView welcomeMessage = (TextView) findViewById(R.id.)

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        String message = email + " witaj w swoim panelu użytkownika";
        //welcomeMessage.setText(message);
        editTextTextEmailAddress.setText(email);
    }
}