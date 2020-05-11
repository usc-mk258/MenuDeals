package com.application.app.myresturants;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class SignUpAsCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);
        this.getSupportActionBar().hide();

    }
}
