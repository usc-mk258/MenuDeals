package com.application.app.myresturants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.models.DealModel;
import com.application.app.myresturants.models.OrderModel;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class DealListAdapter extends RecyclerView.Adapter<DealListAdapter.ViewHolder>{
    private ArrayList<DealModel> listdata;

    // RecyclerView recyclerView;
    public DealListAdapter(ArrayList<DealModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout_deal_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DealModel myListData = listdata.get(position);
       /* holder.tvOrderNum.setText(listdata.get(position).getOrderNumber());
        holder.tvCustomer.setText(listdata.get(position).getOrderedBy());
        holder.tvProduct.setText(listdata.get(position).getProduct());
        holder.tvStatus.setText(listdata.get(position).getStatus());*/


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+position ,Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

    //    public ImageView imageView;
        public TextView tvOrderNum,tvProduct,tvCustomer,tvStatus;
        public ConstraintLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);


       /*     this.tvOrderNum = (TextView) itemView.findViewById(R.id.order_num);
            this.tvProduct = (TextView) itemView.findViewById(R.id.product);
            this.tvCustomer = (TextView) itemView.findViewById(R.id.order_by);
            this.tvStatus = (TextView) itemView.findViewById(R.id.status);*/


            linearLayout = (ConstraintLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
