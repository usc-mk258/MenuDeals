package com.application.app.myresturants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.app.myresturants.dialog.ConfirmDealFragment;
import com.application.app.myresturants.models.DealsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantDealListAdapter extends RecyclerView.Adapter<RestaurantDealListAdapter.ViewHolder>{
    private ArrayList<DealsModel> listdata;
private Context context;
    // RecyclerView recyclerView;
    public RestaurantDealListAdapter(ArrayList<DealsModel> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
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
        final DealsModel myListData = listdata.get(position);
       /* holder.tvOrderNum.setText(listdata.get(position).getOrderNumber());
        holder.tvCustomer.setText(listdata.get(position).getOrderedBy());
        holder.tvProduct.setText(listdata.get(position).getProduct());
        holder.tvStatus.setText(listdata.get(position).getStatus());*/
holder.desc.setText(myListData.getPrice());
holder.product.setText(myListData.getDescription());
        Glide.with(context).load(myListData.getImage_url()).centerCrop().into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView desc,product;
        public ConstraintLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);


       /*     this.tvOrderNum = (TextView) itemView.findViewById(R.id.order_num);
            this.tvProduct = (TextView) itemView.findViewById(R.id.product);
            this.tvCustomer = (TextView) itemView.findViewById(R.id.order_by);
            this.tvStatus = (TextView) itemView.findViewById(R.id.status);*/

            imageView = itemView.findViewById(R.id.imageView2);
            product= itemView.findViewById(R.id.product);
            desc = itemView.findViewById(R.id.desc);
            linearLayout = (ConstraintLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
