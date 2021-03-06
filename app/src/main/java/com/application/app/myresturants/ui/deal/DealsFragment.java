package com.application.app.myresturants.ui.deal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.application.app.myresturants.DealAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.CustomerToken;
import com.application.app.myresturants.models.RestautantModel;
import com.application.app.myresturants.ui.main.RestaurantPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class DealsFragment extends Fragment {

    private DealsViewModel galleryViewModel;
private RecyclerView deals;
private DealAdapter dataAdapter;

    TextView title,desc;
    RestaurantPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
    TabLayout tabs;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_deal, container, false);







        RestautantModel restaurant = new RestautantModel();
       // restaurant.setAddress("asdads");
        restaurant.setDescription("dasdasdsa");
        restaurant.setEmail("asddaads");
        restaurant.setId("asdsadsad");

        Prefrences prefrences = new Prefrences();
        CustomerToken customerToken=  prefrences.getTokenCustomer(getActivity());


         sectionsPagerAdapter = new RestaurantPagerAdapter(getContext(), getChildFragmentManager(),customerToken.getId());
         viewPager = root.findViewById(R.id.view_pager);
        tabs = root.findViewById(R.id.tabs);



        FloatingActionButton fab = root.findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });





        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      /*  Boolean introAdded = getArguments().getBoolean("introAdded");
        if(introAdded){

            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.deal_to_profile,getArguments());

        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}
