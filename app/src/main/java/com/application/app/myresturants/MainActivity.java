package com.application.app.myresturants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button signup_restaurant,getSignup_customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        signup_restaurant = findViewById(R.id.button2);
        getSignup_customer = findViewById(R.id.button);


        signup_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
            }
        });

        getSignup_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignUpAsCustomer.class);
                startActivity(i);
            }
        });
    }
}
