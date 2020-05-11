package com.application.app.myresturants;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.models.DealModel;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{
    private ArrayList<DealModel> listdata;
    private Context context;

    // RecyclerView recyclerView;
    public RestaurantAdapter(ArrayList<DealModel> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.restaurant_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DealModel myListData = listdata.get(position);
      /*  holder.tvOrderNum.setText(listdata.get(position).getOrderNumber());
        holder.tvCustomer.setText(listdata.get(position).getOrderedBy());
        holder.tvProduct.setText(listdata.get(position).getProduct());
        holder.tvStatus.setText(listdata.get(position).getStatus());
*/

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DealList.class);
                context.startActivity(i);
               // Toast.makeText(view.getContext(),"click on item: "+position ,Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView tvProduct,tvPrice;
        public ConstraintLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);


          //  this.tvProduct = (TextView) itemView.findViewById(R.id.product);


           linearLayout = (ConstraintLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
