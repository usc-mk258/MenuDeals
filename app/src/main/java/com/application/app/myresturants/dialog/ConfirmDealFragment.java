package com.application.app.myresturants.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.app.myresturants.R;

import androidx.fragment.app.DialogFragment;

public class ConfirmDealFragment extends DialogFragment
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_confirm_order_fragment, container, false);
//getDialog().setTitle("Deal Info");
        // Do all the stuff to initialize your custom view

        return v;
    }
}
