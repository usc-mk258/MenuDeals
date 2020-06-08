package com.application.app.myresturants;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.application.app.myresturants.helper.Utiles;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.google.firebase.messaging.FirebaseMessaging.INSTANCE_ID_SCOPE;

public class RestaurantActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Boolean introAdded;
    NavController navController;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        introAdded = getIntent().getExtras().getBoolean("introAdded");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Bundle bundle = new Bundle();



        bundle.putBoolean("introAdded",introAdded);
       // navController.setGraph(R.navigation.restaurant_navigation, bundle);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();

        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.nav_gallery, true)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        if(!introAdded){
            navController.navigate(R.id.nav_profile,bundle,navOptions);
        }

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                if(item.getItemId() == R.id.nav_home){

                    navController.navigate(R.id.nav_home);
                    drawer.closeDrawers();
                }
                else if(item.getItemId() == R.id.nav_gallery){
                    navController.navigate(R.id.nav_gallery);
                    drawer.closeDrawers();

                }
                else if(item.getItemId() == R.id.nav_profile){
                    navController.navigate(R.id.nav_profile);
                    drawer.closeDrawers();

                }
                else if(item.getItemId() == R.id.nav_logout){


                    new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FirebaseInstanceId.getInstance().deleteToken(FirebaseInstanceId.getInstance().getToken(), INSTANCE_ID_SCOPE);
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                            Utiles LogOutPreferences = new Utiles();
                            LogOutPreferences.LogOutPreferences(RestaurantActivity.this);
                            startActivity(new Intent(RestaurantActivity.this,LoginActivity.class));
                            RestaurantActivity.this.finish();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                }

                return false;
            }
        });

    }
    private void LogOut() throws IOException {
        Utiles LogOutPreferences = new Utiles();
        LogOutPreferences.LogOutPreferences(this);
        this.finish();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
