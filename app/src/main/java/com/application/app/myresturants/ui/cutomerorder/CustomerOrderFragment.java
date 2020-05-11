package com.application.app.myresturants.ui.cutomerorder;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.app.myresturants.OrderAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.models.OrderModel;

import java.util.ArrayList;

public class CustomerOrderFragment extends Fragment {

    private CustomerOrderViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_restaurant, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.orders);
        OrderAdapter adapter = new OrderAdapter(getDummyData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        return root;
    }

    private ArrayList<OrderModel> getDummyData(){
        ArrayList<OrderModel> ordersList = new ArrayList<>();
        OrderModel orderModel1 =new OrderModel();

        orderModel1.setSrNumber("1");
        orderModel1.setOrderNumber("101");
        orderModel1.setProduct("Frozen yoghurt");
        orderModel1.setOrderedBy("John");
        orderModel1.setDate("03-04-2020");
        orderModel1.setStatus("New");

        ordersList.add(orderModel1);


        OrderModel orderModel2 =new OrderModel();

        orderModel2.setSrNumber("2");
        orderModel2.setOrderNumber("102");
        orderModel2.setProduct("Ice cream sandwich");
        orderModel2.setOrderedBy("Berlin");
        orderModel2.setDate("03-04-2020");
        orderModel2.setStatus("Pendig");

        ordersList.add(orderModel2);


        OrderModel orderModel3 =new OrderModel();

        orderModel3.setSrNumber("3");
        orderModel3.setOrderNumber("103");
        orderModel3.setProduct("Cupcake");
        orderModel3.setOrderedBy("Professor");
        orderModel3.setDate("03-04-2020");
        orderModel3.setStatus("Rejected");

        ordersList.add(orderModel3);




        return ordersList;

    }
}
