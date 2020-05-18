package com.application.app.myresturants.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.app.myresturants.DealListAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.ReviewListAdapter;
import com.application.app.myresturants.dialog.ConfirmDealFragment;
import com.application.app.myresturants.dialog.ReviewFormFragment;
import com.application.app.myresturants.models.DealModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
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
        ReviewListAdapter adapter = new ReviewListAdapter(getDummyData(),getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ReviewFormFragment alertDialog = new ReviewFormFragment();
                alertDialog.show(fm, "fragment_alert");
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });



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