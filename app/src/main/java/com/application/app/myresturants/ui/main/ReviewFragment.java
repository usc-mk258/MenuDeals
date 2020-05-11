package com.application.app.myresturants.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.app.myresturants.DealListAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.models.DealModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReviewFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static ReviewFragment newInstance(int index) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_review, container, false);
       // final TextView textView = root.findViewById(R.id.section_label);
//        textView.setText("reviews");
        RecyclerView recyclerView = root.findViewById(R.id.deals);
        DealListAdapter adapter = new DealListAdapter(getDummyData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return root;
    }

    private ArrayList<DealModel> getDummyData(){
        ArrayList<DealModel> ordersList = new ArrayList<>();
        DealModel orderModel1 =new DealModel();

        orderModel1.setDealName("1");

        ordersList.add(orderModel1);


/*
        DealModel orderModel2 =new DealModel();

        orderModel2.setDealName("2");
        ordersList.add(orderModel2);


        DealModel orderModel3 =new DealModel();

        orderModel3.setDealName("3");
        ordersList.add(orderModel3);
*/




        return ordersList;

    }

}