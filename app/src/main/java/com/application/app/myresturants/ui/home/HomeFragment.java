package com.application.app.myresturants.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.app.myresturants.DealAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.RestaurantAdapter;
import com.application.app.myresturants.models.DealModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView restaurants;
    private HomeViewModel homeViewModel;
    private RestaurantAdapter dataAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        restaurants = root.findViewById(R.id.restaurants_rv);


        ArrayList<DealModel> dummyArray =  new ArrayList<>();
        DealModel data = new DealModel();
        data.setDealDesc("a");

        DealModel data2 = new DealModel();
        data.setDealDesc("b");

        dummyArray.add(data);
        dummyArray.add(data2);

        int numberOfColumns = 2;
        restaurants.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        dataAdapter  = new RestaurantAdapter( dummyArray);
        restaurants.setAdapter(dataAdapter);







        return root;
    }
}
