package com.application.app.myresturants;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.getSupportActionBar().hide();

    }
}