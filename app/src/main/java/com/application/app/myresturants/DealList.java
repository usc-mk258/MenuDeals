package com.application.app.myresturants;

import android.os.Bundle;

import com.application.app.myresturants.models.DealModel;
import com.application.app.myresturants.models.OrderModel;
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

import com.application.app.myresturants.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class DealList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_list);
        setTheme(R.style.AppTheme);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
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



    private ArrayList<DealModel> getDummyData(){
        ArrayList<DealModel> ordersList = new ArrayList<>();
        DealModel orderModel1 =new DealModel();

        orderModel1.setDealName("1");

        ordersList.add(orderModel1);


        DealModel orderModel2 =new DealModel();

        orderModel2.setDealName("2");
        ordersList.add(orderModel2);


        DealModel orderModel3 =new DealModel();

        orderModel3.setDealName("3");
        ordersList.add(orderModel3);




        return ordersList;

    }

}