package com.application.app.myresturants;

import android.content.Intent;
import android.os.Bundle;

import com.application.app.myresturants.models.DealModel;
import com.application.app.myresturants.models.OrderModel;
import com.application.app.myresturants.models.RestautantModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.application.app.myresturants.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class DealList extends AppCompatActivity {

    TextView title,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_list);
        setTheme(R.style.AppTheme);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        RestautantModel restaurant = (RestautantModel) bundle.getSerializable("restaurant");

        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        title.setText(restaurant.getName());
        desc.setText(restaurant.getDescription());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),restaurant);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}