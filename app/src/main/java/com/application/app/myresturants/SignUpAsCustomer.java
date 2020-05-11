package com.application.app.myresturants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SignUpAsCustomer extends AppCompatActivity {
    TextView getStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);
        this.getSupportActionBar().hide();

        getStart = (TextView) findViewById(R.id.get_start);

        getStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpAsCustomer.this, CustomerActivity.class);
                startActivity(i);
            }
        });



    }
}
