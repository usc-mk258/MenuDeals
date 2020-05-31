package com.application.app.myresturants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.helper.Utiles;
import com.application.app.myresturants.models.OrderModel;
import com.application.app.myresturants.models.OrdersModel;

import java.text.ParseException;
import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.ViewHolder>{
    private ArrayList<OrdersModel> listdata;

    // RecyclerView recyclerView;
    public CustomerOrderAdapter(ArrayList<OrdersModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout_order_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final OrdersModel myListData = listdata.get(position);

        String eta ="-";
        if(myListData.getEta()!= null)
        {
            eta = myListData.getEta();
        }
        Utiles utiles = new Utiles();
        holder.tvOrderNum.setText((position+1)+"");
        try {
            holder.tvProduct.setText(utiles.dateFormater(myListData.getCreatedAt()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvCustomer.setText(eta);
        holder.tvStatus.setText(listdata.get(position).getStatus());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(view.getContext(),"click on item: "+position ,Toast.LENGTH_LONG).show();
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
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);


            this.tvOrderNum = (TextView) itemView.findViewById(R.id.order_num);
            this.tvProduct = (TextView) itemView.findViewById(R.id.product);
            this.tvCustomer = (TextView) itemView.findViewById(R.id.order_by);
            this.tvStatus = (TextView) itemView.findViewById(R.id.status);


            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}