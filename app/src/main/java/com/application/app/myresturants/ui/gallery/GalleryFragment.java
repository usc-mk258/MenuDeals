package com.application.app.myresturants.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.app.myresturants.AddDeals;
import com.application.app.myresturants.DealAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.models.DealModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
private RecyclerView deals;
private DealAdapter dataAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
       // final TextView textView = root.findViewById(R.id.text_gallery);

        deals = root.findViewById(R.id.deal);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent i = new Intent(getActivity(), AddDeals.class);
                startActivity(i);
            }
        });



        ArrayList<DealModel> dummyArray =  new ArrayList<>();
        DealModel data = new DealModel();
        data.setDealDesc("a");

        DealModel data2 = new DealModel();
        data.setDealDesc("b");

        dummyArray.add(data);
        dummyArray.add(data2);

        int numberOfColumns = 2;
        deals.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        dataAdapter  = new DealAdapter( dummyArray);
        deals.setAdapter(dataAdapter);








        return root;
    }
}
